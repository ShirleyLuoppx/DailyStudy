package com.ppx.dailystudy.hencoder.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @Author: LuoXia
 * @Date: 2021/4/23 11:02
 * @Description: 自定义View1-3之Canvas的drawText的三种方式
 */
class CanvasDrawTextEt : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )


    private var paint: Paint = Paint()

    init {
        paint.color = Color.BLUE
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.textSize = 50f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawText("今天要打王者比赛啦，稳住，不要送", 600f, 600f, paint)
    }
}