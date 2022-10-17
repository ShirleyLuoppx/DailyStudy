package com.ppx.dailystudy.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.test.service.TestService;

public class TestMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        findViewById(R.id.btn_update_ui_in_child_thread).setOnClickListener(this::onClick);
        findViewById(R.id.btn_copy_to_clipboard).setOnClickListener(this::onClick);
        findViewById(R.id.btn_test_service).setOnClickListener(this::onClick);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update_ui_in_child_thread:
                startActivity(new Intent(this, TestThreadActivity.class));
                break;
            case R.id.btn_copy_to_clipboard:
                copyDataToClipBoard("clip data111");
                break;
            case R.id.btn_test_service:
                Intent intent = new Intent();
                intent.setPackage("com.wt.phonelink");
                intent.setAction("com.wt.phonelink.service_wake");
                startService(intent);
                break;
        }
    }

    private void copyDataToClipBoard(String data) {
        ClipData clipData = ClipData.newPlainText("label", data);
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(this, "拷贝成功", Toast.LENGTH_SHORT).show();
    }
}