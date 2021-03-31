package com.ppx.dailystudy.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/3/31 18:22
 * @Description: 自定义view第一节的家庭作业的view
 */
public class HomeWork1View extends View {

    private Paint paint = new Paint();

    public HomeWork1View(Context context) {
        super(context);
    }

    public HomeWork1View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeWork1View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HomeWork1View(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(getResources().getColor(R.color.colorBlack));
        canvas.drawCircle(200, 200, 200, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawCircle(700,200,200,paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#4990E2"));
        canvas.drawCircle(200,700,200,paint);

        Path path = new Path();

    }
}
