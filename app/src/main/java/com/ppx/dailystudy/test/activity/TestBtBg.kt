package com.ppx.dailystudy.test.activity

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.MyApplication
import com.ppx.dailystudy.R

/**
 * Author: LuoXia
 * Date: 2020/10/26 18:23
 * Description: 用于每日一题中的知识点的测试
 */
class TestBtBg : AppCompatActivity() {

    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_bg)
        context = this
        testToast()
    }

    /**
     * 1、Toast只能在ui线程中弹吗。
     * 我觉得答案应该是no，因为ui线程即主线程，我们看见很多子线程更新ui的，例如，handler.post，runOnUiThread等。
     *
     * 实际上，跟我想象的不太一样哈，其实只需要所在线程有可用Looper即可。主线程默认有looper，其他线程默认没有。
     */
    private fun testToast() {
        Toast.makeText(context, "----ui线程提示----", Toast.LENGTH_LONG).show()

        val thread = object : Thread() {
            override fun run() {
                super.run()

                // 崩溃提示：Can't create handler inside thread that has not called Looper.prepare()

                sleep(3000)
//                Toast.makeText(MyApplication.getContext(), "----子线程提示----", Toast.LENGTH_LONG).show()

                Looper.prepare()
                Toast.makeText(context, "----子线程提示----", Toast.LENGTH_LONG).show()
                Looper.loop()

            }
//            val handler = object : Handler() {
//                override fun handleMessage(msg: Message?) {
//                    super.handleMessage(msg)

//                }
//            }
        }.start()
    }
}