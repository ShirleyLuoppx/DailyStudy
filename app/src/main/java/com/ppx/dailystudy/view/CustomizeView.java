package com.ppx.dailystudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/3/30 11:43
 * @Description: 自定义view的小demo
 */
public class CustomizeView extends View {

    private String TAG = "CustomizeView";
    private int defaultSize;
    /**
     * 注意：画笔需要定义成全局变量，因为onDraw的话是每一次测绘都会执行一次
     */
    Paint paint = new Paint();

    public CustomizeView(Context context) {
        super(context);
    }

    public CustomizeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //用context通过obtainStyledAttributes来获取一个TypedArray对象
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomizeView);
        //通过TypedArray对象调用getDimensionPixelSize获取styleable中自定义的属性值，并传入一个默认值
        //有点没太懂这个自定义属性，感觉没啥用啊
        defaultSize = typedArray.getDimensionPixelSize(R.styleable.CustomizeView_default_size, 100);
        Log.d(TAG, "CustomizeView: " + defaultSize);
        //回收 TypedArray 对象
        typedArray.recycle();
    }

    public CustomizeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomizeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        Log.d(TAG, "getMySize: mode:" + mode + "---size:" + size + "---defaultSize:" + defaultSize);

        switch (mode) {
            //如果没有指定大小，就设置为默认大小
            case MeasureSpec.UNSPECIFIED: {
                mySize = defaultSize;
                break;
            }
            //如果测量模式是最大取值为size
            case MeasureSpec.AT_MOST: {
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            //如果是固定的大小，那就不要去改变它
            case MeasureSpec.EXACTLY: {
                mySize = size;
                break;
            }
            default:
                break;
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(defaultSize, widthMeasureSpec);
        int height = getMySize(defaultSize, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.colorGrayD6));

        //半径长
        int r = getMeasuredHeight() / 2;
        //距离左边的距离加上半径
        int x = getLeft() + r;
        //距离顶部+半径
        int y = getTop() + r;
        //开始绘制
        canvas.drawCircle(x, y, r, paint);
    }
}
