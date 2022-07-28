package com.ppx.dailystudy.test.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.test.fragment.BlankFragment;

/**
 * @Author: LuoXia
 * @Date: 2022/7/25 19:54
 * @Description: 关于在子线程更新ui的情况：
 *
 * 1、view在activity的onresume方法后会创建一个  ViewRootImpl 类，这个类里面会去调用 requestLayout()，而这个方法里面有一个checkThread方法，
 * 判断了当前更新ui操作所在的线程是否是主线程，如果不是则会崩溃。
 *
 * 由此，我们得出两个点：
 * a：在activity的onResume之后去子线程更新ui会崩，但是在resume及resume之前去子线程更新ui都是没有问题的；
 * b：子线程里面调用view.post 或者是 runOnUiThread 去更新ui也是不会报错的，因为这两方法最终都是调用了主线程的handler去更新ui的。
 *
 */
public class TestThreadActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread);
        textView = findViewById(R.id.tv_test);
        //1、这里不会崩，因为此时OnCreate还没结束，ViewRootImpl还没生成，也就还没到requestLayout，也就还没到checkThread，所以不会崩
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText("77777777");
//            }
//        }).start();

        //2、这里会挂，报错：Only the original thread that created a view hierarchy can touch its views.
        //因为是在fragment里面的子线程，就不是主线程了。
        findViewById(R.id.btn_show_fragment).setOnClickListener(v ->
                getFragmentManager().beginTransaction().replace(R.id.cl_fragment, new BlankFragment()).commit());

        //3、这里不会崩，因为runOnUiThread里面其实是调用了mHandler.post，而mHandler是主线程的handler，相当于将这个message又丢给了主线程去处理
        findViewById(R.id.btn_run_on_ui_thread).setOnClickListener(v ->
                new Thread(() -> runOnUiThread(() -> textView.setText("runOnUiThread"))).start());

    }

    @Override
    protected void onStart() {
        super.onStart();
        //4、这里不会崩，原因同onResume里面不会崩的原因一样
        new Thread(new Runnable() {
            @Override
            public void run() {
//                textView.setText("888888");
            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //5、这里不会崩，因为ViewRootImpl的创建是在resume之后的，所以在这里的子线程去做ui更新也是不会崩的。
        new Thread(new Runnable() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                textView.setText("6666661111111");
                textView.setBackgroundColor(R.color.colorBlack);
                textView.setWidth(100);
                textView.setHeight(50);
                textView.requestLayout();
            }
        }).start();
    }
}
