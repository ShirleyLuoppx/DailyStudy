package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * @Author: LuoXia
 * @Date: 2021/4/27 19:52
 * @Description: 自定义View1-3 Text 之Paint 对文字绘制的辅助
 */
class TextShowStyle(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint = Paint()
    private val str = "hello ppx"

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

        /**
         * setTextSize  设置文字大小
         */
        paint.textSize = 50f
        canvas?.drawText(str, 50f, 750f, paint)
        paint.textSize = 90f
        canvas?.drawText(str, 50f, 900f, paint)

        /**
         * setTypeFace  设置字体
         */
        //默认样式
        paint.typeface = Typeface.DEFAULT
        canvas?.drawText(str, 50f, 1000f, paint)
        paint.typeface = Typeface.SERIF
        canvas?.drawText(str, 50f, 1100f, paint)
//        paint.typeface = Typeface.createFromAsset(context.assets, "Satisfy-Regular.ttf")
//        canvas?.drawText(str, 50f, 500f, paint)

        /**
         * setFakeBoldText  是否是粗体
         */
        paint.isFakeBoldText = true
        canvas?.drawText(str, 50f, 1200f, paint)

        /**
         * setStrikeThruText 是否加删除线
         */
        paint = Paint()
        paint.textSize = 90f
        paint.isStrikeThruText = true
        canvas?.drawText(str, 50f, 1300f, paint)

        /**
         * setUnderlineText  下划线
         */
        paint = Paint()
        paint.textSize = 90f
        paint.isUnderlineText = true
        canvas?.drawText(str, 50f, 1400f, paint)

        /**
         * setTextSkewX 设置文字横向错切角度，也就是文字的倾斜角度
         */
        paint = Paint()
        paint.textSize = 90f
        paint.textSkewX = -0.5f
        canvas?.drawText(str, 50f, 1500f, paint)

        /**
         * setTextScaleX 设置文字的横向缩放
         */
        paint = Paint()
        paint.textSize = 90f
        paint.textScaleX = 0.8f
        canvas?.drawText(str, 50f, 1600f, paint)
        paint.textScaleX = 1.0f
        canvas?.drawText(str, 50f, 1700f, paint)
        paint.textScaleX = 2.0f
        canvas?.drawText(str, 50f, 1800f, paint)

        /**
         * setLetterSpacing 设置字符间距
         */
        paint = Paint()
        paint.textSize = 90f
        paint.letterSpacing = 0.2f
        canvas?.drawText(str, 50f, 1900f, paint)

        /**
         * setFontFeatureSettings
         */
        paint = Paint()
        paint.textSize = 90f
        paint.fontFeatureSettings = "smcp"
        canvas?.drawText(str, 50f, 2000f, paint)

        /**
         * setTextAlign 设置文字的对齐方式....这个跟我想象种得不太一样哈哈  我以为会是一个在屏幕居左，一个居中，一个居右呢，
         * 实际上是以x的位置为中轴线，left就是文字的左边对齐中轴线，center就是文字的中间与中轴线重合，right是文字的右边与中轴线重合
         */
        paint = Paint()
        paint.textSize = 90f
        paint.textAlign = Paint.Align.LEFT
        canvas?.drawText(str, 500f, 2100f, paint)
        paint.textAlign = Paint.Align.CENTER
        canvas?.drawText(str, 500f, 2200f, paint)
        paint.textAlign = Paint.Align.RIGHT
        canvas?.drawText(str, 500f, 2300f, paint)

        /**
         * setTextLocale 设置语言
         * 好吧，还只有部分中文会有变化，普通的文字都没有看出变化
         */
        val localStr = "雨骨底条今直沿微写"
        paint = Paint()
        paint.textSize = 90f
        paint.textLocale = Locale.CHINA
        canvas?.drawText(localStr, 50f, 2400f, paint)
        paint.textLocale = Locale.JAPAN
        canvas?.drawText(localStr, 50f, 2500f, paint)
        paint.textLocale = Locale.TAIWAN
        canvas?.drawText(localStr, 50f, 2600f, paint)

        /**
         * setHinting   设置是否启用字体的 hinting （字体微调）
         *
         */

        /**
         * setSubpixelText  是否开启次像素级的抗锯齿
         */

        /**
         * setLinearText
         */
    }
}