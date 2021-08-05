package com.ppx.dailystudy.test.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlin.concurrent.thread

/**
 * @Author: LuoXia
 * @Date: 2021/8/5 16:08
 * @Description: 测试两个activity是否能对同一thread进行操作
 */
class ThreadTestB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val thread = ThreadTestA().thread
        Log.d("ThreadTestB", "onCreate: ${thread.state}---$thread")

        thread.start()
        Log.d("ThreadTestB", "onCreate: ${thread.state}---$thread")
    }
}