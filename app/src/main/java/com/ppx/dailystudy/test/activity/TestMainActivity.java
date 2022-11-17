package com.ppx.dailystudy.test.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.test.service.TestService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class TestMainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);

        findViewById(R.id.btn_update_ui_in_child_thread).setOnClickListener(this::onClick);
        findViewById(R.id.btn_copy_to_clipboard).setOnClickListener(this::onClick);
        findViewById(R.id.btn_test_service).setOnClickListener(this::onClick);

        try {
            analysisByteArrays();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //解析  analysis byte数组类型的json串
    private void analysisByteArrays() throws JSONException {
        String str = "{\n" +
                "    \"service\":\"net\",\n" +
                "    \"subService\":\"internetShare\",\n" +
                "    \"command\":0,\n" +
                "    \"errorCode\":100000,\n" +
                "    \"data\":{\n" +
                "        \"gateway\":\"192.168.43.2\",\n" +
                "        \"dns\":\"192.168.43.1\"\n" +
                "    }\n" +
                "}";
        byte[] bytes = str.getBytes();

        String message = new String(bytes);
        //去掉多余的引号和转义字符
//        String substring = message.substring(1, message.length() - 1).replace("\\\"", "'").replace("\n\"","");
        Log.d("TAG", "analysisByteArrays: " + message);
        //转化为json对象
        JSONObject jsonObject = new JSONObject(message);
        int command = jsonObject.getInt("command");
        int errorCode = jsonObject.getInt("errorCode");
        JSONObject data = jsonObject.getJSONObject("data");
        String gateway = data.getString("gateway");
        String dns = data.getString("dns");


        Log.d("TAG", "analysisByteArrays:  " + jsonObject);
        Log.d("TAG", "analysisByteArrays:  " + command);
        Log.d("TAG", "analysisByteArrays:  " + errorCode);
        Log.d("TAG", "analysisByteArrays:  " + data);
        Log.d("TAG", "analysisByteArrays:  " + gateway);
        Log.d("TAG", "analysisByteArrays:  " + dns);
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