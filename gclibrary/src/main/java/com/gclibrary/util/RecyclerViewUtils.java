package com.gclibrary.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2019/6/5 0005.
 */

public class RecyclerViewUtils {

    private boolean move;
    private int movePostion;
    private RecyclerView recyclerView;

    public RecyclerViewUtils(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerViewListener());
    }

    public void moveToPosition(int movePostion) {
        this.movePostion = movePostion;
        int firstPosition = 0;
        int lastPosition = 0;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            firstPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            lastPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            firstPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }

        if (movePostion <= firstPosition) {
            recyclerView.scrollToPosition(movePostion);
        } else if (movePostion <= lastPosition) {
            int top = recyclerView.getChildAt(movePostion - firstPosition).getTop();
            recyclerView.scrollBy(0, top);
        } else {
            recyclerView.scrollToPosition(movePostion);
            move = true;
        }
    }

    class RecyclerViewListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move) {
                move = false;
                int firstPosition = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    firstPosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                }
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    firstPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                }
                int position = movePostion - firstPosition;
                if (0 <= position && position < recyclerView.getChildCount()) {
                    int top = recyclerView.getChildAt(position).getTop();
                    recyclerView.scrollBy(0, top);
                }
            }
        }
    }
}
