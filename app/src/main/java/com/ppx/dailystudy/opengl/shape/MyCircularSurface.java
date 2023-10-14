package com.ppx.dailystudy.opengl.shape;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @Author Shirley
 * @Date：2023/10/13
 * @Desc：圆形的surfaceview
 */
public class MyCircularSurface extends CustomGLSurfaceView {

    public MyCircularSurface(Context context) {
        super(context);
    }

    public MyCircularSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        MyCircularRender myCircularRender = new MyCircularRender(context);
        setRender(myCircularRender);
    }
}

