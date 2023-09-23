package com.ppx.dailystudy.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author Shirley
 * @Date：2023/9/23
 * @Desc：
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Unbinder unbinder;

    protected abstract int initLayout();
    protected abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());

        unbinder = ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

