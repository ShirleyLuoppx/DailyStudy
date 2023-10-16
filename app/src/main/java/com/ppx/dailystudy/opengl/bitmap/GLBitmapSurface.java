package com.ppx.dailystudy.opengl.bitmap;

import android.content.Context;
import android.util.AttributeSet;

import com.ppx.dailystudy.opengl.shape.CustomGLSurfaceView;

/**
 * @Author Shirley
 * @Date：2023/10/14
 * @Desc：
 */
public class GLBitmapSurface extends CustomGLSurfaceView {

    public GLBitmapSurface(Context context) {
        super(context);
    }

    public GLBitmapSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        GLBitmapRender glBitmapRender = new GLBitmapRender(context);
        setRender(glBitmapRender);
    }
}

