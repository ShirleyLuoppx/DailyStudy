package com.ppx.dailystudy.opengl.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.opengl.shape.CustomGLSurfaceView;
import com.ppx.dailystudy.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @Author Shirley
 * @Date：2023/10/14
 * @Desc： opengl 画图的render
 */
public class GLBitmapRender implements CustomGLSurfaceView.MyGLRender {

    private String TAG = "GLBitmapRender";
    private Context mContext;
    private int vPosition;
    private int fColor;

    private FloatBuffer vertexBuffer;
    private FloatBuffer fragmentBuffer;
    private int program;

    private int[] textureIds;

    public GLBitmapRender(Context context) {
        mContext = context;
        //顶点坐标
        float[] vertexData = new float[]{-1f, -1f, 1f, -1f, -1f, 1f, 1f, 1f};
        //加载顶点坐标
        vertexBuffer = ByteBuffer.allocateDirect(vertexData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertexData);
        vertexBuffer.position(0);
        //纹理坐标
        float[] fragData = new float[]{0f, 1f, 1f, 1f, 0f, 0f, 1f, 0f};
        fragmentBuffer = ByteBuffer.allocateDirect(fragData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fragData);
        fragmentBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated() {
        //加载顶点着色器
        String vertexSource = ShaderUtils.getRawResource(mContext, R.raw.screen_vert);
        //加载纹理着色器
        String fragSource = ShaderUtils.getRawResource(mContext, R.raw.screen_frag);
        Log.d(TAG, "onSurfaceCreated: vertexSource = " + vertexSource);
        Log.d(TAG, "onSurfaceCreated: fragSource = " + fragSource);
        //加载源程序
        program = ShaderUtils.createProgram(vertexSource, fragSource);
        //获取顶点着色器属性
        vPosition = GLES20.glGetAttribLocation(program, "vPosition");
        //获取纹理着色器属性
        fColor = GLES20.glGetAttribLocation(program, "fPosition");//TODO：不懂这里为什么还是用  glGetAttribLocation，以及 fPosition

        //创建纹理
        textureIds = new int[1];
        //生成纹理，第三个参数是指从哪儿开始取
        GLES20.glGenTextures(1, textureIds, 0);
        //绑定纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureIds[0]);
        //设置纹理的环绕方式
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
        //设置纹理的过滤方式
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        //解绑，不是真的解绑，只是说退出当前配置纹理的这个状态，进入下一个状态
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame() {
        //白色清屏
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(1f, 1f, 1f, 1f);
        //使用源程序
        GLES20.glUseProgram(program);
        //顶点着色器使能
        GLES20.glEnableVertexAttribArray(vPosition);
        //绑定顶点着色器坐标
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, vertexBuffer);
        //纹理着色器使能
        GLES20.glEnableVertexAttribArray(fColor);
        //绑定纹理着色器数据
        GLES20.glVertexAttribPointer(fColor, 2, GLES20.GL_FLOAT, false, 8, fragmentBuffer);

        //开始绘制纹理
        //激活纹理，没设置的话就从0开始激活
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        //将textureIds[0]绑定到GL_TEXTURE0
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureIds[0]);
        //获取图片
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.nobb);
        //将图片绑定到textureIds[0]纹理
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        //回收bitmap
        bitmap.recycle();
        //绘制
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
        //解绑 text2d
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

    }
}

