package com.ppx.dailystudy.test.vrandgif.stlrender.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;

/**
 * Fragment基类，封装了常用的生命周期回调，ButterKnife使用
 * Created by gw on 2017/6/30.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;

    protected abstract int getLayoutId();
    protected abstract void initView(View view, Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
//        ButterKnife.inject(this, view);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.reset(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }
}
