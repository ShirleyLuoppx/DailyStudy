package com.ppx.dailystudy.opengl;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @Author Shirley
 * @Date：2023/10/12
 * @Desc：
 */
public class MyTriangleSurface extends CustomGLSurfaceView {

    public MyTriangleSurface(Context context) {
        super(context);
    }

    public MyTriangleSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        MyTriangleRender myTriangleRender = new MyTriangleRender(context);
        setRender(myTriangleRender);
    }
}

