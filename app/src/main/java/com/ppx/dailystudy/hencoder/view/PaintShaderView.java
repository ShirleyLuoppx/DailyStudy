package com.ppx.dailystudy.hencoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/4/16 15:05
 * @Description: 自定义view 1-2 Paint篇 Shader着色器view
 */
public class PaintShaderView extends View {

    private Paint paint = new Paint();

    public PaintShaderView(Context context) {
        super(context);
    }

    public PaintShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaintShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * BitmapShader bitmap着色器，相当于图片裁切吧
         * BitmapShader()的第2、3个参数,横/纵向的 TileMode
         */
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pic);
//        Shader shader1 = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
//        paint.setShader(shader1);
//        canvas.drawCircle(getWidth() / 2, 700, 600, paint);

        /**
         * ComposeShader 混合着色器   不知道为啥没效果啊
         */
//        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.girlpic);
//        Shader shader7 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.pic);
//        Shader shader8 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//
//        Shader shader6 = new ComposeShader(shader8, shader7, PorterDuff.Mode.SRC_OVER);
//        paint.setShader(shader6);
//        canvas.drawCircle(500, 700, 600, paint);

        /**
         * 使用Xfermode
         */
        Xfermode xfermode  =new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
//        canvas.drawBitmap(,0,0,paint);
        //画矩形
        paint.setColor(getResources().getColor(R.color.colorBlueAEAC));
        canvas.drawRect(200,200,800,800,paint);
        //设置xfermode
//        paint.setXfermode(xfermode);
        //画⚪
        paint.setColor(getResources().getColor(R.color.colorRedFF55));
        canvas.drawCircle(1100,500,400,paint);
        //用完及时清理
//        paint.setXfermode(null);
    }
}
