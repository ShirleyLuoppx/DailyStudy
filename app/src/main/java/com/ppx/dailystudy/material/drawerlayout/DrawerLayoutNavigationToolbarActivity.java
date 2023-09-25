package com.ppx.dailystudy.material.drawerlayout;

import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

import butterknife.BindView;

/**
 * @Author Shirley
 * @Date：2023/9/23
 * @Desc： DrawerLayout + Navigation + ToolBar
 */
public class DrawerLayoutNavigationToolbarActivity extends BaseActivity {

    @BindView(R.id.navigation_toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.drawerlayout_navigation_toolbar)
    DrawerLayout drawerLayoutNavigationToolbar;

    @Override
    protected int initLayout() {
        return R.layout.activity_drawerlayout_navigation_toolbar;
    }

    @Override
    protected void initView() {
        toolbar.setNavigationOnClickListener(view -> finish());

        navigation.setNavigationItemSelectedListener(item -> {
            Toast.makeText(DrawerLayoutNavigationToolbarActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return false;
        });

        //设置toolbar左侧  ["三" —— "←"]  的效果
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayoutNavigationToolbar,toolbar,R.string.open,R.string.close);
        toggle.syncState();
        drawerLayoutNavigationToolbar.addDrawerListener(toggle);
    }
}

