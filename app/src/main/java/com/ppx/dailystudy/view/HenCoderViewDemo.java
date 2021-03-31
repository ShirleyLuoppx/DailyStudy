package com.ppx.dailystudy.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Picture;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/3/31 11:20
 * @Description: 画笔和画布的一些api
 */
public class HenCoderViewDemo extends View {

    /**
     * Paint.ANTI_ALIAS_FLAG 抗锯齿，  也可以使用paint.setAntiAlias(true);
     */
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public HenCoderViewDemo(Context context) {
        super(context);
    }

    public HenCoderViewDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HenCoderViewDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HenCoderViewDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         *
         * drawColor，drawRGB --  颜色填充
         */
        //纯黑的话，就会覆蓋掉下面的內容
//        canvas.drawColor(getResources().getColor(R.color.colorBlack));

        //有一点透明度的颜色的话，后面就会有点显示出来的内容
//        canvas.drawColor(getResources().getColor(R.color.colorGray66D6));

        canvas.drawRGB(10, 20, 30);

        paint.setColor(getResources().getColor(R.color.colorDividerBlue779A));
        /**
         * Paint.Style.STROKE  画线
         * Paint.Style.FILL  画实心，默认
         */
        paint.setStyle(Paint.Style.STROKE);

        /**
         * setStrokeWidth 设置线的宽度
         */
        paint.setStrokeWidth(10);

        /**
         * drawCircle  画圆
         */
        canvas.drawCircle(500, 100, 100, paint);

        /**
         * drawRect  画矩形
         */
        canvas.drawRect(650, 20, 850, 120, paint);

        /**
         * drawPoint  画点
         */
        canvas.drawPoint(650, 150, paint);

        /**
         * drawPoints  画很多点
         */
        float[] points = {1, 1, 900, 20, 900, 50, 900, 100};
        canvas.drawPoints(points, 2, 6, paint);

        /**
         * 画椭圆
         */
        canvas.drawOval(900, 20, 1200, 120, paint);

        /**
         * 画线
         */
        canvas.drawLine(1200, 20, 1400, 120, paint);
        canvas.drawLine(1200, 120, 1400, 20, paint);

        paint.setStyle(Paint.Style.FILL);
        /**
         * 画圆角矩形
         */
        canvas.drawRoundRect(800, 200, 1200, 400, 20, 20, paint);

        /**
         * 画扇/弧形
         * 参数解释：
         * left、top、right、bottom：先画一个椭圆形
         * startAngle：view的起始度数，以x轴右侧为正，顺时针为正，逆时针为负
         * sweepAngle：扫过的度数
         * useCenter：是否连接中心，为true画出来的是扇形，false是弧形
         */
        canvas.drawArc(800, 420, 1200, 620, -90, 90, true, paint);
        paint.setColor(getResources().getColor(R.color.colorRedFF55));
        canvas.drawArc(800, 420, 1200, 620, -180, 90, true, paint);
        paint.setColor(getResources().getColor(R.color.colorBlack));
        canvas.drawArc(800, 420, 1200, 620, 0, 180, true, paint);

        /**
         * 画相对复杂一点的图形,这里画个❤，但是看不懂里面的数字
         * */
        Path path = new Path();
        path.addArc(800, 700, 1000, 1000, -225, 225);
        path.arcTo(1000, 700, 1200, 1000, -180, 225, false);
        path.lineTo(1000, 1142);
        canvas.drawPath(path, paint);

        /**
         * 使用Path，添加view
         */
        Path addCirclePath = new Path();
        addCirclePath.addCircle(200, 1200, 200, Path.Direction.CW);
        canvas.drawPath(addCirclePath, paint);

        /**
         * lineTo/rLineTo  画线，rLineTo是相对于上一次画的终点位置而言的，lineTo是以屏幕左上角（0,0）为起点
         */
        paint.setStyle(Paint.Style.STROKE);
        Path toPath = new Path();
        toPath.moveTo(500, 900);
        toPath.lineTo(600, 900);
        toPath.rLineTo(800, 0);
        toPath.rLineTo(-100, -100);
        toPath.moveTo(1300, 1000);
        toPath.lineTo(1400, 900);
        canvas.drawPath(toPath, paint);

        /**
         * 先省略二/三次贝塞尔曲线  quadTo、rQuadTo/cubicTo、rCubicTo
         */

        /**
         * 移动起点   moveTo
         */

        /**
         * 画弧线 差不多等于drawArc，useCenter=false的时候
         * 最后一个参数：forceMoveTo 是否需要强制移动点，比如path之前画了根线，再画弧线的时候，是直接移动到画弧线的地方还是有痕迹的移过去
         * 还是举个例子比较容易理解
         */
        paint.setStyle(Paint.Style.STROKE);
        Path arcToPath = new Path();
        arcToPath.arcTo(600, 1000, 800, 1200, -90, 90, false);
        arcToPath.arcTo(600, 1100, 800, 1300, -90, 90, false);
        arcToPath.arcTo(600, 1200, 800, 1400, -90, 90, true);

        /**
         * addArc  相当于arcTo的forceMoveTo=true
         */
        arcToPath.addArc(600, 1300, 800, 1500, -90, 90);
        canvas.drawPath(arcToPath, paint);

        /**
         * 封闭图形
         * closePath.close()  完全等价于  closePath.lineTo(起点)
         * 且，当画笔是填充的时候，是不需要封闭起来的，因为他本身就会是封闭的
         */
        Path closePath = new Path();
        closePath.moveTo(100, 1500);
        closePath.lineTo(500, 1500);
        closePath.lineTo(300, 1700);
        closePath.close();
        canvas.drawPath(closePath, paint);

        /**
         * 展示图片
         */
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.apple);
        canvas.drawBitmap(bitmap,800,1500,paint);

        paint.setTextSize(50);
        canvas.drawText("hello world",800,1300,paint);



    }
}
