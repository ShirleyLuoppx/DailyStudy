package com.ppx.dailystudy.material.drawerlayout;

import android.graphics.Color;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
public class DrawerNaviToolBarQqActivity extends BaseActivity {

    @BindView(R.id.navigation_qq)
    NavigationView navigation_qq;
    @BindView(R.id.drawerlayout_qq)
    DrawerLayout drawerlayout_qq;

    @Override
    protected int initLayout() {
        return R.layout.activity_drawerlayout_navigation_toolbar_qq;
    }

    @Override
    protected void initView() {
        CommonUtils.setBarColor(this, Color.TRANSPARENT);
        navigation_qq.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(DrawerNaviToolBarQqActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                drawerlayout_qq.closeDrawers();
                return false;
            }
        });
    }
}

