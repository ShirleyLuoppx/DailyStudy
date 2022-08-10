package com.gclibrary.http;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.gclibrary.GcHelper;
import com.gclibrary.R;
import com.gclibrary.http.interceptor.RequestInterceptor;
import com.gclibrary.http.interceptor.ResponseInterceptor;
import com.gclibrary.http.util.FastJsonConvertFactory;
import com.gclibrary.util.LogUtils;
import com.gclibrary.util.ToastUtils;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by JackMar on 2017/2/27.
 * 邮箱：1261404794@qq.com
 */

public class NetWorkCenter {
    //请求超时时间
    private static final int DEFAULT_TIMEOUT = 10;
    private static OkHttpClient.Builder builder;
    private static FastJsonConvertFactory fastJsonConvertFactory = new FastJsonConvertFactory();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

//    /**
//     * 得到Api,需要传Header的情况
//     *
//     * @param t         接口Api
//     * @param mapHeader 需要添加的Headers
//     * @param <ToastUtils>
//     * @return
//     */
//    public static <ToastUtils> ToastUtils getApi(Class<ToastUtils> t, HashMap<String, String> mapHeader) {
//        if (!isInit) {
//            initInterceptor();
//        }
//        Retrofit retrofit = news Retrofit.Builder().baseUrl(NetDefine.HostUrl).client(genericClient(mapHeader))
//                //增加返回值为String的支持
//                .addConverterFactory(ScalarsConverterFactory.create())
//                //增加返回值为Gson的支持(以实体类返回)
//                .addConverterFactory(GsonConverterFactory.create())
//                //增加返回值为Oservable<ToastUtils>的支持
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
//        return retrofit.create(t);
//    }

    /**
     * 得到APi,不需要传Header
     *
     * @param t
     * @param <T>
     * @return
     */
//    public static <T> T getApi(Class<T> t) {
//        builder = news OkHttpClient.Builder();
//        initInterceptor();
//        Retrofit retrofit = news Retrofit.Builder().baseUrl(Const.HostUrl)
//                .client(builder.build())
//                //增加返回值为String的支持
//                .addConverterFactory(ScalarsConverterFactory.create())
//                //增加返回值为Gson的支持(以实体类返回)
//                .addConverterFactory(fastJsonConvertFactory)
//                //增加返回值为Oservable<ToastUtils>的支持
//                .addCallAdapterFactory(rxJavaCallAdapterFactory).build();
//        return retrofit.create(t);
//    }

    /**
     * @param t
     * @param HostUrl 自定义上传地址
     * @param <T>
     * @return
     */
    public synchronized static <T> T getApi(Class<T> t, String HostUrl) {
        builder = new OkHttpClient.Builder();
        initInterceptor();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HostUrl)
                .client(builder.build())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(new FastJsonConvertFactory())
                //增加返回值为Oservable<ToastUtils>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(t);
    }

    /**
     * 得到APi,不需要传Header
     *
     * @param t
     * @param <T>
     * @return
     */
//    public static <T> T getApiFile(Class<T> t) {
//        builder = news OkHttpClient.Builder();
//        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true);
//        Retrofit retrofit = news Retrofit.Builder().baseUrl(Const.HostUrl)
//                .client(builder.build())
//                //增加返回值为String的支持
//                .addConverterFactory(ScalarsConverterFactory.create())
//                //增加返回值为Gson的支持(以实体类返回)
//                .addConverterFactory(fastJsonConvertFactory)
//                //增加返回值为Oservable<ToastUtils>的支持
//                .addCallAdapterFactory(rxJavaCallAdapterFactory).build();
//        return retrofit.create(t);
//    }


    /**
     * 初始化设置拦截器
     * 第一个
     * 这里的拦截器主要是使用OkHttp提供的HttpLoggingInterceptor
     * 在测试阶段方便打印请求的返回数据
     * <p>
     * 第二个
     * 添加返回数据的自定义拦截器，这个拦截器是为了处理返回的数据
     * 判断请求是否成功，返回数据是否正确
     */
    private static void initInterceptor() {
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(GcHelper.getInstance().getContext()));
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .retryOnConnectionFailure(true);
        if (LogUtils.isDebug) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    LogUtils.i(message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        try {
            builder.addInterceptor(new ResponseInterceptor());
            builder.addInterceptor(new RequestInterceptor());
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort(GcHelper.getInstance().getContext(), R.string.net_fail);
        }
    }
}
