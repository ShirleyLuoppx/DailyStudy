package com.ppx.dailystudy.di.dagger.cook;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        Map<String, Boolean> menuList = new LinkedHashMap<>();
        menuList.put("酸辣土豆丝", true);
        menuList.put("小炒黄牛肉", true);
        menuList.put("粉蒸肉", false);
        menuList.put("蒜蓉生蚝", true);

        Menu menu = new Menu(menuList);
        Chef chef = new Chef(menu);
        Log.d("ppx", "onCreate: " + chef.cook());

    }
}

