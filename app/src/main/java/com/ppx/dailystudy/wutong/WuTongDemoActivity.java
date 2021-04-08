package com.ppx.dailystudy.wutong;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2021/4/1 10:20
 * @Description: 梧桐车联相关测试demo
 */
public class WuTongDemoActivity extends AppCompatActivity {

    private String TAG = "WuTongDemoActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wutong_demo);

        Log.d(TAG, "onCreate: " + getResources().getConfiguration().locale.getLanguage());

        int delayMills = 10 * 000;
        Log.d(TAG, "onCreate: delayMills:" + delayMills);


    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
        }
    };

    public void testPostDelay() {
        handler.postDelayed(this::demo, 1000);
    }

    private String demo() {
        return "";
    }


}
