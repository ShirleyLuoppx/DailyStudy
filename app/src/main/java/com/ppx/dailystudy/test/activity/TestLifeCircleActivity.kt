package com.ppx.dailystudy.test.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_test_lifecircle.*

/**
 * Author: LuoXia
 * Date: 2020/11/20 10:42
 * Description: 测试按下home键再回到界面  生命周期的变化  和杀进程的变化
 */
class TestLifeCircleActivity : AppCompatActivity() {

    companion object {
        private val TAG = "ippxTestLifeCircleActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_lifecircle)
        Log.d(TAG, "onCreate: ")

        bt_click_to_b.setOnClickListener {
            startActivity(
                Intent(
                    this@TestLifeCircleActivity,
                    TestLifeCircleActivityB::class.java
                )
            )
        }

        bt_click_back.setOnClickListener { finish() }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: ")
    }
}