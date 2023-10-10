package com.ppx.dailystudy.test.vrandgif.stlrender.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.test.vrandgif.stlrender.utils.FragmentUtils;

/**
 * Created by gongw on 2018/10/15.
 */

public class StlRenderMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stlrender_activity_main);
        FragmentUtils.addFragment(getSupportFragmentManager(), new HomeFragment(), R.id.fl_container, false);
    }
}
