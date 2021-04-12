package com.ppx.dailystudy.hencoder.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/4/6 18:51
 * @Description: HenCoder自定义view第1-2节，Paint详解
 */
public class HenCoderViewOneTwoPaint extends View {

    Paint paint = new Paint();

    public HenCoderViewOneTwoPaint(Context context) {
        super(context);
    }

    public HenCoderViewOneTwoPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HenCoderViewOneTwoPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HenCoderViewOneTwoPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#111111"));

        /**
         * Shader之 LinearGradient：线性渐变色
         * 参数TileMode tile：Shader.TileMode.CLAMP、Shader.TileMode.MIRROR(镜像)、Shader.TileMode.REPEAT(重复)
         */
        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint);

        /**
         * MIRROR
         */
        Shader shader2 = new LinearGradient(600, 100, 1000, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.MIRROR);
        paint.setShader(shader2);
        canvas.drawCircle(800, 300, 200, paint);

        /**
         * REPEAT
         */
        Shader shader3 = new LinearGradient(100, 600, 500, 1000, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.REPEAT);
        paint.setShader(shader3);
        canvas.drawCircle(300, 800, 200, paint);

        /**
         * RadialGradient:辐射性渐变
         */
        Shader shader4 = new RadialGradient(800, 800, 200, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader4);
        canvas.drawCircle(800, 800, 200, paint);

        /**
         * 扫描渐变
         */
        Shader shader5 = new SweepGradient(300, 1300, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"));
        paint.setShader(shader5);
        canvas.drawCircle(300, 1300, 200, paint);

        /**
         * BitmapShader 相当于图片裁切吧
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.cherry);
        Shader shader1 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader1);
        canvas.drawCircle(800, 1300, 200, paint);


    }
}
