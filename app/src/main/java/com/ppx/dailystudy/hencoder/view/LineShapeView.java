package com.ppx.dailystudy.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author: LuoXia
 * @Date: 2021/4/16 18:10
 * @Description: 自定义view 1-2 Paint 线条形状的view
 */
public class LineShapeView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();

    public LineShapeView(Context context) {
        super(context);
    }

    public LineShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LineShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * setStrokeWidth 设置线条宽度
         */
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        canvas.drawCircle(205,400,200,paint);
        paint.setStrokeWidth(5);
        canvas.drawCircle(630,400,200,paint);
        paint.setStrokeWidth(40);
        canvas.drawCircle(1100,400,200,paint);

        /**
         * setStrokeCap 设置线头 BUTT：平头，默认，就是普通的方直粗线；ROUND：圆头，两边多了一个半圆；SQUARE：方头，两边多了一个矩形
         */
        paint.setStrokeWidth(30);

        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(50,650,550,650,paint);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(50,700,550,700,paint);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(50,750,550,750,paint);

        /**
         * setStrokeJoin：设置拐角形状
         */
        path.rLineTo(200, 0);
        path.rLineTo(-160, 120);

        canvas.save();

        canvas.translate(100, 100);
        // 第一种形状：MITER
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, paint);

        canvas.translate(300, 0);
        // 第二种形状：BEVEL
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, paint);

        canvas.translate(300, 0);
        // 第三种形状：ROUND
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, paint);

        canvas.restore();

    }
}
