package com.ppx.dailystudy.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 此类用于接收一个自定义的广播
 */
class SelfDefinedBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        Log.d("ippx", "onReceive: receive a self defined broadcast ")
    }
}
