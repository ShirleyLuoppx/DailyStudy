package com.ppx.dailystudy.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 包上右键-new-other-BroadCast_Receiver，快捷创建一个广播接收器。lb，还自动在manifest里面自己给我们静态注册了这个MyReceiver
 * Export：是否允许接收此程序以外的广播
 * Enable：是否启用这个广播接收器
 */
class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("ippx", "onReceive: 监听到系统开机啦~~~~~~~~~~~~")
    }
}
