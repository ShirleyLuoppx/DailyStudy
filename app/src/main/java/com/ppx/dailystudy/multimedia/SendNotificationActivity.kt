package com.ppx.dailystudy.multimedia

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_send_notification.*

/**
 * Author: luoxia
 * Date: 2020/9/9 22:54
 * Description: 此类主要是为了学习通知栏
 */
class SendNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_notification)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val id = "chat"
            val name = "聊天消息"
            /**
             * 通知的重要程度：
             * IMPORTANCE_UNSPECIFIED：这个翻译貌似是从来不会被通知到
             * IMPORTANCE_NONE：不会展示在通知栏？、？
             * IMPORTANCE_MIN：只会显示在通知栏
             * IMPORTANCE_LOW：会显示在通知栏和状态栏
             * IMPORTANCE_DEFAULT：默认的重要程度，随便哪里都会显示，但是没有声音
             * IMPORTANCE_HIGH：高级的重要程度，随便哪里都会显示，会有声音和震动，也可能会全屏显示
             * IMPORTANCE_MAX：没用过？？
             */
            createNotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)

            val id2 = "subscribe"
            val name2 = "订阅消息"
            createNotificationChannel(id2, name2, NotificationManager.IMPORTANCE_DEFAULT)
        }

        bt_send_notification.setOnClickListener {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notification = NotificationCompat.Builder(this, "chat")//只传context的那种方法以及被废弃啦~
                .setContentTitle("this is a chat for ppx")
                .setContentText("您有一条新消息")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
                .build()
            notificationManager.notify(1, notification)
        }

        bt_send_subscribe_notification.setOnClickListener {
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notification = NotificationCompat.Builder(this, "subscribe")//只传context的那种方法以及被废弃啦~
                .setContentTitle("this is a subscribe")
                .setContentText("subscribe")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
                .build()
            notificationManager.notify(2, notification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String, importance: Int) {

        val channel = NotificationChannel(channelId, channelName, importance)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}