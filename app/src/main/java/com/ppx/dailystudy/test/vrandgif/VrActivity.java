package com.ppx.dailystudy.test.vrandgif;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

public class VrActivity extends AppCompatActivity {
    private VrGifView mGif;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr);


        mGif = (VrGifView) findViewById(R.id.gif);
        mGif.setTouch(true);//是否 可触摸
        mGif.setDrag(true);//是否可拖拽
        mGif.setScale(true);//是否可伸缩
        mGif.setMoveMode(VrGifView.MODE_FAST);//触摸响应速度

    }
}
