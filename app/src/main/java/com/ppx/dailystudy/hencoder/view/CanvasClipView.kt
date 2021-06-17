package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @Author: LuoXia
 * @Date: 2021/5/14 15:55
 * @Description: 自定义View 1-4 Canvas的范围裁切和几何变换
 */
class CanvasClipView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "CanvasClipView"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure: okin")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: okin111")
    }
}