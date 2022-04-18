package com.ppx.dailystudy.test.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @Author: LuoXia
 * @Date: 2021/7/15 10:31
 * @Description: 测试动态广播
 */
class DynamicBCReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("ippx", "onReceive: 收到动态广播拉~~ ${intent?.getStringExtra("dynamicIntentData")}")
    }
}