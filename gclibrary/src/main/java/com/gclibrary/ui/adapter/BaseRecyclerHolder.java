package com.gclibrary.ui.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by guangchuan on 2016/6/16.
 */
public abstract class BaseRecyclerHolder<T>implements View.OnClickListener {
    protected View view;
    protected T data;
    protected List<T> datas;
    protected boolean isLoad=true;
    protected Context context;
    protected int position;


    public BaseRecyclerHolder(Context context,View view) {
        this.context=context;
        this.view=view;
        initView();
        ButterKnife.bind(this, view);
    }

    /**
     * 设置数据
     *
     * @param data
     * @param position
     */
    public void setData(T data, int position, List<T> datas,boolean isLoad) {
        this.data = data;
        this.datas=datas;
        this.position=position;
        this.isLoad=isLoad;
        refreshView();
    }

    public void setData(T data){
        this.data = data;
        refreshView();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 当holder中的view显示在界面
     *
     * @return
     */
    public View getContentView() {
        return view;
    }

    /**
     * 刷新view
     */
    protected abstract void refreshView();

    /**
     * 初始化view
     *
     * @return
     */
    protected abstract void initView();

}
