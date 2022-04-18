package com.ppx.dailystudy.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.ppx.dailystudy.R

/**
 * @Author: LuoXia
 * @Date: 2021/8/26 18:11
 * @Description: 仿微信聊天气泡View
 */
class BubbleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint = Paint()

    /**
     * 气泡矩形圆角数
     */
    private var rectFCorner: Float = 5F

    /**
     * 气泡背景色
     */
    private var bubbleColor: Int = resources.getColor(R.color.colorBlack)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //抗锯齿
        paint.isAntiAlias = true
        paint.color = bubbleColor
        paint.style = Paint.Style.FILL

        drawLHorn(0, canvas)
        drawLHorn(1, canvas, 10F)
        drawLHorn(2, canvas, 15F)
        drawLHorn(3, canvas, 5F)
    }

    /**
     * 气泡框，四个方向的都要有 0123  左上右下
     */
    private fun drawLHorn(direction: Int, canvas: Canvas?, rectFCorner: Float = 5F) {
        when (direction) {
            0 -> {
                //来，左手跟我一起画个矩形
                val rectF = RectF(200F, 20F, 400F, 100F)
                canvas?.drawRoundRect(rectF, rectFCorner, rectFCorner, paint)

                //来，右手再跟我一起画个三角形
                val path = Path()
                path.moveTo((200 - 20).toFloat(), 50F)
                path.lineTo(200F, 60F)
                path.lineTo(200F, 45F)
                path.lineTo((200 - 20).toFloat(), 50F)
                path.close()

                //来，我们再一拼接就形成了一个聊天气泡框
                canvas?.drawPath(path, paint)
            }
            1 -> {
                val rectF = RectF(200F, 220F, 400F, 300F)
                canvas?.drawRoundRect(rectF, rectFCorner, rectFCorner, paint)

                val path = Path()
                path.moveTo(350F, (220 - 20).toFloat())
                path.lineTo((350 + 20).toFloat(), 222F)
                path.lineTo((350 - 20).toFloat(), 222F)
                path.lineTo(350F, (220 - 20).toFloat())

                canvas?.drawPath(path, paint)
            }
            2 -> {
                val rectF = RectF(420F, 20F, 620F, 100F)
                canvas?.drawRoundRect(rectF, rectFCorner, rectFCorner, paint)

                val path = Path()
                path.moveTo(620F, 50F)
                path.lineTo((600 + 30).toFloat(), 60F)
                path.lineTo(620F, 70F)
                path.lineTo(620F, 50F)

                canvas?.drawPath(path, paint)
            }
            3 -> {
                val rectF = RectF(420F, 220F, 620F, 300F)
                canvas?.drawRoundRect(rectF, rectFCorner, rectFCorner, paint)

                val path = Path()
                path.moveTo(520F, 300F)
                path.lineTo(530F, 320F)
                path.lineTo(540F, 300F)

                canvas?.drawPath(path, paint)
            }
            else -> {
            }
        }

    }
}