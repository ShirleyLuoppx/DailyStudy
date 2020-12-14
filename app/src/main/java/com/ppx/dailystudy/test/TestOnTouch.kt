package com.ppx.dailystudy.test

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_ontouch.*


/**
 * Author: LuoXia
 * Date: 2020/11/24 14:38
 * Description: 滑动改变图标坐标
 */
class TestOnTouch : AppCompatActivity() {

    companion object {
        const val TAG = "TestOnTouch"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ontouch)

        iv_ontouch.setOnTouchListener(shopCarSettleTouch);
    }

    private val shopCarSettleTouch: View.OnTouchListener = object : View.OnTouchListener {
        var lastX = 0
        var lastY = 0
        override fun onTouch(v: View, event: MotionEvent): Boolean {
            val ea = event.action
            val dm = resources.displayMetrics
            val screenWidth = dm.widthPixels
            val screenHeight = dm.heightPixels //需要减掉图片的高度
            when (ea) {
                MotionEvent.ACTION_DOWN -> {
                    lastX = event.rawX.toInt() //获取触摸事件触摸位置的原始X坐标
                    lastY = event.rawY.toInt()
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = event.rawX.toInt() - lastX
                    val dy = event.rawY.toInt() - lastY
                    var l: Int = v.getLeft() + dx
                    var b: Int = v.getBottom() + dy
                    var r: Int = v.getRight() + dx
                    var t: Int = v.getTop() + dy
                    if (l < 0) {
                        l = 0
                        r = l + v.getWidth()
                    }
                    if (t < 0) {
                        t = 0
                        b = t + v.getHeight()
                    }
                    if (r > screenWidth) {
                        r = screenWidth
                        l = r - v.getWidth()
                    }
                    if (b > screenHeight) {
                        b = screenHeight
                        t = b - v.getHeight()
                    }
                    v.layout(l, t, r, b)
                    Log.e(TAG, "onTouch: $l==$t==$r==$b")
                    lastX = event.rawX.toInt()
                    lastY = event.rawY.toInt()
                    v.postInvalidate()
                }
                MotionEvent.ACTION_UP -> {
                }
            }
            return true
        }
    }


}