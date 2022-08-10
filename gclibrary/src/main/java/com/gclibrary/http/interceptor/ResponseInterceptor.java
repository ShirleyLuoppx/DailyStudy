package com.gclibrary.http.interceptor;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.gclibrary.GcHelper;
import com.gclibrary.R;
import com.gclibrary.http.exception.ApiException;
import com.gclibrary.http.exception.AuthenticationException;
import com.gclibrary.http.model.Result;
import com.gclibrary.util.LogUtils;
import com.gclibrary.util.ToastUtils;
import com.gclibrary.util.URLtoUTF8;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @Title 拦截器
 * @Date 2016-05-23 17:14
 */

/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * {@linkplain OkHttpClient#interceptors() application interceptor} or as a {@linkplain
 * OkHttpClient#networkInterceptors() network interceptor}. <p> The format of the logs created by
 * this class should not be considered stable and may change slightly between releases. If you need
 * a stable logging format, use your own interceptor.
 */
public final class ResponseInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("0", GcHelper.getInstance().getContext().getString(R.string.net_break_fail));
        }
        //得到结果字符串
        String baseResult;
        try {
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();
            baseResult = buffer.clone().readString(UTF8);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("0", e.getMessage());
        }

        //得到结果地址和参数
        String getUrl = response.request().url().toString();
        String params = "";

        if (response.request().body() != null) {
            Buffer buffer = new Buffer();
            response.request().body().writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = response.request().body().contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            if (isPlaintext(buffer)) {
                params = buffer.readString(charset);
            }
            params = "?" + URLtoUTF8.unescape(params);
        }

//        LogUtils.i("服务器数据：" + baseResult);
        //判断请求是否成功
        if (response.code() == 200 || response.code() == 400) {
            //判断是否有返回数据
            if (!TextUtils.isEmpty(baseResult)) {
                Result<String> result = null;
                try {
                    result = JSON.parseObject(baseResult, new TypeReference<Result<String>>() {
                    });
                } catch (Exception e) {
                    LogUtils.i(getUrl + params + "------------" + e.getMessage());
                    throw new ApiException("0", e.getMessage());
                }
                if (result != null && result.code != 200) {
                    LogUtils.i(getUrl + params + "------------" + (!TextUtils.isEmpty(result.message) ? result.message : result.massage));
                    if (result.code == 205) {
                        throw new AuthenticationException("");
                    } else {
                        throw new ApiException(result.code + "",  (!TextUtils.isEmpty(result.message) ? result.message : result.massage));
                    }
                }
            } else {
                LogUtils.i(getUrl + params + "------------返回数据错误");
                throw new ApiException(response.code() + "", GcHelper.getInstance().getContext().getString(R.string.net_data_fail));
            }

        } else {
            LogUtils.i(getUrl + params + "------------" + response.message());
            throw new ApiException(response.code() + "", GcHelper.getInstance().getContext().getString(R.string.net_fail));

        }
        return response;

    }

    private boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}
