package com.ppx.dailystudy.opengl.shape;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @Author Shirley
 * @Date：2023/10/13
 * @Desc： 简单四边形的surfaceview
 */
public class MyQuadrilateralSurface extends CustomGLSurfaceView {

    public MyQuadrilateralSurface(Context context) {
        super(context);
    }

    public MyQuadrilateralSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        MyQuadrilateralRender myQuadrilateralRender = new MyQuadrilateralRender(context);
        setRender(myQuadrilateralRender);
    }
}

