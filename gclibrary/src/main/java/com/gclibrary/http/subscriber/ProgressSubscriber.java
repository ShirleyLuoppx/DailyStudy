package com.gclibrary.http.subscriber;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.provider.MediaStore;
import android.util.Log;


import com.gclibrary.R;
import com.gclibrary.base.BaseActivity;
import com.gclibrary.http.exception.ApiException;
import com.gclibrary.http.exception.AuthenticationException;
import com.gclibrary.ui.BaseTitle;
import com.gclibrary.ui.EmptyView;
import com.gclibrary.ui.rvlist.CHandler;
import com.gclibrary.ui.rvlist.RefreshUtil;
import com.gclibrary.util.NetWorkUtil;
import com.gclibrary.util.ToastUtils;
import com.gclibrary.view.customdialog.DialogMaker;
import com.gclibrary.view.customdialog.ProgressCircleDialogHandler;
import com.gclibrary.view.customdialog.ProgressDialogHandler;
import com.hwangjr.rxbus.RxBus;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * @Title
 * @Author JackMar
 * @Date 2017-03-10 17:14
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements DialogMaker.DialogCallBack {

    private IOnNextListener mIOnNextListener;
    private RefreshUtil refreshUtil;
    private CHandler mChandler;
    private boolean needCircle = true;
    private Context context;
    private EmptyView emptyView;
    private ProgressCircleDialogHandler mProgressDialogHandler;

    public ProgressSubscriber(Context context, IOnNextListener mIOnNextListener) {
        this(context, true, null, null, mIOnNextListener);
    }

    public ProgressSubscriber(Context context, boolean needCircle, IOnNextListener mIOnNextListener) {
        this(context, needCircle, null, null, mIOnNextListener);
    }

    public ProgressSubscriber(Context context, RefreshUtil refreshUtil, IOnNextListener mIOnNextListener) {
        this(context, false, refreshUtil, null, mIOnNextListener);
    }

    public ProgressSubscriber(Context context, EmptyView emptyView, IOnNextListener mIOnNextListener) {
        this(context, false, null, emptyView, mIOnNextListener);
    }

    public ProgressSubscriber(Context context, boolean needCircle, RefreshUtil refreshUtil, EmptyView emptyView, IOnNextListener mIOnNextListener) {
        this.mIOnNextListener = mIOnNextListener;
        this.refreshUtil = refreshUtil;
        this.context = context;
        this.needCircle = needCircle;
        this.emptyView = emptyView;
        if (refreshUtil != null) {
            mChandler = new CHandler(refreshUtil);
        }
        if (needCircle) {
            mProgressDialogHandler = new ProgressCircleDialogHandler(context, this, true);
        }
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null && needCircle) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null && needCircle) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }

        if (refreshUtil != null) {
            refreshUtil.getmRecyclerView().showEmptyView();
        }
    }

    private void completeRefreshAndLoad() {
        if (refreshUtil != null) {
            refreshUtil.completeRefreshAndLoad();
            mChandler.obtainMessage(CHandler.DISMISS_PROGRESS);
        }
    }

    private void completeShowContent() {
        if (emptyView != null) {
            emptyView.setShowContent();
        }
    }

    private void completeShowError() {
        if (emptyView != null) {
            emptyView.setError();
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        if (NetWorkUtil.isNetworkConnected(context)) {
            showProgressDialog();
            if (refreshUtil != null) {
                mChandler.obtainMessage(CHandler.SHOW_PROGRESS);
            }
            if (emptyView != null) {
                emptyView.setLoading();
            }
        } else {
            ToastUtils.showShort(context, R.string.net_break_fail);
            dismissProgressDialog();
            completeShowError();
            completeRefreshAndLoad();
            RxBus.get().post(new ApiException("-1", null));
            return;
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        completeShowContent();
        completeRefreshAndLoad();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        try {
            dismissProgressDialog();
            completeShowError();
            completeRefreshAndLoad();
            if (mIOnNextListener != null) {
                mIOnNextListener.onError(e);
            }
            Log.i("onError", e.getMessage());
            if (e instanceof SocketTimeoutException) {
                ((BaseActivity)context).showFailToast(context.getString(R.string.net_break_fail));
            } else if (e instanceof ConnectException) {
                ((BaseActivity)context).showFailToast(context.getString(R.string.net_break_fail));
            } else if (e instanceof ApiException) {
                ((BaseActivity)context).showFailToast(e.getMessage());
            } else if (e instanceof AuthenticationException) {
                RxBus.get().post(new AuthenticationException(""));
            } else {
                ((BaseActivity)context).showFailToast(e.getMessage());
            }

        } catch (Exception ex) {
            ((BaseActivity)context).showFailToast(context.getString(R.string.net_break_fail));
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mIOnNextListener != null) {
            mIOnNextListener.onNext(t);
        }
        dismissProgressDialog();
        completeShowContent();
        completeRefreshAndLoad();
    }


    @Override
    public void onButtonClicked(Dialog dialog, int position, Object tag) {

    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelDialog(Dialog dialog, Object tag) {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}