package com.ppx.dailystudy.test.seekbar;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;
import com.jaywei.PureVerticalSeekBar;
import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;

import butterknife.BindView;

/**
 * @Author Shirley
 * @Date：2023/9/25
 * @Desc：
 */
public class SeekBarActivity extends BaseActivity {

    private String TAG = "SeekBarActivity";

    @BindView(R.id.seekbar)
    VerticalSeekBar seekBar;
    @BindView(R.id.seekbar_circle)
    PureVerticalSeekBar mPureVerticalSeekBar_circle;
    @BindView(R.id.btn_click)
    Button btn_click;
    @BindView(R.id.btn_increase_progress)
    Button btn_increase_progress;
    @BindView(R.id.btn_decrease_progress)
    Button btn_decrease_progress;
    @BindView(R.id.my_vertical_seekbar)
    MyVerticalSeekBar my_vertical_seekbar;

    private int curProgress = 0;

    @Override
    protected int initLayout() {
        return R.layout.activity_seekbar;
    }

    @Override
    protected void initView() {

        /**
         * 这一个seekbar目前看来还是功能比较齐全的，能设置滑块图片、颜色、能设置滑杆颜色，滑杆宽度，但是在处理两端极值的时候不是很友好
         */
        mPureVerticalSeekBar_circle.setVertical_color(Color.YELLOW, Color.RED);
        mPureVerticalSeekBar_circle.setCircle_color(Color.BLUE);

        btn_increase_progress.setOnClickListener(view -> {
            if (curProgress <= 99) {
                curProgress++;
                seekBar.setProgress(curProgress);
            }
            Log.d(TAG, "initView: increase progress = " + curProgress);
        });

        btn_decrease_progress.setOnClickListener(view -> {
            if (curProgress >= 1) {
                curProgress--;
                seekBar.setProgress(curProgress);
            }
            Log.d(TAG, "initView: decrease progress = " + curProgress);
        });

        btn_click.setOnClickListener(view -> {
            if (mPureVerticalSeekBar_circle.getVisibility() == View.VISIBLE) {
                mPureVerticalSeekBar_circle.setVisibility(View.GONE);
            } else {
                mPureVerticalSeekBar_circle.setVisibility(View.VISIBLE);
            }
        });

        mPureVerticalSeekBar_circle.setOnSlideChangeListener(new PureVerticalSeekBar.OnSlideChangeListener() {
            @Override
            public void OnSlideChangeListener(View view, float progress) {
                Log.d(TAG, "OnSlideChangeListener: " + ((int) progress));
                curProgress = (int) progress;
            }

            @Override
            public void onSlideStopTouch(View view, float progress) {

            }
        });

        /**
         * VerticalSeekBar，这个seekbar 能设置背景，能设置滑块图片，能设置滑杆颜色，能设置滑块颜色
         */
//        seekBar.setRotationAngle(270);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "onProgressChanged: " + progress + "--" + fromUser);
                curProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStartTrackingTouch: ");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "onStopTrackingTouch: ");
            }
        });


        my_vertical_seekbar.setOnSeekBarChangeListener(new MyVerticalSeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(MyVerticalSeekBar VerticalSeekBar, int progress, boolean fromUser) {
                curProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(MyVerticalSeekBar VerticalSeekBar) {

            }

            @Override
            public void onStopTrackingTouch(MyVerticalSeekBar VerticalSeekBar) {

            }
        });

    }
}

