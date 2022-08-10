package com.gclibrary.http.interceptor;

import com.gclibrary.SpUtils;
import com.gclibrary.util.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("token", SpUtils.getInstance().getToken())
//                .addHeader("u_sn", "LwaTamHvzEQN8BoJ")
                .addHeader("u_sn", SpUtils.getInstance().getUserID())
//                .addHeader("uag_sn", "nhQkrLY2MAN6F2pA")
                .addHeader("uag_sn", SpUtils.getInstance().getDlsID())
                .addHeader("u_type", SpUtils.getInstance().getCustomUrlBool("isDls") ? "2" : "1")//1 普通用户 2 代理商
                .build();
        LogUtils.i("-----token-------" + SpUtils.getInstance().getToken());
        LogUtils.i("-----u_sn-------" + SpUtils.getInstance().getUserID());
        LogUtils.i("-----u_type-------" + (SpUtils.getInstance().getCustomUrlBool("isDls") ? "2" : "1"));
        try {
            return chain.proceed(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
