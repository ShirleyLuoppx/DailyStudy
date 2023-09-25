package com.ppx.dailystudy.material.drawerlayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrawerLayoutMainActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = "DrawerLayoutMainActivity";

    @BindView(R.id.btn_simple)
    Button btn_simple;
    @BindView(R.id.btn_simple_and_toolbar)
    Button btn_simple_and_toolbar;
    @BindView(R.id.btn_simple_navigation_toolbar)
    Button btn_simple_navigation_toolbar;
    @BindView(R.id.btn_simple_navigation_toolbar_immersive)
    Button btn_simple_navigation_toolbar_immersive;
    @BindView(R.id.btn_simple_navigation_imitate_qq)
    Button btn_simple_navigation_imitate_qq;


    @Override
    protected int initLayout() {
        return R.layout.activity_drawerlayout_main;
    }

    @Override
    protected void initView() {
        btn_simple.setOnClickListener(this);
        btn_simple_and_toolbar.setOnClickListener(this);
        btn_simple_navigation_toolbar.setOnClickListener(this);
        btn_simple_navigation_toolbar_immersive.setOnClickListener(this);
        btn_simple_navigation_imitate_qq.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                Log.d(TAG, "onClick: clickkkkkkkkkkkkk1");
                startActivity(new Intent(this, DrawerLayoutSimpleActivity.class));
                break;
            case R.id.btn_simple_and_toolbar:
                Log.d(TAG, "onClick: clickkkkkkkkkkkkk2");
                startActivity(new Intent(this, DrawerLayoutToolBarActivity.class));
                break;
            case R.id.btn_simple_navigation_toolbar:
                Log.d(TAG, "onClick: clickkkkkkkkkkkkk3");
                startActivity(new Intent(this, DrawerLayoutNavigationToolbarActivity.class));
                break;
            case R.id.btn_simple_navigation_toolbar_immersive:
                Log.d(TAG, "onClick: clickkkkkkkkkkkkk4");
                startActivity(new Intent(this, DrawerNavToolbarImmersiveActivity.class));
                break;
            case R.id.btn_simple_navigation_imitate_qq:
                Log.d(TAG, "onClick: clickkkkkkkkkkkkk5");
                startActivity(new Intent(this, DrawerNaviToolBarQqActivity.class));
                break;
        }
    }
}
