package com.ppx.dailystudy.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_fprce_off_line.*

/**
 * Author: luoxia
 * Date: 2020/9/6 16:17
 * Description: 在这里点击按钮就发送强制下线的广播
 */
class ForceOffLineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fprce_off_line)

        br_force_offline.setOnClickListener {
            val intent = Intent("com.ppx.force.offline")
            sendBroadcast(intent)
        }
    }


}