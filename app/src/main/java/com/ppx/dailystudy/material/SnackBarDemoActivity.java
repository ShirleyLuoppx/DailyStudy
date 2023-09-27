package com.ppx.dailystudy.material;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.ppx.dailystudy.R;
import com.ppx.dailystudy.common.BaseActivity;
import com.ppx.dailystudy.common.CommonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Shirley
 * @Date：2023/9/26
 * @Desc：SnackBar 的简单使用
 */
public class SnackBarDemoActivity extends BaseActivity {

    @Override
    protected int initLayout() {
        return R.layout.activity_snackbar_demo;
    }

    @Override
    protected void initView() {

    }

    public void simpleSnackBarUse(View view) {
        Snackbar.make(view, "click snackBar", Snackbar.LENGTH_SHORT).show();
    }

    public void updateSnackBarTextColor(View view) {
        Snackbar snackbar = Snackbar.make(view, "click snackBar", Snackbar.LENGTH_SHORT);
        snackbar.setTextColor(getResources().getColor(R.color.colorAsGreen));
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorBlue5D6A7D));
        snackbar.show();
    }

    public void setActionUse(View view) {
        Snackbar snackbar = Snackbar.make(view, "click snackBar", Snackbar.LENGTH_SHORT);
        snackbar.setAction("snakeBar Action", view1 -> Toast.makeText(SnackBarDemoActivity.this, "click snackbar action", Toast.LENGTH_SHORT).show());
        snackbar.show();
    }

    public void setActionTextColor(View view) {
        Snackbar snackbar = Snackbar.make(view, "click snackBar", Snackbar.LENGTH_SHORT);
        snackbar.setAction("snakeBar setAction", v -> Toast.makeText(SnackBarDemoActivity.this, "click snackbar action", Toast.LENGTH_SHORT).show()).setActionTextColor(getResources().getColor(R.color.colorRedFF55)).show();
    }

    public void addCallBack(View view) {
        Snackbar.make(view, "click snackBar", Snackbar.LENGTH_SHORT).setAction("click action", v -> {
        }).addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                Toast.makeText(SnackBarDemoActivity.this, "onDismissed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShown(Snackbar transientBottomBar) {
                super.onShown(transientBottomBar);
                Toast.makeText(SnackBarDemoActivity.this, "onShown", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

}

