package com.ppx.dailystudy.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/4/22 15:54
 * @Description: 设置绘制之后得颜色晕染
 */
public class ShaderLayoutView extends View {

    Paint paint = new Paint();

    public ShaderLayoutView(Context context) {
        super(context);
    }

    public ShaderLayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaderLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ShaderLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * setShadowLayer：在之后的绘制内容下面加一层阴影。
         */
        paint.setTextSize(100);
        paint.setShadowLayer(10,0,0, getResources().getColor(R.color.colorRedFF55));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("文字渲染文字渲染文字渲染 ",80,500,paint);
    }
}
