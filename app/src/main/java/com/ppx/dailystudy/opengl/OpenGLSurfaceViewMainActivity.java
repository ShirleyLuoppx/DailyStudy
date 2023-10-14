package com.ppx.dailystudy.opengl;

import android.content.Intent;
import android.view.View;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;
import com.ppx.dailystudy.opengl.shape.MyTriangleActivity;

/**
 * @Author Shirley
 * @Date：2023/10/11
 * @Desc：
 */
public class OpenGLSurfaceViewMainActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_opengl_surface_view_main;
    }

    @Override
    protected void initView() {
    }

    public void clickBaseGlSurfaceView(View view) {
        startActivity(new Intent(this, MyGLSurfaceViewActivity.class));
    }

    public void clickCustomGlSurfaceView(View view) {
        startActivity(new Intent(this, CustomGLSurfaceViewActivity.class));
    }
    public void clickTriangleSurfaceView(View view) {
        startActivity(new Intent(this, MyTriangleActivity.class));
    }
}

