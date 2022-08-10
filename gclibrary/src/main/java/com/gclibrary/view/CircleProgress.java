package com.gclibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.gclibrary.R;


/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class CircleProgress extends View {

    private Context context;
    private float width;
    private float height;
    private int bgColor;
    private int foreColor;
    private Paint bgPaint;
    private Paint forePaint;
    private int totle = 100;
    private int progress = 0;

    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
//        LinearGradient lg = news LinearGradient(0, 0, view_width, bitmapWidth, DEFAULT_START_COLOR, DEFAULT_END_COLOR, Shader.TileMode.CLAMP);
//        progressPaint.setShader(lg);

        bgColor = context.getResources().getColor(R.color.white);
        foreColor = context.getResources().getColor(R.color.text_yellow_F1AA38);
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setColor(bgColor);

        forePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        forePaint.setColor(foreColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF bgRectF = new RectF(0, 0, width, height);
        canvas.drawRoundRect(bgRectF, height * 1.0f / 2, height * 1.0f / 2, bgPaint);

        RectF foreRectF = new RectF(0, 0, width*(progress*1.0f/totle), height);
        canvas.drawRoundRect(foreRectF, height * 1.0f / 2, height * 1.0f / 2, forePaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
    }

    public void setBgColorRes(@ColorRes int bgColor) {
        this.bgColor = context.getResources().getColor(bgColor);
        bgPaint.setColor(this.bgColor);
        invalidate();
    }

    public void setForeColorRes(@ColorRes int foreColor) {
        this.foreColor = context.getResources().getColor(foreColor);
        forePaint.setColor(this.foreColor);
        invalidate();
    }

    public void setProgress(int progress,int totle) {
        this.progress = progress;
        this.totle = totle;
        invalidate();
    }

    public void setProgress(int progress) {
        this.progress = progress;
        setProgress(progress,100);
    }
}
