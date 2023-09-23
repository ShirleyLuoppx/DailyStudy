package com.ppx.dailystudy.material.drawerlayout;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

import butterknife.OnClick;

/**
 * @Author Shirley
 * @Date：2023/9/23
 * @Desc： Drawerlayout + ToolBar
 */
public class DrawerLayoutToolBarActivity extends BaseActivity {


    @Override
    protected int initLayout() {
        return R.layout.activity_drawerlayout_toolbar;
    }

    @Override
    protected void initView() {
    }

    @OnClick(R.id.toolbar)
    void submit() {
        finish();
    }
}

