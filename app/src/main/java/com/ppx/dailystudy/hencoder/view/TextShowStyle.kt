package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

/**
 * @Author: LuoXia
 * @Date: 2021/4/27 19:52
 * @Description: 自定义View1-3 Text 之Paint 对文字绘制的辅助
 */
class TextShowStyle(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val paint = Paint()
    val str = "hello ppx"

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        /**
         * setTextSize  设置文字大小
         */
        paint.textSize = 20f
        canvas?.drawText(str, 50f, 100f, paint)
        paint.textSize = 50f
        canvas?.drawText(str, 50f, 200f, paint)

        /**
         * setTypeFace  设置字体
         */
        //默认样式
        paint.typeface = Typeface.DEFAULT
        canvas?.drawText(str, 50f, 300f, paint)
        paint.typeface = Typeface.SERIF
        canvas?.drawText(str, 50f, 400f, paint)
        paint.typeface = Typeface.createFromAsset(context.assets, "Satisfy-Regular.ttf")
        canvas?.drawText(str, 50f, 500f, paint)

        /**
         * setFakeBoldText  是否是粗体
         */
        paint.isFakeBoldText = true
        canvas?.drawText(str,50f,600f,paint)

        /**
         * setStrikeThruText 是否加删除线
         */
        paint.isStrikeThruText = true
        canvas?.drawText(str,50f,700f,paint)

        /**
         * setUnderlineText  下划线
         */
        paint.isUnderlineText = true
        canvas?.drawText(str,50f,800f,paint)
    }
}