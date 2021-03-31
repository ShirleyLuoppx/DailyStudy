package com.ppx.dailystudy.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * Date: 2021/3/29 15:23
 * Description:自定义view的一个测试
 */
public class TestCustomizeViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_customize_view);

        findViewById(R.id.bt_fill_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestCustomizeViewActivity.this, HenCoderViewActivity.class));
            }
        });
    }
}
