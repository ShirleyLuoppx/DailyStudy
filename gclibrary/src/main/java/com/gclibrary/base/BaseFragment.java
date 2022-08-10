package com.gclibrary.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gclibrary.R;
import com.gclibrary.ui.BaseTitle;
import com.gclibrary.ui.EmptyView;
import com.gclibrary.view.customdialog.DialogMaker;
import com.hwangjr.rxbus.RxBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/2/21 0021.
 */

public abstract class BaseFragment extends Fragment implements DialogMaker.DialogCallBack {
    protected Dialog dialog;
    public Activity context;
    protected View rootView = null;
    public BaseTitle baseTitle;
    private FrameLayout flBaseContent;
    public EmptyView emptyView;
    protected Handler handler = new Handler();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        RxBus.get().register(this);
    }

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
        Intent intent = new Intent(context, tClass);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.item_common_base, container, false);
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        initBaseView();
        if (getLayoutId() != 0) {
            setLyContent(getLayoutId());
        }
        return rootView;
    }

    /**
     * 基布局
     */
    public void initBaseView() {
        if (baseTitle != null) return;
        baseTitle = rootView.findViewById(R.id.v_title);
        baseTitle.setVisibility(View.GONE);
        flBaseContent = rootView.findViewById(R.id.fl_base_content);
        emptyView = new EmptyView(context);
        emptyView.setOnClick(new EmptyView.OnClick() {
            @Override
            public void setRestartData() {
                reLoadData();
            }
        });
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
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            initView();
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        rootView = null;
        RxBus.get().unregister(this);
    }

    public void reLoadData() {

    }

    /**
     * 弹出对话框
     */
    public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommonAlertDialog(context, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag, true);
        }
        return dialog;
    }

    public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag, boolean isCenter) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommonAlertDialog(context, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag, isCenter);
        }
        return dialog;
    }

    public Dialog showAlertDialog(String msg, String[] btns, DialogMaker.DialogCallBack callBack) {
        return DialogMaker.showCommonAlertDialog(context, "提示", msg, btns, callBack, true, true, "", true);
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


}
