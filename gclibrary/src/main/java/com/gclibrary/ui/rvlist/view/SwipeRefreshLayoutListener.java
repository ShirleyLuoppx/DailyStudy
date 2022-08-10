package com.gclibrary.ui.rvlist.view;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by JackMar on 2017/3/10.
 * 邮箱：1261404794@qq.com
 */

public class SwipeRefreshLayoutListener implements SwipeRefreshLayout.OnRefreshListener {
    private JRecyclerView mJRecyclerView;

    public SwipeRefreshLayoutListener(JRecyclerView mJRecyclerView) {
        this.mJRecyclerView = mJRecyclerView;
    }

    @Override
    public void onRefresh() {
        if (!mJRecyclerView.isRefresh()&&!mJRecyclerView.isLoadMore()) {
            mJRecyclerView.setIsRefresh(true);
            mJRecyclerView.refresh();
        }
    }
}
