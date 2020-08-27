package com.ppx.dailystudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test_finish_function.*

/**
 * Author: luoxia
 * Date: 2020/8/27 14:59
 * Description: 此类只是测试了finish的用法
 *
 *
 * 结论：在另一个Aactivity里面调用Bactivity的finish方法，点击事件是可以进来，但是Bactivity的finish()是两个活动都执行不到的
 *
 *
 */
class TestFinishFunction : AppCompatActivity() {

    private val TAG = "TestFinishFunction"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_finish_function)

        bt_finish.setOnClickListener { finishActivity() }
    }

    fun finishActivity() {
        Log.d(TAG, "finishActivity: okin")
        finish()
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ======================")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ======================")
    }

}