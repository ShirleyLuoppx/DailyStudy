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

        canvas.drawColor(Color.parseColor("#506E79"));

        /**
         * 一个实心黑圆
         */
        paint.setColor(getResources().getColor(R.color.colorBlack));
        canvas.drawCircle(200, 200, 200, paint);

        /**
         * 一个空心黑圆
         */
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        canvas.drawCircle(700, 200, 200, paint);

        /**
         * 一个实心蓝圆
         */
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#4990E2"));
        canvas.drawCircle(200, 700, 200, paint);

        /**
         * 一个黑色圆环
         */
        paint.setColor(getResources().getColor(R.color.colorBlack));
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.addCircle(700, 700, 200, Path.Direction.CW);
        path.addCircle(700, 700, 150, Path.Direction.CW);
        canvas.drawPath(path, paint);

        /**
         * 画一个饼图
         */
        paint.setColor(Color.parseColor("#F44236"));
        canvas.drawArc(200, 1000, 800, 1600, -180, 120, true, paint);

        paint.setColor(Color.parseColor("#FEC107"));
        canvas.drawArc(230, 1030, 830, 1630, -60, 50, true, paint);

        paint.setColor(Color.parseColor("#9828AE"));
        canvas.drawArc(230, 1030, 830, 1630, -8, 5, true, paint);

        paint.setColor(Color.parseColor("#9AA4A4"));
        canvas.drawArc(230, 1030, 830, 1630, -1, 5, true, paint);

        paint.setColor(Color.parseColor("#029688"));
        canvas.drawArc(230, 1030, 830, 1630, 6, 70, true, paint);

        paint.setColor(Color.parseColor("#2196F3"));
        canvas.drawArc(230, 1030, 830, 1630, 78, 102, true, paint);

        //画线和字
        paint.setColor(getResources().getColor(R.color.colorWhite));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        Path mTextPath = new Path();
        mTextPath.moveTo(100, 1000);
        mTextPath.lineTo(300, 1000);
        mTextPath.lineTo(340,1050);
        canvas.drawPath(mTextPath, paint);

        paint.setTextSize(45f);
        canvas.drawText("Lollipop", 100, 980, paint);
    }
}
