package com.gclibrary.ui.rvlist;

import android.os.Handler;
import android.os.Message;

/**
 * Created by JackMar on 2017/6/29.
 * 邮箱：1261404794@qq.com
 */

public class CHandler extends Handler {
    public static int SHOW_PROGRESS = 1;
    public static int DISMISS_PROGRESS = 2;
    private RefreshUtil refreshUtil;

    public CHandler(RefreshUtil refreshUtil) {
        this.refreshUtil = refreshUtil;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case 1:
                refreshUtil.showRcListRefresh();
                break;
            case 2:
                refreshUtil.completeRefreshAndLoad();
                break;
        }
    }
}