package com.ppx.dailystudy.chap10service

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Author: LuoXia
 * Date: 2020/9/28 22:31
 * Description: IntentService
 */
class MyIntentService : IntentService("MyIntentService") {
    private val TAG = "MyIntentService"

    /**
     * 完美的解决了，在服务中做耗时操作需要自己开启一个子线程且在操作完毕之后需要自己手动停止服务的问题
     * intentservice的onHandleIntent中的内容就是在子线程中执行的，且执行完毕后悔自动调用onDestroy去停止服务
     */
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent: 当前线程id：${Thread.currentThread().id}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}