package com.gclibrary.http.util;


import com.gclibrary.http.model.Result;

import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<Result<T>,T> {
    @Override
    public T call(Result<T> tBaseResult) {
        return tBaseResult.data;
    }
}