package com.gclibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 12985 on 2017/5/16.
 */

public class ColorImageView extends ImageView {
    private Context context;

    public ColorImageView(Context context) {
        this(context, null);
    }

    public ColorImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setColorFilter(filterColor, isPressed() || isSelected() ? PorterDuff.Mode.SRC_ATOP : PorterDuff.Mode.DST);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);
        invalidate();
    }

    private int filterColor = 0x33000000;

    public void setFilterColor(@ColorRes int idRes) {
        this.filterColor = context.getResources().getColor(idRes);
    }
}
