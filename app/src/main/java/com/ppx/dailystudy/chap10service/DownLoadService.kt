package com.ppx.dailystudy.chap10service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Environment
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.ppx.dailystudy.R
import java.io.File

class DownLoadService : IntentService("DownLoadService") {
    companion object {
        const val TAG = "DownLoadService"
    }

    public var downLoadTask: DownLoadTask? = null
    private var downLoadUrl: String = ""

    private val downLoadListener = object : DownLoadListener {
        override fun onProgress(progress: Int) {
            getNotificationManager().notify(1, getNotification("downLoading", progress))
        }

        override fun onSuccess() {
            downLoadTask = null
            stopForeground(true)
            getNotificationManager().notify(1, getNotification("success", -1))
            Toast.makeText(this@DownLoadService, "success", Toast.LENGTH_SHORT).show()
        }

        override fun onFailed() {
            downLoadTask = null
            stopForeground(true)
            getNotificationManager().notify(1, getNotification("failed", -1))
            Toast.makeText(this@DownLoadService, "success", Toast.LENGTH_SHORT).show()
        }

        override fun onPause() {
            downLoadTask = null
            Toast.makeText(this@DownLoadService, "pause", Toast.LENGTH_SHORT).show()
        }

        override fun onCanceled() {
            downLoadTask = null
            stopForeground(true)
            Toast.makeText(this@DownLoadService, "cancel", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return downLoadBinder
    }

    override fun onHandleIntent(intent: Intent?) {

    }

    private val downLoadBinder = DownLoadBinder()

    /**
     * 又学到一招，如果是kt的内部类需要引用外部类的成员变量的话，内部类需要加inner修饰。
     */
    inner class DownLoadBinder : Binder() {

        fun startDownLoad(url: String) {
            if (downLoadTask == null) {
                downLoadUrl = url
                downLoadTask = DownLoadTask(downLoadListener)
                //注意这里的execute  ，不知道为啥不能提示出来，需要自己手打....
                downLoadTask?.execute(downLoadUrl)
                startForeground(1, getNotification("startDownLoad", 0))
                Log.d(TAG, "startDownLoad:")
            }
        }

        fun pauseDownLoad() {
            if (downLoadTask != null) {
                downLoadTask?.pause()
            }
        }

        fun cancelDownLoad() {
            if (downLoadTask != null) {
                downLoadTask?.cancel()
            } else {
                if (downLoadUrl != null) {
                    val fileName = downLoadUrl.substring(downLoadUrl.lastIndexOf("/"))
                    val directory =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
                    val file = File(directory + fileName)

                    if (file.exists()) {
                        file.delete()
                    }
                    getNotificationManager().cancel(1)
                    stopForeground(true)
                    Toast.makeText(
                        DownLoadService().applicationContext,
                        "cancelDownLoad",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }

    private fun getNotificationManager(): NotificationManager {
        return getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private fun getNotification(title: String, progress: Int): Notification {
        createChannel()

        val intent = Intent(this, ServiceActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "download")

        builder.apply {
            setSmallIcon(R.mipmap.ic_launcher_round)
            setContentTitle(title)
            setContentIntent(pendingIntent)
            if (progress > 0) {
                setContentText("progress:$progress")
                //最大进度，当前进度，是否使用模糊进度条
                setProgress(100, progress, false)
            }
        }

        return builder.build()
    }

    /**
     * 创建通知的通道了
     */
    private fun createChannel() {
        if (Build.VERSION.SDK_INT > 26) {
            getNotificationManager().createNotificationChannel(
                NotificationChannel(
                    "download",
                    "downloading",
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
        }
    }
}
