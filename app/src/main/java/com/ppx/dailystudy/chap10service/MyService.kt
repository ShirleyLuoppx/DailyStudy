package com.ppx.dailystudy.chap10service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

/**
 * service
 */
class MyService : Service() {

    private val TAG = "MyService"

    private val downLoaderBinder : DownLoaderBinder = DownLoaderBinder()

    override fun onBind(intent: Intent): IBinder? {
        return downLoaderBinder
    }

    /**
     * 服务创建的时候调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    /**
     * 每一次服务启动的时候都会被调用
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

    /**
     * 暂停的方法
     */
    private fun stop() {
        stopSelf()
    }

    class DownLoaderBinder : Binder() {
        private val TAG = "DownLoaderBinder"

        public fun startDownLoad() {
            Log.d(TAG, "startDownLoad: ")
        }

        public fun getProgress(): Int {
            Log.d(TAG, "getProgress: ")
            return 0
        }
    }
}
