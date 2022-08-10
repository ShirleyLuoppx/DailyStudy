package com.gclibrary.view;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gclibrary.R;
import com.gclibrary.util.ToastUtils;
import com.gclibrary.util.Tools;

/**
 * Created by 张广川 on 2021/3/23.
 */

public class ToastView {
    private Context context;
    private Toast toast;
    private final TextView tv;

    private ToastView(Context context, CharSequence text, int duration) {
        this.context = context;
        View v = Tools.inflate(context, R.layout.item_toast_view);
        LinearLayout llBg = v.findViewById(R.id.ll_bg);
        Tools.setBgCircle(llBg, 12, R.color.ll_black_70);
        tv = v.findViewById(R.id.tv);
        tv.setText(text);
        toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    public static ToastView makeText(Context context, CharSequence text, int duration) {
        return new ToastView(context, text, duration);
    }

    public static ToastView makeText(Context context, @StringRes int resId, int duration) {
        return new ToastView(context, context.getText(resId), duration);
    }

    public void show() {
        if (toast != null) {
            toast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (toast != null) {
            toast.setGravity(gravity, xOffset, yOffset);
        }
    }

    public void setText(CharSequence text) {
        if (tv != null) {
            tv.setText(text);
        }
    }

    public void setText(@StringRes int resId) {
        if (tv != null) {
            setText(context.getText(resId));
        }
    }
}
