package com.ppx.dailystudy.opengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.opengl.shape.CustomGLSurfaceView;
import com.ppx.dailystudy.utils.ShaderUtils;
import com.ppx.dailystudy.utils.TextureUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * @Author Shirley
 * @Date：2023/10/30
 * @Desc：添加水印
 */
public class WaterMarkRender implements CustomGLSurfaceView.MyGLRender {


    private Context mContext;
    private FloatBuffer vertexBuffer;
    private FloatBuffer fragBuffer;
    private int program;
    private int vPosition;
    private int fPosition;
    private int vboId;
    private int[] textureIds;
    private int imageTextureId;
    private int textTextureId;


    //顶点坐标
    float[] vertexData = {-1f, -1f, 1f, -1f, -1f, 1f, 1f, 1f,
            //用来 加一个 图片水印 到左上角
            -1f, 0.5f, 0f, 0.5f, -1f, 1f, 0f, 1f,

            //用来 加一个文字水印 到右下角
            0f, -1f, 1f, -1f, 0f, -0.8f, 1f, -0.8f};
    //纹理坐标
    float[] fragmentData = {0f, 1f, 1f, 1f, 0f, 0f, 1f, 0f};

    public WaterMarkRender(Context context) {
        mContext = context;
        //读取顶点坐标
        vertexBuffer = ByteBuffer.allocateDirect(vertexData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertexData);
        vertexBuffer.position(0);
        //读取纹理坐标
        fragBuffer = ByteBuffer.allocateDirect(fragmentData.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fragmentData);
        fragBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated() {
        //获取顶点着色器资源文件str
        String vPositionStr = ShaderUtils.getRawResource(mContext, R.raw.screen_vert);
        //获取片元着色器资源文件str
        String fPositionStr = ShaderUtils.getRawResource(mContext, R.raw.screen_frag);
        //获取源程序
        program = ShaderUtils.createProgram(vPositionStr, fPositionStr);
        //获取顶点着色器属性
        vPosition = GLES20.glGetAttribLocation(program, "vPosition");
        //获取片元着色器属性
        fPosition = GLES20.glGetAttribLocation(program, "fPosition");

        //设置支持文字透明
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);

        int[] vbo_s = new int[1];
        GLES20.glGenBuffers(1, vbo_s, 0);
        vboId = vbo_s[0];
        Log.d("TAG", "onSurfaceCreated: vbo_s "+vbo_s[0]);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboId);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, vertexData.length * 4 + fragmentData.length * 4, null, GLES20.GL_STATIC_DRAW);
        GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER, 0, vertexData.length * 4, vertexBuffer);
        GLES20.glBufferSubData(GLES20.GL_ARRAY_BUFFER, vertexData.length * 4, fragmentData.length * 4, fragBuffer);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

        textureIds = new int[1];
        GLES20.glGenTextures(1, textureIds, 0);
        Log.d("TAG", "onSurfaceCreated: textureIds "+textureIds[0]);
        //绑定数据
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureIds[0]);
        //设置纹理环绕方式
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
        //设置纹理的过滤方式
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        //解绑纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        //获取图标的纹理id
        imageTextureId = TextureUtils.createImageTexture(mContext, R.mipmap.bjwd);
        Bitmap bitmap = TextureUtils.createTextImage("冷月葬花魂", 25, "#ff0000", "#00000000", 10);
        //获取文字的纹理id
        textTextureId = TextureUtils.loadBitmapTexture(bitmap);
        //回收bitmap
        bitmap.recycle();
    }

    @Override
    public void onSurfaceChanged(int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame() {
        //清屏
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLES20.glClearColor(0f, 0.3f, 0.5f, 0);
        //使用源程序
        GLES20.glUseProgram(program);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboId);
        //顶点使能
        GLES20.glEnableVertexAttribArray(vPosition);
        //顶点赋值
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, 0);
        //片元使能
        GLES20.glEnableVertexAttribArray(fPosition);
        //片元赋值
        GLES20.glVertexAttribPointer(fPosition, 2, GLES20.GL_FLOAT, false, 8, vertexData.length * 4);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureIds[0]);
//        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        //绑定
//        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureIds[0]);
        //获取图片
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.byg);
        //图片绑定到GL_TEXTURE_2D
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        //回收
        bitmap.recycle();
        //画图
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
        //解绑
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        //图片水印---下面的这些代码让我不知道我上面这一段是做什么用的
        //使能
//        GLES20.glEnableVertexAttribArray(vPosition);  这里不使能也是可以的，不知道是不是上面已经做过使能这一步了，虽然解绑了，但是纹理状态还是可用的
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, 32);
//        GLES20.glEnableVertexAttribArray(fPosition);
        GLES20.glVertexAttribPointer(fPosition, 2, GLES20.GL_FLOAT, false, 8, vertexData.length * 4);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, imageTextureId);
        //绘制图片
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        //文字水印
//        GLES20.glEnableVertexAttribArray(vPosition);
        GLES20.glVertexAttribPointer(vPosition, 2, GLES20.GL_FLOAT, false, 8, 64);
//        GLES20.glEnableVertexAttribArray(fPosition);
        GLES20.glVertexAttribPointer(fPosition, 2, GLES20.GL_FLOAT, false, 8, vertexData.length * 4);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textTextureId);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
    }
}

