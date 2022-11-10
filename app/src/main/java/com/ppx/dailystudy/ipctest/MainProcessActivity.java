package com.ppx.dailystudy.ipctest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @Author: LuoXia
 * @Date: 2022/7/16 17:29
 * @Description: 运行在主进程的界面
 */
public class MainProcessActivity extends AppCompatActivity {

    private static final String TAG = "MainProcessActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_process);


        click();
        verifyMultiProcess();
        serializableUser();
    }

    /**
     * 实现User类的序列化和反序列化
     */
    private void serializableUser() {
        try {
            //序列化
            UserSerializable userSerializable = new UserSerializable(1, "ppx", "大数据");

            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cache.txt"));
            outputStream.writeObject(userSerializable);
            outputStream.close();
            Log.d(TAG, "serializableUser: user" + userSerializable + "," + userSerializable.toString());

            //反序列化
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("cache.txt"));
            UserSerializable newUserSerializable = (UserSerializable) inputStream.readObject();
            inputStream.close();
            Log.d(TAG, "serializableUser: newUser" + newUserSerializable + "," + newUserSerializable.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }


    /**
     * 点击方法
     */
    private void click() {
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

    /**
     * 用于验证A进程修改完变量后，B进程能否实时读取到修改后的值
     */
    private void verifyMultiProcess() {
        UserManager.num = 1;
        Log.d(TAG, "onCreate: num = " + UserManager.num);
    }
}
