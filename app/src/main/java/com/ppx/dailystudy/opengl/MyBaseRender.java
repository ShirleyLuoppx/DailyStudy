package com.ppx.dailystudy.opengl;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * @Author Shirley
 * @Date：2023/10/11
 * @Desc： Render中使用红色清屏
 */
public class MyBaseRender implements GLSurfaceView.Renderer {

    private String TAG = "MyBaseRender";

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.d(TAG, "onSurfaceChanged: " + width + "-" + height);
        //这一句感觉没啥意思呀？？注释了也还是会有红色的全屏显示，设置其他值的width和height 也是全屏的
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //使用红色清屏
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        //设置清屏颜色的RGB百分比
        GLES20.glClearColor(0.24f, 0.48f, 0.75f, 1f);
    }
}

