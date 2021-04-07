package com.ppx.dailystudy.hencoder.avtivity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.hencoder.fragment.HenCoderViewFragment;
import com.ppx.dailystudy.hencoder.homework.HenCoderViewHomeWork1Fragment;

/**
 * @Author: LuoXia
 * @Date: 2021/3/31 16:51
 * @Description:自定义view的main类
 */
public class HenCoderViewActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout baseFrameLayout;
    private TextView btnHenCoderViewFillType, btnHenCoderHomework1, btnHenCoderViewDemo;
    private String tag = "";
    private String FILL_TYPE = "FILL_TYPE";
    private String PAINT = "PAINT";
    private String BASE_API = "BASE_API";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hencoder_view);

        initView();
    }

    @Override
    public void onClick(View view) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        switch (view.getId()) {
            case R.id.btn_hencoder_view_fill_type:
                tag = FILL_TYPE;
                fragment = new HenCoderViewFragment(tag);
                transaction.add(R.id.base_framelayout, fragment);
                break;
            case R.id.btn_hencoder_view_paint:
                tag = PAINT;
                fragment = new HenCoderViewFragment(tag);
                transaction.add(R.id.base_framelayout, fragment);
                break;
            case R.id.btn_hencoder_view_demo:
                tag = BASE_API;
                fragment = new HenCoderViewFragment(tag);
                transaction.add(R.id.base_framelayout, fragment);
                break;
            case R.id.btn_hencoder_homework1:
                transaction.replace(R.id.base_framelayout, new HenCoderViewHomeWork1Fragment());
                break;
            default:
                break;
        }

        transaction.commit();
    }

    private void initView() {
        btnHenCoderViewFillType = findViewById(R.id.btn_hencoder_view_fill_type);
        btnHenCoderViewFillType.setOnClickListener(this);
        btnHenCoderHomework1 = findViewById(R.id.btn_hencoder_homework1);
        btnHenCoderHomework1.setOnClickListener(this);
        btnHenCoderViewDemo = findViewById(R.id.btn_hencoder_view_demo);
        btnHenCoderViewDemo.setOnClickListener(this);
        baseFrameLayout = findViewById(R.id.base_framelayout);
    }

    //隐藏fragment
    private void hideFragment(FragmentTransaction transaction, Fragment hideFragment) {
        if (hideFragment != null) {
            transaction.hide(hideFragment);
        }
    }
}
