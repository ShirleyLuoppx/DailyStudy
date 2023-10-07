package com.ppx.dailystudy.material;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

import butterknife.BindView;

/**
 * @Author Shirley
 * @Date：2023/9/26
 * @Desc：ToolBar 简单使用
 */
public class ToolBarDemoActivity extends BaseActivity {

    @BindView(R.id.toolbar_simple)
    Toolbar toolbar_simple;

    @Override
    protected int initLayout() {
        return R.layout.activity_simple_toolbar;
    }

    @Override
    protected void initView() {
        toolbar_simple.setOnMenuItemClickListener(item -> {
            Toast.makeText(ToolBarDemoActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return false;
        });

        toolbar_simple.setNavigationOnClickListener(v -> finish());
    }
}

