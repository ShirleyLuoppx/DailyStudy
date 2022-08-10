package com.gclibrary.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gclibrary.R;
import com.gclibrary.SpUtils;
import com.gclibrary.manager.XActivityManager;
import com.gclibrary.ui.BaseTitle;
import com.gclibrary.ui.EmptyView;
import com.gclibrary.util.Density;
import com.gclibrary.util.LogUtils;
import com.gclibrary.util.StatusBarUtil;
import com.gclibrary.view.ToastViewResult;
import com.gclibrary.view.customdialog.DialogMaker;
import com.hwangjr.rxbus.RxBus;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2019/2/21 0021.
 */

public abstract class BaseActivity extends FragmentActivity implements DialogMaker.DialogCallBack, EasyPermissions.PermissionCallbacks {
    protected Activity context;
    private Unbinder unbinder;
    public BaseTitle baseTitle;
    private FrameLayout flBaseContent;
    private LinearLayout llRoot;
    public EmptyView emptyView;
    protected Dialog dialog;
    public Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        XActivityManager.getInstance().addToActivityList(this);
        LogUtils.e("-----------当前文件：" + getClass().getName());
        RxBus.get().register(this);
        setContentView(R.layout.item_common_base);
        initBaseView();
        baseTitle.setVisibility(View.GONE);
        if (getLayoutId() != 0) {
            setLyContent(getLayoutId());
        }
        unbinder = ButterKnife.bind(this);
        try {
            initView();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
            showAlertDialog("出现异常:"+e.getMessage(),new String[]{"确定"},null);
        }
    }

    /**
     * 基布局
     */
    public void initBaseView() {
        if (baseTitle != null) return;
        llRoot = findViewById(R.id.ll_root);
        baseTitle = findViewById(R.id.v_title);
        flBaseContent = findViewById(R.id.fl_base_content);
        emptyView = new EmptyView(this);
        emptyView.setOnClick(new EmptyView.OnClick() {
            @Override
            public void setRestartData() {
                reLoadData();
            }
        });
    }

    public void setActivityBgColor(int color) {
        llRoot.setBackgroundResource(color);
    }

    /**
     * 具体布局内容
     */
    public void setLyContent(int layoutId) {
        View view = View.inflate(context, layoutId, null);
        flBaseContent.removeAllViews();
        view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        flBaseContent.addView(view);
        flBaseContent.addView(emptyView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//大于19
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//大于21
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

//        StatusBarUtil.setTranslucentForImageView(this, 0, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//大于23
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    public void launch(Class tClass) {
        launch(tClass, null);
    }

    public void launch(Class tClass, Bundle bundle) {
        Intent intent = new Intent(context, tClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    public void launchResult(Class tClass, int requestCode) {
        launchResult(tClass, requestCode, null);
    }

    public void launchResult(Class tClass, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, tClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    public Bundle getBundle() {
        return getIntent().getExtras();
    }

    public void setBundleBack(Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        setResult(Activity.RESULT_OK, intent);
    }

    @Override
    protected void onDestroy() {
        RxBus.get().unregister(this);
        XActivityManager.getInstance().removeToActivityList(this);
        unbinder.unbind();
        super.onDestroy();
    }

    public void reLoadData() {

    }

    public boolean requestPermission(String[] Permissions, String des, int requestCode) {
        if (!EasyPermissions.hasPermissions(this, Permissions)) {
            EasyPermissions.requestPermissions(this, des, requestCode,
                    Permissions);
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommonAlertDialog(this, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag, true);
        }
        return dialog;
    }

    public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag, boolean isCenter) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommonAlertDialog(this, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag, isCenter);
        }
        return dialog;
    }

    public Dialog showAlertDialog(String msg, String[] btns, DialogMaker.DialogCallBack callBack) {
        return DialogMaker.showCommonAlertDialog(this, "温馨提示", msg, btns, callBack, true, true, "", true);
    }

    public Dialog showWaitDialog(String msg, boolean isCanCancelabel, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommenWaitDialog(this, msg, this, isCanCancelabel, tag);
        }
        return dialog;
    }

    public void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    /**
     * 通用提示
     */
    public void commonDialog(String str) {
        commonDialog(str, false);
    }

    /**
     * 通用提示
     */
    public void commonDialog(String str, boolean isTwo) {
        showAlertDialog("提示", str, isTwo ? new String[]{"确定", "取消"} : new String[]{"确定"}, true, true, "");
    }

    @Override
    public void onButtonClicked(Dialog dialog, int position, Object tag) {

    }

    @Override
    public void onCancelDialog(Dialog dialog, Object tag) {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showKeyboard(false);
    }

    public void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(getCurrentFocus(), 0);
            }
        } else {
            if (getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    @Override
    public Resources getResources() {//还原字体大小
        Resources res = super.getResources();
        Configuration configuration = res.getConfiguration();
        if (configuration.fontScale != 1.0) {//fontScale要缩放的比例
            configuration.fontScale = 1.0f;
            res.updateConfiguration(configuration, res.getDisplayMetrics());
        }
        return res;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Density.setDefault(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Density.setDefault(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                SpUtils.getInstance().setCustomUrl("lastonTouchEvent", System.currentTimeMillis());
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 展示成功提示
     */
    public void showSuccessToast(String str){
        if(TextUtils.isEmpty(str)) return;
        ToastViewResult.makeTextSuccess(context,str).show();
    }

    public void showFailToast(String str){
        if(TextUtils.isEmpty(str)) return;
        ToastViewResult.makeTextFail(context,str).show();
    }

}
