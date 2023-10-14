package com.ppx.dailystudy.opengl.shape;

import android.content.Context;
import android.opengl.GLES20;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @Author Shirley
 * @Date：2023/10/13
 * @Desc：圆形的render
 */
public class MyCircularRender implements CustomGLSurfaceView.MyGLRender {

    private Context mContext;
    private FloatBuffer vertexBuffer;
    private int program;
    private int vPosition;
    private int fColor;

    public MyCircularRender(Context context) {
        mContext = context;
        //圆形的顶点数据
        float[] circularData = new float[720];
        for (int i = 0; i < 720; i += 2) {
            //在百度了一波之后，还是不知道cos跟sin 与坐标有啥关系。
            circularData[i] = (float) Math.cos(i);
            circularData[i + 1] = (float) Math.sin(i);
        }

        //加载顶点数据
        vertexBuffer = ByteBuffer.allocateDirect(circularData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(circularData);
        vertexBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated() {
        //加载顶点着色器数据
        String vertexSource = ShaderUtils.getRawResource(mContext, R.raw.screen_vert);
        //加载片元着色器
        String fragSource = ShaderUtils.getRawResource(mContext, R.raw.screen_frag_color);
        //加载渲染程序
        program = ShaderUtils.createProgram(vertexSource, fragSource);
        //获取顶点着色器属性
        vPosition = GLES20.glGetAttribLocation(program, "vPosition");
        //获取片元着色器属性
        fColor = GLES20.glGetUniformLocation(program, "f_Color");
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame() {
        //黑色清屏
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(0f, 0f, 0f, 1f);
        //使用渲染程序
        GLES20.glUseProgram(program);
        //绘制圆形的颜色
        GLES20.glUniform4f(fColor, 0.9f, 0.3f, 0.2f, 1f);
        //使能
        GLES20.glEnableVertexAttribArray(vPosition);
        //圆形顶点赋值
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, vertexBuffer);
        //绘制
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 360);
    }
}

