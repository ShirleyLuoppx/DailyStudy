package com.ppx.dailystudy.test.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_test_lifecircle_b.*

/**
 * @Author: LuoXia
 * @Date: 2021/2/25 9:34
 * @Description: DESCRIPTION
 */
@SuppressLint("LongLogTag")
class TestLifeCircleActivityB : AppCompatActivity() {
    /**
     * 结合activity的几种启动模式：standard、singTop、singleTask、singIEInstance
     *
     * 1、当A、B、C同为standard时，A->B->C->B 的生命周期变化
     *      A新建->A onPause -> B 新建 -> A onStop -> B onPause -> C 新建 -> B onStop -> C onPause -> B重绘 -> C 销毁
     *
     * 2、当AC同为standard，B为singleTop时， A -> B -> C -> B 的生命周期变化
     *      A新建->A onPause -> B 新建 -> A onStop -> B onPause -> C 新建 -> B onStop -> C onPause -> B重绘 -> C 销毁
     *      （注：当B为singleTop时，从C到B的时候栈顶并没有一个B实例，所以需要B重绘，C销毁，与情况1一致）
     *
     * 3、当AC同为standard，B为singTask时，A -> B -> C -> B 的生命周期变化
     *      A新建->A onPause -> B 新建 -> A onStop -> B onPause -> C 新建 -> B onStop -> C onPause -> B重绘 -> C销毁
     *      （注：这里虽然看起来是一样的，是因为C -> B 的时候，发现栈内有B的实例，所以直接使用B实例，且将B之上的C实例出栈销毁）
     *
     * 4、当AC同为standard，B为singleInstance时，A -> B -> C 点两次返回 的生命周期变化
     *      A新建->A onPause -> B 新建 -> A onStop -> B onPause -> C 新建 -> B onStop -> C onPause -> A重绘 -> C 销毁 -> A onPause -> B 重绘 -> A销毁
     *      （注： 因为AC同栈，所以当C点击返回的时候，会先回到A，再次点击回到B）
     *
     * 注：   新建 = onCreate -> onStart -> onResume  ; 重绘 = onRestart -> onStart -> onResume  ;  销毁 = onStop -> onDestroy  ;
     *
     * 5、屏幕旋转时候的生命周期： onPause -> onSaveInstanceState -> onStop -> onDestroy -> onCreate -> onStart -> onRestoreInstanceState -> onResume ，
     * 如果不想要activity重建的话 ，可以在清单文件给该 activity添加配置 ： android:configChanges="screenSize|orientation"，这样，屏幕旋转的时候就只会回调 onConfigurationChanged() 。
     */
    val TAG: String = "ippxTestLifeCircleActivityB"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_lifecircle_b)
        Log.d(TAG, "onCreate: ")

        bt_click_back.setOnClickListener { finish() }
        bt_click_to_c.setOnClickListener {
            startActivity(
                Intent(
                    this@TestLifeCircleActivityB,
                    TestLifeCircleActivityC::class.java
                )
            )
        }
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.d(TAG, "onConfigurationChanged: ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: 1")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState: 1")
    }
}