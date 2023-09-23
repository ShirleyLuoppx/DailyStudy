package com.ppx.dailystudy.material.drawerlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DrawerLayoutSimpleActivity extends BaseActivity {

    @BindView(R.id.tv_simple)
    TextView tv_simple;
    @BindView(R.id.drawerlayout_simple)
    DrawerLayout drawerlayout_simple;
    @BindView(R.id.btn_click)
    Button btn_click;

    @Override
    protected int initLayout() {
        return R.layout.activity_drawerlayout_simple;
    }

    @Override
    protected void initView() {
        btn_click.setOnClickListener(view -> {
            /**
             * 需要注意的是：
             * 1、如果通过openDrawer接口打开侧滑页面的时候，参数中的gravity必须跟布局页面中的layout_gravity对应，要不然会崩溃报错;
             * 2、openDrawer的时候默认开启动画
             *
             */
            drawerlayout_simple.openDrawer(GravityCompat.START, true);
        });
    }
}
