package com.gclibrary.view;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gclibrary.R;
import com.gclibrary.util.Tools;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2019/1/16 0016.
 */

public class ToastViewResult {
    private Toast toast;

    private ToastViewResult(Context context, int icon, String text, int duration) {
        View v = Tools.inflate(context, R.layout.item_toast_view_result);
        ImageView iv = v.findViewById(R.id.iv);
        TextView tv = v.findViewById(R.id.tv);
        LinearLayout llBg = v.findViewById(com.gclibrary.R.id.ll_bg);
        Tools.setBgCircle(llBg, 24, R.color.ll_black_70);
        tv.setText(text);
        iv.setImageResource(icon);
        toast = new Toast(context);
        toast.setDuration(duration);
        toast.setView(v);
        toast.setGravity(Gravity.CENTER, 0, 0);
    }

    public static ToastViewResult makeText(Context context, int icon, String text, int duration) {
        return new ToastViewResult(context, icon, text, duration);
    }

    public static ToastViewResult makeTextSuccess(Context context, String text) {
        return new ToastViewResult(context, R.drawable.notice_success, text, Toast.LENGTH_SHORT);
    }

    public static ToastViewResult makeTextFail(Context context, String text) {
        return new ToastViewResult(context, R.drawable.notice_fail, text, Toast.LENGTH_SHORT);
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

    /**
     * 反射字段
     *
     * @param object    要反射的对象
     * @param fieldName 要反射的字段名称
     */
    private static Object getField(Object object, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        if (field != null) {
            field.setAccessible(true);
            return field.get(object);
        }
        return null;
    }

}
