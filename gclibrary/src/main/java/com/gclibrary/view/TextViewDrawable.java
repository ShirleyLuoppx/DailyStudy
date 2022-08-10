package com.gclibrary.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gclibrary.R;
import com.gclibrary.util.ScrUtils;

/**
 * Created by 12985 on 2017/1/10.
 */
public class TextViewDrawable extends TextView {
    private Context context;
    private int drawablePadding;
    private int drawableWdith;
    private int drawableHeight;

    public TextViewDrawable(Context context) {
        this(context, null);
    }

    public TextViewDrawable(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.textview_bg, defStyleAttr, 0);
        try {
            drawablePadding = (int) ScrUtils.getRealWidth(context, a.getInt(R.styleable.textview_bg_tv_drawable_padding, 0));
            drawableWdith = (int) ScrUtils.getRealWidth(context, a.getInt(R.styleable.textview_bg_tv_drawable_width, 0));
            drawableHeight = (int) ScrUtils.getRealWidth(context, a.getInt(R.styleable.textview_bg_tv_drawable_height, 0));
        } catch (Exception e) {
        }

        a.recycle();
        init();
    }

    private void init() {
        Drawable[] drawables = getCompoundDrawables();
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawableWdith, drawableHeight);
            }
        }
        setCompoundDrawablePadding(drawablePadding);
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }

    public void setImage(int[] images){
        if(images==null) return;
        Drawable[] drawables =new Drawable[images.length];
        for (int i=0;i<drawables.length;i++) {
            if(images[i]!=0){
                drawables[i]=getResources().getDrawable(images[i]);
            }
            if (drawables[i] != null) {
                drawables[i].setBounds(0, 0, drawableWdith, drawableHeight);
            }
        }
        setCompoundDrawablePadding(drawablePadding);
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }


}
