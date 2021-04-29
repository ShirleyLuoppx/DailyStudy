package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @Author: LuoXia
 * @Date: 2021/4/28 17:13
 * @Description: 自定义View 1-3 Text的测量文字尺寸 类的api
 */
class MeasureTextSizeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "MeasureTextSizeView"
    private var paint = Paint()
    private val str = "It's cloudy. But i'm in a good."

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.textSize = 90f

        /**
         * getFontSpacing  获取系统的行距
         * 也就是两行字的baseline之间的距离
         */
        canvas?.drawText(str, 50f, 800f, paint)
        canvas?.drawText(str, 50f, 800f + paint.fontSpacing, paint)
        canvas?.drawText(str, 50f, 800f + paint.fontSpacing * 2, paint)

        /**
         *  FontMetircs getFontMetrics
         *  FontMetrics 是个相对专业的工具类，它提供了几个文字排印方面的数值：ascent, descent, top, bottom, leading。
         *  baseline，就是基线，一行文字的一个重心线
         *  ascent, descent，限制大部分文字的顶部和底部，会有特殊字符超过这个区间范围
         *  top, bottom：所有文字都会这个范围之间
         *  leading：额外间距，在，上行的 bottom 线和下行的 top之间的距离
         */

        val bounds = Rect(50, 1100, 1000, 1200)
        paint.style = Paint.Style.FILL
        canvas?.drawText(str, 50f, 1200f, paint)

        paint.getTextBounds(str, 0, str.length, bounds)
        bounds.left += 50
        bounds.top += 1200
        bounds.right += 50
        bounds.bottom += 1200
        paint.style = Paint.Style.STROKE
        canvas?.drawRect(bounds, paint)

        /**
         * 测量文字的宽度并返回
         */
        val strWidth = paint.measureText(str)
        canvas?.drawText(str, 50f, 1300f, paint)
        canvas?.drawLine(50f, 1300f, 50f + strWidth, 1300f, paint)

        /**
         * getTextWidths  获取每个字符的宽度
         */

        /**
         * breakText 测量文字宽度，在给定范围内截取最大字符的宽度，超出即截断，就不显示后面的了
         * breakText返回的是截取的文字个数
         */
        var measureCount = 0
        var widthArr = floatArrayOf()

        measureCount = paint.breakText(str.toCharArray(), 0, str.length, 400f, widthArr)
        canvas?.drawText(str, 0, measureCount, 50f, 1400f, paint)

        measureCount = paint.breakText(str.toCharArray(), 0, str.length, 800f, widthArr)
        canvas?.drawText(str, 0, measureCount, 50f, 1500f, paint)

        measureCount = paint.breakText(str.toCharArray(), 0, str.length, 1200f, widthArr)
        canvas?.drawText(str, 0, measureCount, 50f, 1600f, paint)

        measureCount = paint.breakText(str.toCharArray(), 0, str.length, 1600f, widthArr)
        canvas?.drawText(str, 0, measureCount, 50f, 1700f, paint)

        /**
         * getRunAdvance  计算光标的位置，，真是感觉好多不常用的方法哟
         */
        val advance = paint.getRunAdvance(str, 0, str.length, 0, str.length, false, 0)
        canvas?.drawText(str, 50f, 1800f, paint)

        paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        canvas?.drawLine(50f + advance, 1800f - 50f, 50f + advance, 1800f + 10f, paint)
    }
}