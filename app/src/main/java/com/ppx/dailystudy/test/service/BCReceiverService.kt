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

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}