package com.ppx.dailystudy.hencoder.avtivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.hencoder.homework.HenCoderViewHomeWork1Fragment;

/**
 * @Author: LuoXia
 * @Date: 2021/3/31 16:51
 * @Description:自定义view的main类
 */
public class HenCoderViewActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout baseFrameLayout;
    private Button btnHenCoderViewFillType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hencoder_view);

        initView();
    }

    @Override
    public void onClick(View view) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()) {
            case R.id.btn_hencoder_view_fill_type:
                transaction.replace(R.id.base_framelayout,new HenCoderViewFillTypeFragment());
                break;
            case R.id.btn_hencoder_homework1:
                transaction.replace(R.id.base_framelayout,new HenCoderViewHomeWork1Fragment());
                break;
            default:
                break;
        }

        transaction.commit();
    }

    private void initView() {
        btnHenCoderViewFillType = findViewById(R.id.btn_hencoder_view_fill_type);
        btnHenCoderViewFillType.setOnClickListener(this);
        baseFrameLayout = findViewById(R.id.base_framelayout);
    }
}
