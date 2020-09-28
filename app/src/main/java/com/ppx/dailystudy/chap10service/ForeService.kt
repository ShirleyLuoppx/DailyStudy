package com.ppx.dailystudy.chap10service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.ppx.dailystudy.R

/**
 * Author: LuoXia
 * Date: 2020/9/28 21:53
 * Description: 前台服务示例
 */
class ForeService : Service() {

    private val TAG = "MyService"

    private val downLoaderBinder: DownLoaderBinder = DownLoaderBinder()
    var notificationManager: NotificationManager? = null

    override fun onBind(intent: Intent): IBinder? {
        return downLoaderBinder
    }

    /**
     * 服务创建的时候调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")

        createChannel()
        val pendingIntent = PendingIntent.getActivity(
            this, 0,
            Intent(this, ServiceActivity::class.java), 0
        )

        val builder: NotificationCompat.Builder =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationCompat.Builder(this, "foreservice")
            } else {
                NotificationCompat.Builder(this)
            }
        val notification = builder
            .setContentTitle("前台服务示例")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
    }

    /**
     * 创建一个通道
     */
    private fun createChannel(){
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager?.createNotificationChannel(NotificationChannel("foreservice", "前台服务", NotificationManager.IMPORTANCE_HIGH))
        }
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
