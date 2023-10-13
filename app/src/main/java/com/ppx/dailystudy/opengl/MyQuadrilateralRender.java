package com.ppx.dailystudy.opengl;

import android.content.Context;
import android.opengl.GLES20;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.utils.ShaderUtils;

import java.io.BufferedReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @Author Shirley
 * @Date：2023/10/13
 * @Desc：二维的四边形的render
 */
public class MyQuadrilateralRender implements CustomGLSurfaceView.MyGLRender {

    private Context mContext;
    FloatBuffer floatBuffer;
    //渲染程序
    private int program;
    //顶点着色器属性
    private int vPosition;
    private int fPosition;

    public MyQuadrilateralRender(Context context) {
        mContext = context;
        //TODO：四边形顶点坐标--这个坐标数据 是有要求的吗，为啥我这样写就不行，我写的这些都会缺一个角-- 真相只有一个：
        // 那就是：opengl是不能直接绘制多边形的，多边形只能通过三角形拼接而成，且重点是：前3个点形成一个三角形，后3个点形成一个三角形。
        // 关于绘制多边形的规则，可以参考这篇文章写得比较简单明了：https://blog.csdn.net/ywl5320/article/details/81161147
        float[] vertexData = {1f, 0f, 0f, 1f, 0f, -1f, -1f, 0f};

        floatBuffer = ByteBuffer.allocateDirect(vertexData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertexData);
        floatBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated() {
        //加载顶点数据
        String vertexString = ShaderUtils.getRawResource(mContext, R.raw.screen_vert);
        //加载片元数据
        String fragmentString = ShaderUtils.getRawResource(mContext, R.raw.screen_frag_color);
        //加载源程序
        program = ShaderUtils.createProgram(vertexString, fragmentString);
        //从渲染程序中获取顶点着色器属性
        vPosition = GLES20.glGetAttribLocation(program, "vPosition");//这里的接口 glGetAttribLocation和name-vPosition 可以从.vert 文件中的 attribute vec4 vPosition 寻到踪迹
        //从渲染程序中获取片元着色器属性
        fPosition = GLES20.glGetUniformLocation(program, "f_Color");//同理，这里的接口 glGetUniformLocation和参数值f_Color ，也可以从 .frag文件中的 uniform vec4 f_Color; 得到
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
        //给四边形绘制颜色
        GLES20.glUniform4f(fPosition, 0.2f, 0.5f, 0.3f, 1f);
        //使能，使渲染程序能使用
        GLES20.glEnableVertexAttribArray(vPosition);
        //绑定数据
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, floatBuffer);
        //绘制四边形，mode为 GL_TRIANGLE_STRIP-表示可以绘制连续的三角形，思路就是相邻两个三角形可以共用一条边，GL_TRIANGLES表示绘制的三角形是独立的，
        // mode为first-表示从数组的哪个点开始绘制，count-表示需要绘制几个顶点
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
    }
}

