package com.ppx.dailystudy.opengl;

import android.opengl.GLES20;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;
import com.ppx.dailystudy.opengl.york.YEglHelper;

/**
 * @Author Shirley
 * @Date：2023/10/11
 * @Desc：
 */
public class CustomGLSurfaceViewActivity extends BaseActivity {

    private SurfaceView customGLSurfaceView;

    @Override
    protected int initLayout() {
        return R.layout.activity_custom_gl_surfaceview;
    }

    @Override
    protected void initView() {
        customGLSurfaceView = findViewById(R.id.custom_gl_surfaceview);
        customGLSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        MyEglHelper myEglHelper = new MyEglHelper();
                        myEglHelper.initEgl(holder.getSurface(), null);

                        while (true) {
                            GLES20.glViewport(0, 0, width, height);
                            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
                            GLES20.glClearColor(0.24f, 0.48f, 0.75f, 1f);
                            myEglHelper.swapBuffers();

                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }.start();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                holder = null;
            }
        });


    }
}

