package com.ppx.dailystudy.hencoder.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author: LuoXia
 * @Date: 2023/1/18 21:07
 * @Description:
 */
public class ArgbGradientView extends View {



    private int color = 0xffff0000;
    private Paint mPaint = new Paint();

    public ArgbGradientView(Context context) {
        super(context);
    }

    public ArgbGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArgbGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ArgbGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);

        canvas.drawCircle(200, 200, 100, mPaint);
    }
}
