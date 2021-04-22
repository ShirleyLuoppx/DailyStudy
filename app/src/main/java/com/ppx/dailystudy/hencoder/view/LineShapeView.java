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
        canvas.drawCircle(205, 400, 200, paint);
        paint.setStrokeWidth(5);
        canvas.drawCircle(630, 400, 200, paint);
        paint.setStrokeWidth(40);
        canvas.drawCircle(1100, 400, 200, paint);

        /**
         * setStrokeCap 设置线头 BUTT：平头，默认，就是普通的方直粗线；ROUND：圆头，两边多了一个半圆；SQUARE：方头，两边多了一个矩形
         */
        paint.setStrokeWidth(30);

        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(50, 650, 550, 650, paint);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(50, 700, 550, 700, paint);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(50, 750, 550, 750, paint);

        /**
         * setStrokeJoin：设置拐角形状
         */
        //先把点移动到(40,800)得位置
        path.moveTo(40, 800);
        //再从这个位置水平画线到x=250得位置
        path.rLineTo(250, 0);
        //再以这个最后画线得位置为原点，画线到(-230,130)得位置
        path.rLineTo(-230, 30);

        canvas.save();

        // 第一种形状：MITER（默认得），尖角
        paint.setStrokeJoin(Paint.Join.MITER);
        /**
         * setStrokeMiter： 当setStrokeJoin(Paint.Join.MITER)的时候，如果线长过长，MITER会被转成BEVEL，setStrokeMiter默认值是4，角度约29，如果角度小于这个，就会变为BEVEL
         */
        paint.setStrokeMiter(4);
        canvas.drawPath(path, paint);

        //水平移动400
        canvas.translate(400, 0);
        // 第二种形状：BEVEL，平角
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, paint);

        canvas.translate(400, 0);
        // 第三种形状：ROUND，圆角
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, paint);

        canvas.restore();

    }
}
