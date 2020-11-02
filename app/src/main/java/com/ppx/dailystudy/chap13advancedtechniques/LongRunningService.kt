package com.ppx.dailystudy.chap13advancedtechniques

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class LongRunningService : Service() {

    private val TAG = "LongRunningService"

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread(object : Runnable {
            override fun run() {
                Log.d(TAG, "run: 执行到-子线程测试")
            }

        }).start()
        setTimeAlarm()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun setTimeAlarm() {
        val manager = getSystemService(ALARM_SERVICE) as AlarmManager

        val time = 2
        val intent = Intent(this, LongRunningService::class.java)
        val pendingIntent = PendingIntent.getService(this, 0, intent, 0)
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time.toLong(), pendingIntent)
    }
}
