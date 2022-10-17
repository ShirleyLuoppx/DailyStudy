package com.ppx.dailystudy.test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2022/10/16 15:30
 * @Description:
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Button btnLeft;
    private Button btnRight;
    private RelativeLayout left;
    private RelativeLayout right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);

        drawerLayout = findViewById(R.id.drawerlayout);
        btnLeft = findViewById(R.id.left_btn);
        btnRight = findViewById(R.id.right_btn);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(left, true);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(right, false);
            }
        });
//        drawerLayout.openDrawer(GravityCompat.START);
    }
}
