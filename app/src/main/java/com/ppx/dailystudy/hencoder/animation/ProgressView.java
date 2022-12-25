package com.ppx.dailystudy.hencoder.animation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author: LuoXia
 * @Date: 2022/12/25 23:20
 * @Description:
 */
public class ProgressView extends View {

    private int progress;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        //通知view主动 发起重绘
        invalidate();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(1 * progress + "", 100, 100, paint);
    }
}
