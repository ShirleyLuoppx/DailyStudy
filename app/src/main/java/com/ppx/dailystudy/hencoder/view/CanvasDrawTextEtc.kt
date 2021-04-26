package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * @Author: LuoXia
 * @Date: 2021/4/23 11:02
 * @Description: 自定义View1-3之Canvas的drawText的三种方式
 */
//貌似好像似乎，自定义View必须有这个两个参数的构建方法才能被正常调用呢？
class CanvasDrawTextEt(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var paint: Paint = Paint()

    init {
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.textSize = 50f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /**
         * 第一种方式：drawText：如果x,y是(0,0)的话会看不到文字，因为文字的左边起点在文字的左下角
         */
        canvas?.drawText("今天要打王者比赛啦，稳住，不要送", 600f, 600f, paint)

        /**
         *  第二种方式：drawTextOnPath：在path上绘制文字
         *   hOffset：文字顺着水平往右的偏移量
         *   vOffset：文字顺着path往下的偏移量
         */
        paint.strokeWidth = 5f
        val pathEffectView = CornerPathEffect(50f)
        val path = Path();
        path.moveTo(50f, 1000f)
        path.rLineTo(200f, 300f)
        path.rLineTo(300f, -500f)
        path.rLineTo(100f, 100f)
        path.rLineTo(300f, 100f)
        paint.pathEffect = pathEffectView
        canvas?.drawPath(path, paint)
        /**
         * save 保存上次画的内容，而不会让上一次画的内容再下次画的时候被清空
         */
//        canvas?.save()
        val str =
            "take it easy man  take it easy man  take it easy man   take it easy man  take it easy man "
        canvas?.drawTextOnPath(str, path, 10f, 10f, paint)

//        canvas?.restore()


        /**
         * 第三种方式：StaticLayout:绘制文字。既可以为文字设置宽度上限来让文字自动换行，也会在 \n 处主动换行。
         * 但是都，被废弃了！！
         */
        val text1 = "恭喜“混子小分队”成功打进初赛"
        val staticLayout = StaticLayout(
            text1,
            paint as TextPaint?, 600, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true
        )

        val text2 = "a\nbc\ndefghi\njklm\nnopqrst\nuvwx\nyz"
        val staticLayout2 = StaticLayout(
            text2, paint as TextPaint, 600,
            Layout.Alignment.ALIGN_NORMAL, 1f, 0f, true
        )

        canvas?.save()
        canvas?.translate(50f, 1100f)
        staticLayout.draw(canvas)
        canvas?.translate(0f, 1200f)
        staticLayout2.draw(canvas)
        canvas?.restore()
    }


}