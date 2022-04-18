package com.ppx.dailystudy.test.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.fragment_demo.*

/**
 * @Author: LuoXia
 * @Date: 2021/8/5 16:07
 * @Description: 测试两个activity是否能对同一thread进行操作
 * 结论：不能，在b活动引用的a活动的thread对象跟a活动中定义的thread对象不是同一个引用地址，且状态也不是同步的，b活动的状态会重新从NEW开始
 */
class ThreadTestA : AppCompatActivity() {

    val thread: Thread = Thread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_demo)

        btn_thread_test.setOnClickListener {
            startActivity(Intent(this, ThreadTestB::class.java))
        }

        Log.d("ThreadTestA", "onCreate: ${thread.state}---$thread")
        Thread.sleep(2000)
        thread.start()
        Log.d("ThreadTestA", "onCreate: ${thread.state}---$thread")
    }
}