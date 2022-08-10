package com.gclibrary.dialog;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.PopupWindow;

import com.gclibrary.R;
import com.gclibrary.util.ScrUtils;

public class FixedPopWindow extends PopupWindow {
    public FixedPopWindow(View view, int width, int height, boolean isOutSide) {
        super(view, width, height, isOutSide);
        view.setBackgroundResource(R.color.ll_black_30);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FixedPopWindow.this.dismiss();
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }
}