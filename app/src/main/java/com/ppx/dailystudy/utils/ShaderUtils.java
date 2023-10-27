package com.ppx.dailystudy.utils;

import static android.opengl.GLUtils.texImage2D;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author Shirley
 * @Date：2023/10/12
 * @Desc：
 */
public class ShaderUtils {
    private static String TAG = "ShaderUtils";

    /**
     * 根据rawId 加载对应着色器 shader 内容
     *
     * @param context
     * @param rawId
     * @return 对应shader的string字符串内容
     */
    public static String getRawResource(Context context, int rawId) {
        InputStream inputStream = context.getResources().openRawResource(rawId);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();

        String line = "";
        try {
            // 注意 readLine是读一行少一行，调用之后需要将数据 拿出来赋值，不用数据就少了...
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    /**
     * 创建源程序
     *
     * @param vertexSource 顶点数据
     * @param fragSource   片元数据
     * @return 源程序
     */
    public static int createProgram(String vertexSource, String fragSource) {
        //加载顶点着色器（这里相当于将点连成线）
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        //加载片元着色器
        int fragShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragSource);

        if (vertexShader != 0 && fragShader != 0) {
            //创建渲染程序 program
            int program = GLES20.glCreateProgram();
            //将顶点着色器添加到 渲染程序
            GLES20.glAttachShader(program, vertexShader);
            //将片元着色器添加到 渲染程序
            GLES20.glAttachShader(program, fragShader);
            //链接到源程序
            GLES20.glLinkProgram(program);
            return program;
        }
        return 0;
    }

    /**
     * 根据 shaderType 返回加载、编译、检测后的shader
     *
     * @param shaderType 着色器类型 GL_VERTEX_SHADER ：顶点 ；GL_FRAGMENT_SHADER：片元
     * @param source     shader字符串
     * @return shader ID
     */
    public static int loadShader(int shaderType, String source) {
        //创建shader
        int shader = GLES20.glCreateShader(shaderType);
        if (shader != 0) {
            //加载shader
            GLES20.glShaderSource(shader, source);
            //编译shader
            GLES20.glCompileShader(shader);
            int[] compile = new int[1];
            //检测shader是否编译通过
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compile, 0);
            if (GLES20.GL_TRUE != compile[0]) {
                Log.e(TAG, "loadShader: compile error " + compile[0]);
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
            //编译成功返回 shader ID
            return shader;
        } else {
            return 0;
        }
    }

    /**
     * 加载图片为 2D纹理
     *
     * @param context 上下文
     * @param rawID   资源ID
     * @return 生成的纹理
     */
    public static int createImageTexture(Context context, int rawID) {
        //生产一个纹理
        int[] textureIds = new int[1];
        GLES20.glGenTextures(1, textureIds, 0);
        //绑定为 2D纹理
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureIds[0]);
        //设置环绕模式
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_REPEAT);
        //设置过滤模式
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), rawID);
        //绑定 bitmap到 textureIds[0] 这个2D纹理上
        texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        //退出 纹理的设置，进入下一环节
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
        return textureIds[0];

    }


}

