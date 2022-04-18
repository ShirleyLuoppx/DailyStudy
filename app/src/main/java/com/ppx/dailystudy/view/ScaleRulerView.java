package com.ppx.dailystudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @CreateDate: 2021/3/30 22:01
 * @Description: 自定义刻度尺
 */
public class ScaleRulerView extends View {

    private Paint scalePaint;

    public ScaleRulerView(Context context) {
        super(context);
    }

    public ScaleRulerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScaleRulerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        scalePaint = new Paint();
        scalePaint.setColor(getResources().getColor(R.color.colorBlack));

        canvas.drawLine(100, 100, 200, 200, scalePaint);

    }
}
