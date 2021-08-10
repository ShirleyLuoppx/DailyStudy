package com.ppx.dailystudy.wutong;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ppx.dailystudy.R;

import java.util.ArrayList;
import java.util.List;

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

        testSystemCopy();
//        testSystemCopyErr();
    }

    public static void main(String[] args) {
        shiftPlace();
    }

    /**
     * 关于java移位
     * 之前在kotlin里面写了移位 ： value.shl/shr (3) --> value 乘以/除以 2的3次方
     */
    private static void shiftPlace() {
        //3 * 2的3次方 == 24
        int a = 3 << 3;
        //24 / 2的3次方 == 3
        int b = 24 >> 3;

        int c = (byte) (2 ^ 24 >> 24 & 0xFF);
        int d = (byte) (2 ^ 16 >> 16 & 0xFF);
        int e = (byte) (256 >> 8 & 0xFF);// 256/2^8 =1    1-->0000 0001   0xFF

        System.out.println("移位后的值：\n" + a + "\n" + b + "\n" + c + "---" + d + "---" + e);
    }

    public static void testSystemCopy() {
        /**
         * Object src
         * int  srcPos
         * Object dest
         * int destPos
         * int length
         */
        String str = "678";
        int number = 102378978;
        byte[] bytes;
        byte[] bytes1 = new byte[3];
        bytes = intToByteArray(number);

        for (byte b : bytes1) {
            Log.d("TAG", "testSystemCopy: 添加前size：" + b);
        }
        System.arraycopy(bytes, 0, bytes1, 0, 2);
        for (byte b : bytes1) {
            Log.d("TAG", "testSystemCopy: 添加后size：" + b);
        }
    }

    private void testSystemCopyErr() {
        List<String> stringList = new ArrayList<>();
        stringList.add("我");
        stringList.add("我");
        stringList.add("我");
        stringList.add("我");
        stringList.add("我");

        List<String> destList = new ArrayList<>();
        Log.d(TAG, "testSystemCopyErr: copy前：" + destList.size());
        System.arraycopy(stringList, 0, destList, 0, 5);
        Log.d(TAG, "testSystemCopyErr: copy后：" + destList.size());
    }

    public static byte[] intToByteArray(int value) {
        int i = (byte) (value >> 24 & 0xFF);
        int j = (byte) (value >> 16 & 0xFF);
        int k = (byte) (value >> 8 & 0xFF);

        return new byte[]{(byte) (value & 0xFF), (byte) k, (byte) j, (byte) i};
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
