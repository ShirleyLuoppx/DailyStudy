package com.ppx.dailystudy.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * @Author: LuoXia
 * @Date: 2021/8/26 18:11
 * @Description: 仿微信聊天气泡View
 */
class BubbleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //抗锯齿
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        drawFilletShape(canvas)
        drawLHorn(0, canvas)
    }

    /**
     * 先画一个圆角矩形
     * TODO:矩形角度需要可更改
     */
    private fun drawFilletShape(canvas: Canvas?) {
        val rectF = RectF(200F, 20F, 400F, 100F)
        canvas?.drawRoundRect(rectF, 10F, 10F, paint)
    }

    /**
     * 再画一个角  四个方向的都要有 0123  左上右下
     */
    private fun drawLHorn(direction: Int, canvas: Canvas?) {
        when (direction) {
            0 -> {
                val path = Path()
                path.moveTo((200 - 20).toFloat(), 50F)
                path.lineTo(200F, 60F)
                path.lineTo(200F, 45F)
                path.lineTo((200 - 20).toFloat(), 50F)

                canvas?.drawPath(path, paint)
            }
            else -> {
            }
        }

    }
}