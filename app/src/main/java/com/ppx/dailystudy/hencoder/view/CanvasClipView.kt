package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.ppx.dailystudy.R

/**
 * @Author: LuoXia
 * @Date: 2021/5/14 15:55
 * @Description: 自定义View 1-4 Canvas的范围裁切和几何变换
 * clipRect() 和 clipPath()
 */
class CanvasClipView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    private val TAG = "CanvasClipView"

    private var mPaint: Paint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            setMeasuredDimension(
                MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(widthMeasureSpec) * 2
            )
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: okin111")

        mPaint.textSize = 60f
        canvas?.drawText("this is the original picture", 10f, 50f, mPaint)
        /**
         * 原图
         */
        canvas?.drawBitmap(
            BitmapFactory.decodeResource(context.resources, R.mipmap.girlpic), 10f, 100f, mPaint
        )

        canvas?.drawText("this is the picture after clipRect", 740f, 50f, mPaint)
        /**
         * clipRect裁剪
         */
        canvas?.save()
        canvas?.clipRect(1050f, 400f, 1250f, 1000f)
        canvas?.drawBitmap(
            BitmapFactory.decodeResource(context.resources, R.mipmap.girlpic), 800f, 100f, mPaint
        )
        canvas?.restore()


        canvas?.drawText("clipPath", 10f, 1000f, mPaint)
        /**
         * clipPath 裁切
         */
        canvas?.save()
        val path = Path()
        path.addCircle(500f, 1500f, 200f, Path.Direction.CCW)
        canvas?.clipPath(path)
        canvas?.drawBitmap(BitmapFactory.decodeResource(context.resources,R.mipmap.girlpic),10f,1100f,mPaint)
        canvas?.restore()


    }
}