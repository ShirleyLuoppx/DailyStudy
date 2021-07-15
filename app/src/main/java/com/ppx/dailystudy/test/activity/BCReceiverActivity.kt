package com.ppx.dailystudy.test.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import com.ppx.dailystudy.test.receiver.DynamicBCReceiver
import com.ppx.dailystudy.test.receiver.StaticBCReceiver
import com.ppx.dailystudy.test.service.BCReceiverService
import kotlinx.android.synthetic.main.activity_bc_receiver.*

/**
 * @Author: LuoXia
 * @Date: 2021/7/15 9:50
 * @Description: 测试广播
 *
 * 总结：动态与静态广播的区别：无非就是动态广播是在代码里面注册然后加上intentfilter过滤器，静态广播在清单文件里面注册并加上intentfilter
 */
class BCReceiverActivity : AppCompatActivity() {

    private val staticReceiver = StaticBCReceiver()
    private val staticBroadCastAction = "com.ppx.static.broadcast"
    private val dynamicReceiver = DynamicBCReceiver()
    private val dynamicBroadCastAction = "com.ppx.dynamic.broadcast"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bc_receiver)

        sendStaticBroadCast()
        sendDynamicBroadCast()

        startService(Intent(this, BCReceiverService::class.java))
    }

    /**
     * 发送一个静态广播
     */
    private fun sendStaticBroadCast() {
        btn_send_static_broadcast.setOnClickListener {
            val staticIntent = Intent(staticBroadCastAction)
            staticIntent.putExtra("staticIntentData", "i am a good boy ~~")
            sendBroadcast(staticIntent)
        }
    }

    /**
     * 发送一个动态广播
     */
    private fun sendDynamicBroadCast() {
        btn_send_dynamic_broadcast.setOnClickListener {
            val dynamicIntent = Intent(dynamicBroadCastAction)
            dynamicIntent.putExtra("dynamicIntentData", "this is a data from a dynamic broadcast ~~ ")
            val dynamicIntentFilter = IntentFilter(dynamicBroadCastAction)
            registerReceiver(dynamicReceiver, dynamicIntentFilter)
            sendBroadcast(dynamicIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(staticReceiver)
    }
}