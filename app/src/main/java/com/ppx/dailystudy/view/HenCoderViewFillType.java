package com.ppx.dailystudy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/3/31 16:50
 * @Description: 关于自定义view第一节的path.setFillType的一个Demo
 */
public class HenCoderViewFillType extends View {

    Paint paint = new Paint();

    public HenCoderViewFillType(Context context) {
        super(context);
    }

    public HenCoderViewFillType(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HenCoderViewFillType(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HenCoderViewFillType(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(getResources().getColor(R.color.colorRedFF55));
        /**
         * 参数说明：
         * setFillType的参数说明：fillType
         *           Path.FillType.WINDING：non-zero winding rule （非零环绕数原则），任意一点射出一条线，遇到顺时针的交点+1，逆时针-1，结果不为0的区域涂色，反之不涂
         *           Path.FillType.EVEN_ODD：奇偶原则，任意一点射出一条线，与图形相交（相切不算）次数为奇数的部分涂色，反之不涂
         *           Path.FillType.INVERSE_WINDING：与WINDING相反
         *           Path.FillType.INVERSE_EVEN_ODD：与EVEN_ODD相反
         *
         * addCircle 的最后一个参数：circle绘制的方向（这个参数只在图形有交叉的时候会有作用）
         *          Path.Direction.CCW：逆时针
         *          Path.Direction.CW：顺时针
         */
        Path path = new Path();
        path.setFillType(Path.FillType.INVERSE_WINDING);
        path.addCircle(300, 300, 200, Path.Direction.CCW);
        path.addCircle(500, 300, 200, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }
}
