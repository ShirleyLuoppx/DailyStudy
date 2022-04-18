package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.MeasureSpec

/**
 * @Author: LuoXia
 * @Date: 2021/4/28 17:13
 * @Description: 自定义View 1-3 Text的测量文字尺寸 类的api
 */
class MeasureTextSizeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "MeasureTextSizeView"
    private var paint = Paint()
    private val str = "It's cloudy. But i'm in a good."

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
        val advance = paint.getRunAdvance(str, 0, str.length, 0, str.length, false, str.length)
        canvas?.drawText(str, 50f, 1800f, paint)

        canvas?.drawLine(50f + advance, 1800f - 100f, 50f + advance, 1800f + 10f, paint)

        /**
         * 测量表情的宽度大小
         */
        val advanceStr = "我心飞扬   中国最强   \uD83C\uDDE8\uD83C\uDDF3"

        paint.strokeWidth = 5f
        val advance1 = paint.getRunAdvance(
            advanceStr,
            0,
            advanceStr.length,
            0,
            advanceStr.length,
            false,
            advanceStr.length
        )
        canvas?.drawText(advanceStr, 50f, 1900f, paint)
        canvas?.drawLine(50f + advance1, 1900f - 100f, 50f + advance1, 1900f + 10f, paint)

        val advance2 = paint.getRunAdvance(
            advanceStr,
            0,
            advanceStr.length,
            0,
            advanceStr.length,
            false,
            advanceStr.length - 1
        )
        canvas?.drawText(advanceStr, 50f, 2000f, paint)
        canvas?.drawLine(50f + advance2, 2000f - 100f, 50f + advance2, 2000f + 10f, paint)

        val advance3 = paint.getRunAdvance(
            advanceStr,
            0,
            advanceStr.length,
            0,
            advanceStr.length,
            false,
            advanceStr.length - 2
        )
        canvas?.drawText(advanceStr, 50f, 2100f, paint)
        canvas?.drawLine(50f + advance3, 2100f - 100f, 50f + advance3, 2100f + 10f, paint)

        val advance4 = paint.getRunAdvance(
            advanceStr,
            0,
            advanceStr.length,
            0,
            advanceStr.length,
            false,
            advanceStr.length - 3
        )
        canvas?.drawText(advanceStr, 50f, 2200f, paint)
        canvas?.drawLine(50f + advance4, 2200f - 100f, 50f + advance4, 2200f + 10f, paint)

        val advance5 = paint.getRunAdvance(
            advanceStr,
            0,
            advanceStr.length,
            0,
            advanceStr.length,
            false,
            advanceStr.length - 4
        )
        canvas?.drawText(advanceStr, 50f, 2300f, paint)
        canvas?.drawLine(50f + advance5, 2300f - 100f, 50f + advance5, 2300f + 10f, paint)

        val advance6 = paint.getRunAdvance(
            advanceStr,
            0,
            advanceStr.length,
            0,
            advanceStr.length,
            false,
            advanceStr.length - 5
        )
        canvas?.drawText(advanceStr, 50f, 2400f, paint)
        canvas?.drawLine(50f + advance6, 2400f - 100f, 50f + advance6, 2400f + 10f, paint)

        /**
         * getOffsetForAdvance  给出一个位置的像素值，计算出文字中最接近这个位置的字符偏移量（即第几个字符最接近这个坐标）。
         * getOffsetForAdvance() 配合上 getRunAdvance() 一起使用，就可以实现「获取用户点击处的文字坐标」的需求。
         */

        /**
         * hasGlyph 检查指定字符串是否是一个单独的字形(glyph）
         */
        val isAGlyph = paint.hasGlyph("a")
        val isABGlyph = paint.hasGlyph("ab")
        val isEmotionGlyph = paint.hasGlyph("\uD83C\uDDE8\uD83C\uDDF3")
        Log.d(TAG, "onDraw: $isAGlyph----$isABGlyph-------$isEmotionGlyph")
    }

    public fun testMergeConflict(){
        Log.d(TAG, "testMergeConflict: 测试使用git merge命令的时候 发生冲突")
    }
}