package com.ppx.dailystudy.hencoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(widthMeasureSpec) * 2);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * setShadowLayer：在之后的绘制内容下面加一层阴影。
         * radius 是阴影的模糊范围； dx dy 是阴影的偏移量； shadowColor 是阴影的颜色。
         * 如果要清除阴影层，使用 clearShadowLayer() 。
         */
        paint.setTextSize(100);
        paint.setShadowLayer(10, 0, 0, getResources().getColor(R.color.colorRedFF55));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("文字渲染文字渲染文字渲染 ", 80, 500, paint);

        /**
         *  setMaskFilter：在绘制层上方的附加效果。
         */

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girlpic);
        //原图
        canvas.drawBitmap(bitmap, 100, 600, paint);

        paint.setMaskFilter(new BlurMaskFilter(500, BlurMaskFilter.Blur.NORMAL));
        //设置了BlurMaskFilter之后的图，看起来会比原图白一些
        canvas.drawBitmap(bitmap, 100, 1500, paint);

        /**
         * EmbossMaskFilter：direction 是一个 3 个元素的数组，指定了光源的方向；
         * ambient 是环境光的强度，数值范围是 0 到 1；
         * specular 是炫光的系数；
         * blurRadius 是应用光线的范围。
         */
        paint.setMaskFilter(new EmbossMaskFilter(new float[]{0, 1, 1}, 1f, 8, 10));
        canvas.drawBitmap(bitmap, 100, 2200, paint);
    }
}
