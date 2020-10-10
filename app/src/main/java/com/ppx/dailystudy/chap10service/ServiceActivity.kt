package com.ppx.dailystudy.chap10service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_service.*

/**
 * Author: LuoXia
 * Date: 2020/9/28 15:53
 * Description: 调用service的启动和停止
 */
class ServiceActivity : AppCompatActivity() {
    private val TAG = "ServiceActivity"
    private var downLoaderBinder = MyService.DownLoaderBinder()

    private val conn = object : ServiceConnection {
        /**
         * 和服务绑定取消的时候回调
         */
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        /**
         * 和服务绑定成功之后回调
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downLoaderBinder = service as MyService.DownLoaderBinder
            downLoaderBinder.getProgress()
            downLoaderBinder.startDownLoad()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        initEvent()
    }

    private fun initEvent() {

        /**
         * 需要注意的是：如果同时调用了startService和bindService的话，相应的需要同时调用stopService和unbindService来停止服务
         */
        bt_start_service.setOnClickListener {
            startService(Intent(this, MyService().javaClass))
        }

        bt_stop_service.setOnClickListener { stopService(Intent(this, MyService().javaClass)) }

        /**
         * BIND_AUTO_CREATE:表示服务和活动绑定后自动创建服务，这会让onCreate马上执行但是不会执行onStartCommand
         */
        bt_bind_service.setOnClickListener {
            bindService(
                Intent(this, MyService().javaClass),
                conn,
                Context.BIND_AUTO_CREATE
            )
        }

        bt_unbind_service.setOnClickListener { unbindService(conn) }

        bt_start_foreservice.setOnClickListener {
            startService(
                Intent(
                    this,
                    ForeService().javaClass
                )
            )
        }

        bt_start_intentservice.setOnClickListener {
            Log.d(TAG, "onCreate: 当前线程id：${Thread.currentThread().id}")
            startService(
                Intent(
                    this,
                    MyIntentService().javaClass
                )
            )
        }
    }
}