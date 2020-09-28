package com.ppx.dailystudy.chap10service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * 服务学习类
 */
class MyService : Service() {

    private val TAG = "MyService"


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    /**
     * 服务创建的时候调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    /**
     * 服务启动的时候调用
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 销毁的时候调用
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}
