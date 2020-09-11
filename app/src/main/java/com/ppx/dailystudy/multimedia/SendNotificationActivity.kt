package com.ppx.dailystudy.multimedia

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_send_notification.*

/**
 * Author: luoxia
 * Date: 2020/9/9 22:54
 * Description: DESCRIPTION
 */
class SendNotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_notification)

        bt_send_notification.setOnClickListener {
            var notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var notification = NotificationCompat.Builder(this)
                .setContentTitle("this is titke")
                .setContentText("this is text")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round))
                .build()
            notificationManager.notify(1, notification)
        }
    }
}