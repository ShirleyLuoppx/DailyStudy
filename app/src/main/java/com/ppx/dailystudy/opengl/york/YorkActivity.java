package com.ppx.dailystudy.opengl.york;

import android.opengl.GLES20;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

/**
 * @Author Shirley
 * @Date：2023/10/11
 * @Desc：
 */
public class YorkActivity extends BaseActivity {
    private SurfaceView mYGLSurfaceView;
    @Override
    protected int initLayout() {
        return R.layout.activity_york;
    }

    @Override
    protected void initView() {
        mYGLSurfaceView = findViewById(R.id.mYGLSurfaceView);
        mYGLSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        YEglHelper eglHelper = new YEglHelper();
                        eglHelper.initEgl(surfaceHolder.getSurface(), null);

                        while (true) {
                            GLES20.glViewport(0, 0, width, height);

                            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
                            GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
                            eglHelper.swapBuffers();

                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                surfaceHolder = null;
            }
        });
    }
}

