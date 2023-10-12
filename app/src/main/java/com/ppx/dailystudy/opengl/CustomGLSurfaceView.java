package com.ppx.dailystudy.opengl;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.ref.WeakReference;

import javax.microedition.khronos.egl.EGLContext;

/**
 * @Author Shirley
 * @Date：2023/10/11
 * @Desc： 自定义的SurfaceView
 */
public class CustomGLSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Surface surface;
    private EGLContext eglContext;
    private MyEGLThread myEGLThread;
    private MyGLRender myGLRender;
    public final static int RENDERMODE_WHEN_DIRTY = 0;//手动刷新
    public final static int RENDERMODE_CONTINUOUSLY = 1;//自动刷新

    private int mRenderMode = RENDERMODE_CONTINUOUSLY;

    public CustomGLSurfaceView(Context context) {
        this(context, null);
    }

    public CustomGLSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public void setRender(MyGLRender myRender) {
        this.myGLRender = myRender;
    }

    public void setRenderMode(int mRenderMode) {
        if (myGLRender == null) {
            throw new RuntimeException("must set render before set RenderMode");
        }
        this.mRenderMode = mRenderMode;
    }

    //添加设置Surface和EglContext的方法
    public void setSurfaceAndEglContext(Surface surface, EGLContext eglContext) {
        this.surface = surface;
        this.eglContext = eglContext;
    }

    public EGLContext getEglContext() {
        if (myEGLThread != null) {
            return myEGLThread.getEglContext();
        }
        return null;
    }

    public void requestRender() {
        if (myEGLThread != null) {
            myEGLThread.requestRender();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (surface == null) {
            surface = holder.getSurface();
        }
        myEGLThread = new MyEGLThread(new WeakReference<CustomGLSurfaceView>(this));
        myEGLThread.isCreate = true;
        myEGLThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        myEGLThread.width = width;
        myEGLThread.height = height;
        myEGLThread.isChange = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        myEGLThread.onDestroy();
        myEGLThread = null;
        surface = null;
        eglContext = null;
    }


    public interface MyGLRender {
        void onSurfaceCreated();

        void onSurfaceChanged(int width, int height);

        void onDrawFrame();
    }

    /**
     * 主要是在Thread中实现Render的三个回调
     */
    public static class MyEGLThread extends Thread {

        private String TAG = "MyEGLThread";
        private WeakReference<CustomGLSurfaceView> myGlSurfaceViewWeakReference;
        private MyEglHelper eglHelper = null;
        private Object object = null;//主要是做阻塞用

        private boolean isExit = false;
        private boolean isCreate = false;
        private boolean isChange = false;
        private boolean isStart = false;

        private int width;
        private int height;

        public MyEGLThread(WeakReference<CustomGLSurfaceView> myGlSurfaceViewWeakReference) {
            this.myGlSurfaceViewWeakReference = myGlSurfaceViewWeakReference;
        }

        @Override
        public void run() {
            super.run();
            isExit = false;
            isStart = false;
            object = new Object();
            eglHelper = new MyEglHelper();
            eglHelper.initEgl(myGlSurfaceViewWeakReference.get().surface, myGlSurfaceViewWeakReference.get().eglContext);

//            while (true) {
                if (isExit) {
                    Log.d(TAG, "run: isExit = true");
                    //释放资源
                    release();
                    return;
//                    break;
                }
                if (isStart) {
                    Log.d(TAG, "run: isStart = true");
                    if (myGlSurfaceViewWeakReference.get().mRenderMode == RENDERMODE_WHEN_DIRTY) {
                        Log.d(TAG, "run: isStart = true  mRenderMode == RENDERMODE_WHEN_DIRTY");
                        synchronized (object) {
                            try {
                                object.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (myGlSurfaceViewWeakReference.get().mRenderMode == RENDERMODE_CONTINUOUSLY) {
                        Log.d(TAG, "run: isStart = true  mRenderMode == RENDERMODE_CONTINUOUSLY");
                        try {
                            Thread.sleep(1000 / 100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        throw new RuntimeException("mRenderMode is wrong value");
                    }
                }
                onCreate();
                onChange(width, height);
                onDraw();
                isStart = true;
//            }
        }

        private void onCreate() {
            if (isCreate && myGlSurfaceViewWeakReference.get().myGLRender != null) {
                isCreate = false;
                myGlSurfaceViewWeakReference.get().myGLRender.onSurfaceCreated();
            }
        }

        private void onChange(int width, int height) {
            if (isChange && myGlSurfaceViewWeakReference.get().myGLRender != null) {
                isChange = false;
                myGlSurfaceViewWeakReference.get().myGLRender.onSurfaceChanged(width, height);
            }
        }

        private void onDraw() {
            if (myGlSurfaceViewWeakReference.get().myGLRender != null && eglHelper != null) {
                myGlSurfaceViewWeakReference.get().myGLRender.onDrawFrame();
                if (!isStart) {
                    myGlSurfaceViewWeakReference.get().myGLRender.onDrawFrame();
                }
                eglHelper.swapBuffers();

            }
        }

        private void requestRender() {
            Log.d(TAG, "requestRender: ");
            if (object != null) {
                synchronized (object) {
                    object.notifyAll();
                }
            }
        }

        public void onDestroy() {
            Log.d(TAG, "onDestroy: ");
            isExit = true;
            requestRender();
        }

        public void release() {
            Log.d(TAG, "release: ");
            if (eglHelper != null) {
                eglHelper.destroyEgl();
                eglHelper = null;
                object = null;
                myGlSurfaceViewWeakReference = null;
            }
        }

        public EGLContext getEglContext() {
            if (eglHelper != null) {
                return eglHelper.getEglContext();
            }
            return null;
        }
    }
}

