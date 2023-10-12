package com.ppx.dailystudy.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * @Author Shirley
 * @Date：2023/10/11
 * @Desc： 最简单的opengl的使用
 */
public class MyBaseGLSurfaceView extends GLSurfaceView {

    public MyBaseGLSurfaceView(Context context) {
        super(context);
    }

    public MyBaseGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        MyBaseRender myRender = new MyBaseRender();
        setRenderer(myRender);
    }
}

