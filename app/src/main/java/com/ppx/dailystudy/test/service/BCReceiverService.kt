package com.ppx.dailystudy.test.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @Author: LuoXia
 * @Date: 2021/7/15 10:47
 * @Description: 用于启动静态广播的服务
 */
class BCReceiverService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("ippx", "onCreate: BCReceiverService 启动拉~~ ")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}