package com.gclibrary.ui.rvlist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.gclibrary.R;
import com.gclibrary.ui.EmptyView;
import com.gclibrary.ui.rvlist.view.JRecyclerView;
import com.gclibrary.util.ScrUtils;
import com.gclibrary.util.ViewUtil;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

/**
 * 封装列表刷新加载
 * Created by JackMar on 2017/2/28.
 * 邮箱：1261404794@qq.com
 */

public class RefreshUtil implements JRecyclerView.JRecyclerViewListener {

    private Context context;
    //recyclerView列表
    private JRecyclerView mRecyclerView;

    //组合刷新监听
    private IOnRefreshListener onRefreshListener;
    //是否是第一次刷新
    private boolean isFristRefresh = true;

    private boolean enableRfresh = true;
    private int pageSize = 10;


    public RefreshUtil(Context context, JRecyclerView mRecyclerView) {
        this.context = context;
        this.mRecyclerView = mRecyclerView;
        initRecylerView(true);
    }

    /**
     * 初始化recyclerView
     */
    private void initRecylerView(boolean needRefresh) {
        if (mRecyclerView != null) {
            //设置线性布局
            mRecyclerView.setLinearLayout();
            //设置recycleview下拉刷新不能用
            mRecyclerView.setSwipeRefreshEnable(needRefresh);
            mRecyclerView.setPullRefreshEnable(needRefresh);
            mRecyclerView.setOnRefreshListener(this);
        }
    }

    /**
     * 设置默认的分割线
     */
    public void setDefaultDivider() {
        if (mRecyclerView != null) {
            //设置分割线
            mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL, 1, 0, 0, context.getResources().getColor(R.color.view_bg)));
        }
    }

    /**
     * 设置默认的分割线
     */
    public void setHDivider(int height, int color) {
        if (mRecyclerView != null) {
//            int h = ViewUtil.dip2px(context, height);
            //设置分割线
            mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL, (int) ScrUtils.dpToPx(context, height), 0, 0, context.getResources().getColor(color)));
        }
    }

    /**
     * 设置默认的分割线
     */
    public void setWDivider(int height, int color) {
        if (mRecyclerView != null) {
//            int h = ViewUtil.dip2px(context, height);
            //设置分割线
            mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.VERTICAL, ScrUtils.getRealWidth(context, height), 0, 0, context.getResources().getColor(color)));
        }
    }

    /**
     * 设置默认的分割线
     */
    public void setDefaultWidthDivider() {
        if (mRecyclerView != null) {
            //设置分割线
            mRecyclerView.addItemDecoration(new RecycleViewDivider(context, LinearLayoutManager.HORIZONTAL, 1, ViewUtil.dip2px(context, 15), ViewUtil.dip2px(context, 15), context.getResources().getColor(R.color.view_bg)));
        }
    }

    /**
     * 启动刷新
     */
    public void showRcListRefresh() {
        if (mRecyclerView != null && isFristRefresh) {
            mRecyclerView.setRefreshing(true);
            onRefresh();
            isFristRefresh = false;
        }
    }

    public void showRcListRefresh2() {
        if (mRecyclerView != null) {
            mRecyclerView.setRefreshing(true);
            onRefresh();
        }
    }


    /**
     * RecyclerView是否需要加载
     *
     * @param enable
     */
    private boolean enable = true;

    public void enableLoad(boolean enable) {
        this.enable = enable;
        if (mRecyclerView != null) {
            mRecyclerView.setLoadEnable(enable);
        }
    }

    /**
     * RecyclerView是否需要加载
     *
     * @param enable
     */
    public void enableRefresh(boolean enable) {
        enableRfresh = enable;
        if (mRecyclerView != null) {
            //设置recycleview下拉刷新不能用
            mRecyclerView.setSwipeRefreshEnable(enable);
            mRecyclerView.setPullRefreshEnable(enable);
        }
    }

    /**
     * 设置刷新监听
     *
     * @param onRefreshListener
     */
    public void setOnRefreshListener(IOnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    /************************
     * RecyclerView 刷新加载的回掉
     ********************************************************/
    @Override
    public void onRefresh() {
        if (onRefreshListener != null) {
            onRefreshListener.onRefresh();
        }

    }

    @Override
    public void onLoadMore() {
        if (onRefreshListener != null) {
            onRefreshListener.onLoad();
        }
    }


    /**
     * 完成刷新或者加载
     */
    private int total;

    public void completeRefreshAndLoad() {
        if (mRecyclerView != null) {
            mRecyclerView.setRefreshCompleted();
            mRecyclerView.setRefreshing(false);
        }
        enableRefresh(enableRfresh);
        int otherCount = 0;//判断是否增加了头部或尾部
        if ((mRecyclerView.getRecyclerView().getAdapter() instanceof HeaderAndFooterWrapper)) {
            HeaderAndFooterWrapper adapter = (HeaderAndFooterWrapper) mRecyclerView.getRecyclerView().getAdapter();
            otherCount = adapter.getFootersCount() + adapter.getHeadersCount();
        }
        int currentCount = mRecyclerView.getRecyclerView().getAdapter().getItemCount() - otherCount;
        //处理上拉加载根据分页判断是否需要继续加载
        if ((total == 0 && currentCount % pageSize != 0) || (total != 0 && total == currentCount)) {
            if (mRecyclerView != null) {
                mRecyclerView.setLoadEnable(false);
            }
        } else {
            if (mRecyclerView != null && enable) {
                mRecyclerView.setLoadEnable(true);
            }
        }
//        if (currentCount == 0 && emptyView != null) {
//            emptyView.setEmpty();
//        }
        mRecyclerView.showEmptyView();
    }


    public JRecyclerView getmRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 当pagesize不固定是根据内容总数分页
     */
    public void setContentCount(int total) {
        this.total = total;
    }

    /**
     * 设置分页大小默认为10
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置加载默认之后的默认布局
     */
    public void setEmptyView(View emptyView) {
        mRecyclerView.setEmptyView(emptyView);
    }

}
