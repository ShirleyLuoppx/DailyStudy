package com.ppx.dailystudy.kotlin.coroutines

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @Author: LuoXia
 * @Date: 2023/1/28 21:25
 * @Description:
 */
class CoroutinesDemo : AppCompatActivity() {

    val TAG: String = "CoroutinesDemo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        main()
    }


    fun main() {
        GlobalScope.launch {
            test()

            //withContext后面的代码块又会被自动切换主线程
            //ui操作
            Log.d(TAG, "main: " + Thread.currentThread().name)
        }

    }


    //withContext是一个被suspend修饰的挂起函数，只能在协程里面调用withContext或者是在一个被suspend修饰的挂起函数里面调用--最终也就是需要在一个协程里面去调用withContext
    //withContext的神奇之处在于，能自动从IO线程切回主线程
    suspend fun test() {
        withContext(Dispatchers.IO) {
            //做一些耗时操作，例如：网络请求接口
            Log.d(TAG, "test: " + Thread.currentThread().name)
        }
    }
}