package com.ppx.dailystudy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

/**
 * @Author: LuoXia
 * @Date: 2021/3/30 16:53
 * @Description: 自定义ViewGroup的Demo
 */
public class CustomizViewGroup extends ViewGroup {
    public CustomizViewGroup(Context context) {
        super(context);
    }

    public CustomizViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomizViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomizViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int currTop = 0;
        for (int index = 0; index < getChildCount(); index++) {
            View childView = getChildAt(index);
            //调用layout()  将view绘制在界面上  ，layout(l,t,r,b)的四个参数是以ViewGroup的左上角为原点，以y轴向下绘制的
            childView.layout(0, currTop, 0 + childView.getMeasuredWidth(), currTop + childView.getMeasuredHeight());

            currTop += childView.getMeasuredHeight();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //将所有的子View进行测绘，这会触发每一个子View的onMeasure
        //需要与measureChild();进行区分，measureChild();是对单个View进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                //如果宽高都是包裹内容
                int viewGroupWidth = getMaxWidth();
                int viewGroupHeight = getTotalHeight();
                //使用setMeasuredDimension()来设置通过测量后的view的宽高
                setMeasuredDimension(viewGroupWidth, viewGroupHeight);
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //如果只有宽为包裹
                setMeasuredDimension(getMaxWidth(), height);
            } else if (heightMode == MeasureSpec.AT_MOST) {
                //如果只有高为包裹
                setMeasuredDimension(width, getTotalHeight());
            }
        }
    }

    /**
     * 计算最大宽度
     *
     * @return
     */
    public int getMaxWidth() {
        int maxWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int width = childView.getMeasuredWidth();
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    /**
     * 计算总高度
     *
     * @return
     */
    public int getTotalHeight() {
        int totalHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            totalHeight += getChildAt(i).getMeasuredHeight();
        }
        return totalHeight;
    }
}
