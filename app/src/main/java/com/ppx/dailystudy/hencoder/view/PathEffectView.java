package com.ppx.dailystudy.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author: LuoXia
 * @Date: 2021/4/22 11:36
 * @Description: 自定义view1-2 Paint - 使用 PathEffect 来给图形的轮廓设置效果
 */
public class PathEffectView extends View {

    Paint paint = new Paint();

    public PathEffectView(Context context) {
        super(context);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 比之前多出这个方法重写
     * 重写onMeasure，解决ScrollView内嵌套自定义view不能显示的问题
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        /**
         * 因为ScrollView源码中的onMeasure方法中，对heightMode == MeasureSpec.UNSPECIFIED 没有做任何处理，直接return，导致获取不到height，所以看不见view
         * 这里我们判断一下如果heightMode == MeasureSpec.UNSPECIFIED，就设置一个宽高就行
         */
        if (heightMode == MeasureSpec.UNSPECIFIED) {
            setMeasuredDimension(widthSize, widthSize * 2);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        /**
         * PathEffect 单一效果之一：
         * DashPathEffect：使用虚线来绘制线条。
         * 参数一：float类型数字，必须为偶数个，按照  画线长度、空白长度、画线长度、空白长度」……的顺序排列
         * 参数二：虚线的偏移量
         */
        PathEffect pathEffect = new DashPathEffect(new float[]{30, 10}, 10);
        paint.setPathEffect(pathEffect);
        canvas.drawCircle(230, 400, 200, paint);

        /**
         * CornerPathEffect:把所有拐角变成圆角。
         */
        PathEffect cornerPathEffect = new CornerPathEffect(40);
        paint.setPathEffect(cornerPathEffect);
        Path path1 = new Path();
        path1.moveTo(400, 600);
        path1.rLineTo(100, -400);
        path1.rLineTo(200, 400);
        path1.rLineTo(300, -400);
        path1.rLineTo(150, 500);
        path1.rLineTo(350, -100);

        canvas.drawPath(path1, paint);

        /**
         * DiscretePathEffect：把线条进行随机的偏离，让轮廓变得乱七八糟。乱七八糟的方式和程度由参数决定。
         * 参数一：拼接的每个线段的长度
         * 参数二：是偏离量，偏离量越大 线条越扭曲
         */
        PathEffect discretePathEffect = new DiscretePathEffect(20, 20);
        paint.setPathEffect(discretePathEffect);
        Path path2 = new Path();
        path2.moveTo(40, 650);
        path2.rLineTo(200, 400);
        path2.rLineTo(100, -400);
        path2.rLineTo(200, 400);
        path2.rLineTo(300, -400);
        path2.rLineTo(150, 500);
        path2.rLineTo(350, -100);
        canvas.drawPath(path2, paint);

        /**
         * PathDashPathEffect：使用一个 Path 来绘制「虚线」
         */
        Path dashPath = new Path();
        dashPath.moveTo(50, 0);
        dashPath.rLineTo(50, 100);
        dashPath.rLineTo(-100, 0);
        dashPath.rLineTo(50, -100);

        PathEffect pathDashPathEffect = new PathDashPathEffect(dashPath, 130, 0, PathDashPathEffect.Style.TRANSLATE);
        paint.setPathEffect(pathDashPathEffect);

        Path path3 = new Path();
        path3.moveTo(40, 1100);
        path3.rLineTo(200, 400);
        path3.rLineTo(100, -400);
        path3.rLineTo(200, 400);
        path3.rLineTo(300, -400);
        path3.rLineTo(150, 500);
        path3.rLineTo(350, -100);

        canvas.drawPath(path3, paint);

        /**
         * SumPathEffect：一个组合类效果的PathEffect
         */
        PathEffect dashPathEffect = new DashPathEffect(new float[]{30, 30, 20, 20, 10, 10}, 10);
        PathEffect discretePathEffect1 = new DiscretePathEffect(20, 30);
        PathEffect sumPathEffect = new SumPathEffect(dashPathEffect, discretePathEffect1);
        paint.setPathEffect(sumPathEffect);

        Path path4 = new Path();
        path4.moveTo(40, 1500);
        path4.rLineTo(200, 400);
        path4.rLineTo(100, -400);
        path4.rLineTo(200, 400);
        path4.rLineTo(300, -400);
        path4.rLineTo(150, 500);
        path4.rLineTo(350, -100);

        canvas.drawPath(path4, paint);

        /**
         * ComposePathEffect：组合效果类的 PathEffect。先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect。
         * innerpe 是先应用的， outerpe 是后应用的。感觉innerpe占主要效果
         */
        PathEffect dashPathEffect1 = new DashPathEffect(new float[]{20, 10}, 10);
        PathEffect discretePathEffect2 = new DiscretePathEffect(20, 20);
        PathEffect composePathEffect = new ComposePathEffect(dashPathEffect1, discretePathEffect2);
        paint.setPathEffect(composePathEffect);

        Path path5 = new Path();
        path5.moveTo(40, 2000);
        path5.rLineTo(200, 400);
        path5.rLineTo(100, -400);
        path5.rLineTo(200, 400);
        path5.rLineTo(300, -400);
        path5.rLineTo(150, 500);
        path5.rLineTo(350, -100);

        canvas.drawPath(path5, paint);
    }
}
