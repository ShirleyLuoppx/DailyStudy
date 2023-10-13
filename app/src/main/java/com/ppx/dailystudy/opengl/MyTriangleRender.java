package com.ppx.dailystudy.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.util.Log;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @Author Shirley
 * @Date：2023/10/12
 * @Desc：
 */
public class MyTriangleRender implements CustomGLSurfaceView.MyGLRender {

    private String TAG = "MyTriangleRender";
    private Context mContext;
    private FloatBuffer verTexBuffer;
    //渲染程序
    int program;
    //顶点着色器中的属性
    private int vPosition;
    //片元着色器中的属性
    private int fPosition;


    public MyTriangleRender(Context context) {
        mContext = context;

        //顶点数据
        float[] vertexData = {0f, 1f, -1f, -1f, 1f, -1f};
        //读取顶点数据
        verTexBuffer = ByteBuffer.allocateDirect(vertexData.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertexData);
        //TODO：嘛意思？？？？
        verTexBuffer.position(0);
    }


    @Override
    public void onSurfaceCreated() {
        //获取顶点着色器 shader  资源
        String vertexSource = ShaderUtils.getRawResource(mContext, R.raw.screen_vert);
        //获取片元着色器shader  资源
        String fragmentSource = ShaderUtils.getRawResource(mContext, R.raw.screen_frag_color);
        Log.d(TAG, "onSurfaceCreated: vertexSource = " + vertexSource);
//        Log.d(TAG, "onSurfaceCreated: fragmentSource = " + fragmentSource);
        //获取 源程序
        program = ShaderUtils.createProgram(vertexSource, fragmentSource);
        //从渲染程序中得到顶点着色器中的属性
        vPosition = GLES20.glGetAttribLocation(program, "vPosition");//TODO：这里的name 是从哪里知道的？？
        //从渲染程序中得到片元着色器中的属性
        fPosition = GLES20.glGetUniformLocation(program, "f_Color");
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        //设置窗口大小
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame() {
        //红色清屏
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(0f, 1f, 0f, 1f);
        //使用 着色器源程序
        GLES20.glUseProgram(program);
        //绘制绿色
        GLES20.glUniform4f(fPosition, 1f, 0f, 1f, 1f);
        //使能顶点属性数组，使之有效
        GLES20.glEnableVertexAttribArray(vPosition);
        //为顶点属性赋值，绑定顶点坐标
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, verTexBuffer);
        //绘制图形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 3);//三角形
    }
}

