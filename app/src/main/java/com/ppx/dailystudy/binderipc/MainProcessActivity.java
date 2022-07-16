package com.ppx.dailystudy.binderipc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2022/7/16 17:29
 * @Description: 运行在主进程的界面
 */
public class MainProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_process);

        findViewById(R.id.ben_show_second_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainProcessActivity.this, SecondActivity.class));
            }
        });

        findViewById(R.id.ben_show_third_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainProcessActivity.this, ThirdActivity.class));
            }
        });

    }
}
