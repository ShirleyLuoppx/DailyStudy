package com.ppx.dailystudy.chap14coolweather.util

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Author: LuoXia
 * Date: 2020/11/7 20:34
 * Description: 网络请求工具类
 */
class HttpUtil {


    fun sendOkHttpRequest(address: String, callback: Callback) {
        val client = OkHttpClient()
        val request: Request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)

    }

}