package com.ppx.dailystudy.material.drawerlayout;

import android.graphics.Color;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;
import com.ppx.dailystudy.common.CommonUtils;

import butterknife.BindView;

/**
 * @Author Shirley
 * @Date：2023/9/25
 * @Desc：
 */
public class DrawerNavToolbarImmersiveActivity extends BaseActivity {

    @BindView(R.id.navigation_toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navigationView;
    @BindView(R.id.drawerlayout_navigation_toolbar)
    DrawerLayout drawerLayout;
    @BindView(R.id.constraint)
    ConstraintLayout constraintLayout;

    @Override
    protected int initLayout() {
        return R.layout.activity_drawerlayout_navigation_toolbar;
    }

    @Override
    protected void initView() {

        CommonUtils.setBarColor(this, Color.TRANSPARENT);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(item -> {
            Toast.makeText(DrawerNavToolbarImmersiveActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawers();
            return false;
        });
    }
}

