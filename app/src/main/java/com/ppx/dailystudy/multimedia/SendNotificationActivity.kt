package com.ppx.dailystudy.multimedia

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_send_notification.*
import java.io.File

/**
 * Author: luoxia
 * Date: 2020/9/9 22:54
 * Description: 此类主要是为了学习通知栏
 */
class SendNotificationActivity : AppCompatActivity() {

    /**
     * NotificationManager是状态栏通知的管理类，负责发通知、清楚通知等
     * 是一个系统Service，必须通过 getSystemService()方法来获取
     */
    var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_notification)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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
            createNotificationChannel(true, id, name, NotificationManager.IMPORTANCE_HIGH)

            val id2 = "subscribe"
            val name2 = "订阅消息"
            createNotificationChannel(true, id2, name2, NotificationManager.IMPORTANCE_DEFAULT)
        }

        bt_send_notification.setOnClickListener {

            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                Intent(this, NotificationLayoutActivity::class.java),
                0
            )


            /**
             * notification是具体的状态栏通知对象，可以设置icon、文字、提示声音、振动等等参数
             */
            var builder: NotificationCompat.Builder? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder = NotificationCompat.Builder(this, "chat")
            } else {
                builder = NotificationCompat.Builder(this)
            }
            val notification = builder//只传context的那种方法以及被废弃啦~
                .setContentTitle("this is a chat for ppx")
                .setContentText("您有一条新消息")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)//设置点击状态栏中的通知后自动在通知栏中取消这个通知
                .setSound(Uri.fromFile(File("")))//可以设置通知的时间
                //所以，这里表示，通知来临时，立即振动一秒，然后静止一秒，然后再振动一秒
                .setVibrate(
                    longArrayOf(
                        0,
                        1000,
                        1000,
                        1000
                    )
                )//设置振动的频率，第一个参数为通知来临时静止的时间，第二个表示振动的时间，第三个表示静止的时间，第四个表示振动的时间，以此类推
                .build()
//            notificationManager.cancel(1)   不知道这个为哈不行。
            notificationManager?.notify(1, notification)

            platVibrate()

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
    private fun createNotificationChannel(
        isVibrate: Boolean,
        channelId: String,
        channelName: String,
        importance: Int
    ) {
        val channel = NotificationChannel(channelId, channelName, importance)
        if (isVibrate) {
            channel.enableVibration(true)//不起作用
            channel.vibrationPattern = longArrayOf(0, 1000, 1000, 1000)
        } else {
            channel.enableVibration(false)
            channel.vibrationPattern = longArrayOf(0)
        }
        notificationManager?.createNotificationChannel(channel)
    }

    /**
     * 来一个振动的方法
     * 太奇怪了，enableVibration也不起作用，权限也给了，版本也判断了
     */
    private fun platVibrate() {
        val vibrate = getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        vibrate.vibrate(longArrayOf(0, 1000, 1000, 1000), -1)
    }
}