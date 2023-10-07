package com.ppx.dailystudy.common;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.ppx.dailystudy.MyApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Shirley
 * @Date：2023/9/25
 * @Desc： 工具类
 */
public class CommonUtils {

    public static void showShortMsg(String msg){
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
    public static void showLongMsg(String msg){
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 设置状态栏透明
     *
     * @param activity
     * @param color
     */
    public static void setBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            View decorView = win.getDecorView();
            win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏(4.4-5.0透明，5
            // .0以上半透明)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//android5.0以上设置透明效果
                win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //清除flag，为了android5.0以上也全透明效果
                //让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | option);
                win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                win.setStatusBarColor(color);//设置状态栏背景色
            }
        }
    }

    /**
     * @param size 数组大小
     * @return 生成一个从1到size的 内容数字随机的int数组
     */
    private static List<Integer> generateRandomNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            numbers.add(i + 1);
        }

        Collections.shuffle(numbers);

        return numbers;
    }


        public static void getRandomIntArray(int size) {
            int[] numbers = new int[size];

            for (int i = 0; i < size; i++) {
                numbers[i] = i + 1;
            }

            shuffleArray(numbers);

            for (int i = 0; i < size; i++) {
                System.out.print(numbers[i] + " ");

                if ((i + 1) % 5 == 0) {
                    System.out.println();
                }
            }
        }

        // Fisher-Yates shuffle algorithm
        private static void shuffleArray(int[] array) {
            int n = array.length;

            for (int i = 0; i < n - 1; i++) {
                int j = i + (int) (Math.random() * (n - i));
                //原来这种叫洗牌算法..
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }

}

