package com.ppx.dailystudy.test.activity

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import com.ppx.dailystudy.thread.AsyncTaskDemo
import kotlinx.android.synthetic.main.activiyu_timepicker_test.*

/**
 * Author: LuoXia
 * Date: 2020/11/5 9:18
 * Description: 111
 */
class TestDemo : AppCompatActivity() {

    private val TAG: String = "TestDemo"
    private lateinit var mHandler: Handler
    private val asyncTaskDemo = AsyncTaskDemo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiyu_timepicker_test)

        timepicker.setOnTimeChangedListener { view, hourOfDay, minute ->

        }

        /**
         * 测试handler.postDelay的run()里面 再调用handler.postDelay
         *
         * 结论：延迟第一个postDelayed()的时间去调用run()里面的内容，然后run()里面的内容延迟循环调用
         */
//        Log.d(TAG, "onCreate: 111111111111")
//        testDoublePostDelay()
//        mHandler = Handler(Looper.getMainLooper())
//        mHandler.postDelayed(object : Runnable {
//            override fun run() {
//                Log.d(TAG, "run: 3333333")
//                testDoublePostDelay()
//                mHandler.postDelayed(this, 5 * 1000)
//            }
//        }, 10 * 1000)

        asyncTaskDemo.test()
    }

    private fun testDoublePostDelay() {
        Log.d(TAG, "testDoublePostDelay: 22222222222")
    }
}