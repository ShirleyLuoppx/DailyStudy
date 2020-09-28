package com.ppx.dailystudy.chap10service

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_service.*

/**
 * Author: LuoXia
 * Date: 2020/9/28 15:53
 * Description: 调用service的启动和停止
 */
class ServiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        bt_start_service.setOnClickListener {
            startService(Intent(this,MyService().javaClass))
        }

        bt_stop_service.setOnClickListener { stopService(Intent(this, MyService().javaClass)) }
    }
}