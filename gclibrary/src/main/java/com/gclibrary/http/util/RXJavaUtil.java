package com.gclibrary.http.util;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JackMar on 2017/2/28.
 * 邮箱：1261404794@qq.com
 */
public class RXJavaUtil {
    /**
     * 线程调度，将网络请求放在后台线程操作，在主线程上界面显示
     *
     * @param o
     * @param s
     * @param <T>
     */
    public static <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }
//
//    /**
//     * 用来统一处理Http的resultCode,riber
//     *并将HttpResult的Data部分剥离出来返回给subsc
//     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
//     */
//    public static class HttpResultFunc<T> implements Func1<BaseResult<T>, T> {
//        @Override
//        public T call(BaseResult<T> baseResult) {
//            if (tBaseResult.getHead().getCode() != 1) {
//                throw news ApiException(tBaseResult.getHead().getMsg());
//            }
//            return tBaseResult.getBody();
//        }
//    }

    public static <T> void toCacheSubscribe(String key, Observable<T> o, Subscriber<T> s) {

        o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }
}
