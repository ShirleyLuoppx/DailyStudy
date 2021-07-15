package com.ppx.dailystudy.test.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * @Author: LuoXia
 * @Date: 2021/7/15 9:51
 * @Description: 测试静态广播
 */
class StaticBCReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("ippx", "onReceive: 收到广播拉  ${intent?.getStringExtra("staticIntentData")}")
    }
}