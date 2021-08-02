package com.ppx.dailystudy.hencoder.avtivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.hencoder.fragment.PaintingSequenceFragment;
import com.ppx.dailystudy.hencoder.fragment.ViewCanvasClipFragment;
import com.ppx.dailystudy.hencoder.fragment.ViewPaintFragment;
import com.ppx.dailystudy.hencoder.fragment.ViewTextFragment;
import com.ppx.dailystudy.hencoder.homework.HenCoderViewHomeWork1Fragment;

/**
 * @Author: LuoXia
 * @Date: 2021/3/31 16:51
 * @Description:自定义view的main类
 */
public class HenCoderViewActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout baseFrameLayout;
    private TextView btnHenCoderViewFillType, btnHenCoderHomework1, btnHenCoderViewDemo, btnHenCoderViewPaint, btnViewText ,btnViewCanvasClip;
    private String tag = "";
    private String FILL_TYPE = "FILL_TYPE";
    private String PAINT = "PAINT";
    private String BASE_API = "BASE_API";
    private String TEXT = "TEXT";
    private String CANVAS = "CANVAS";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hencoder_view);

        initView();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment;
        switch (view.getId()) {
            case R.id.btn_hencoder_view_demo:
                tag = BASE_API;
                fragment = new ViewTextFragment(tag);
                transaction.add(R.id.base_framelayout, fragment);
                break;
            case R.id.btn_hencoder_view_fill_type:
                tag = FILL_TYPE;
                fragment = new ViewTextFragment(tag);
                transaction.add(R.id.base_framelayout, fragment);
                break;
            case R.id.btn_hencoder_homework1:
                transaction.replace(R.id.base_framelayout, new HenCoderViewHomeWork1Fragment());
                break;
            case R.id.btn_hencoder_view_paint:
                tag = PAINT;
                fragment = new ViewPaintFragment(tag);
                transaction.replace(R.id.base_framelayout, fragment);
            case R.id.btn_view_canvas_text:
                tag = TEXT;
                fragment = new ViewTextFragment(tag);
                transaction.replace(R.id.base_framelayout, fragment);
                break;
            case R.id.btn_view_canvas_clip:
                transaction.replace(R.id.base_framelayout, new ViewCanvasClipFragment());
                break;
            case R.id.btn_paint_sequence:
                transaction.replace(R.id.base_framelayout, new PaintingSequenceFragment());
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
        btnHenCoderViewPaint = findViewById(R.id.btn_hencoder_view_paint);
        btnHenCoderViewPaint.setOnClickListener(this);
        btnViewText = findViewById(R.id.btn_view_canvas_text);
        btnViewText.setOnClickListener(this);
        btnViewCanvasClip = findViewById(R.id.btn_view_canvas_clip);
        btnViewCanvasClip.setOnClickListener(this);
        baseFrameLayout = findViewById(R.id.base_framelayout);
    }

    /**
     * 隐藏fragment
     *
     * @param transaction
     * @param hideFragment
     */
    private void hideFragment(FragmentTransaction transaction, Fragment hideFragment) {
        if (hideFragment != null) {
            transaction.hide(hideFragment);
        }
    }
}
