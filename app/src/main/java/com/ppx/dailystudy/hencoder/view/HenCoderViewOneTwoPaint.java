package com.ppx.dailystudy.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.parseColor("#111111"));

        /**
         * Shader之 线性渐变色
         * 参数TileMode tile：Shader.TileMode.CLAMP、Shader.TileMode.MIRROR(镜像)、Shader.TileMode.REPEAT(重复)
         */
        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint);
    }
}
