package com.ppx.dailystudy.chap13advancedtechniques

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * 加一个闹钟服务
 * 一些多窗口模式的知识：
 * 1、应用是默认支持多窗口切换的，tarGetsdkVersion>24的时候可以在application使用   android:resizeableActivity="false"  来禁用多窗口模式
 * 2、如果是targetsdkVersion<24的时候，也可以在活动上指明：android:screenOrientation="portrait"  只 支持竖屏，android:screenOrientation="landscape"只支持横屏
 * 3、多窗口切换的时候的生命周期变化：例如，A应用多窗口切到B应用再到A，
 *  启动A：此时的生命周期变化为-->A onCreate--> A onStart -->  A onResume-->
 *  进入多窗口模式：  A onPause -->  A onStop --> A onDestroy -->   A onCreate--> A onStart -->  A onResume-->  A onPause    相当于经历了一次重建，因为进入多窗口模式活动大小发生较明显改变，默认重建活动
 *  进入B应用：  B onCreate --> B onStart --> A onResume
 *  再切到A ：   B  onPause         A  onResume
 *  也可以在activity使用     android:configChanges="keyboardHidden|screenSize|screenLayout|orientation"  来阻止活动的重建，而转到  onConfigurationChanged() 里面去处理屏幕变化情况
 */
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
