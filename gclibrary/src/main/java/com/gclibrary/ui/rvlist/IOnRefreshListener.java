package com.gclibrary.ui.rvlist;

/**
 * 封装刷新的回掉
 * Created by JackMar on 2016/11/9.
 * 邮箱：1261404794@qq.com
 */

public abstract interface IOnRefreshListener {

    /**
     * 刷新回掉
     *
     */
    public void onRefresh();

    /**
     * 加载回掉
     */
    public void onLoad();

}
