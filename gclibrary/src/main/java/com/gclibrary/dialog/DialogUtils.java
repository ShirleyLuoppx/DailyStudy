package com.gclibrary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gclibrary.R;
import com.gclibrary.ui.BaseTitle;
import com.gclibrary.util.ScrUtils;
import com.gclibrary.util.Tools;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * Created by 12985 on 2016/11/24.
 */

public class DialogUtils {

    public static Dialog dialog(Context context, View view, int animationStyle, int width, int gravity, Boolean isTouchOutside) {
        Dialog dialog = new Dialog(context, R.style.dialogThemeFullScr);
        dialog.setCanceledOnTouchOutside(isTouchOutside);
        dialog.setCancelable(isTouchOutside);
        Window window = dialog.getWindow();
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowManager.LayoutParams wl = window.getAttributes();

        wl.gravity = gravity;
        Rect rect = new Rect();
        View v = window.getDecorView();
        v.getWindowVisibleDisplayFrame(rect);
        wl.width = width;
        if (animationStyle != 0)
            window.setWindowAnimations(animationStyle); //设置窗口弹出动画
        window.setAttributes(wl);
        dialog.setContentView(view);
        return dialog;
    }


    public static Dialog dialogBottom(Context context, View view, boolean isOutSide) {
        Dialog dialog = new Dialog(context, R.style.dialogThemeFullScr);
        dialog.setCanceledOnTouchOutside(isOutSide);
        dialog.setCancelable(isOutSide);
        Window window = dialog.getWindow();
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowManager.LayoutParams wl = window.getAttributes();

        wl.gravity = Gravity.BOTTOM;
        Rect rect = new Rect();
        View v = window.getDecorView();
        v.getWindowVisibleDisplayFrame(rect);
        wl.width = ScrUtils.getScreenWidth(context);
        window.setWindowAnimations(R.style.dialogAnimBottom);
        window.setAttributes(wl);
        dialog.setContentView(view);
        return dialog;
    }

    public static Dialog dialogCenter(Context context, View view, boolean isFill, boolean isOutSide) {
        Dialog dialog = new Dialog(context, R.style.dialogThemeFullScr);
        dialog.setCanceledOnTouchOutside(isOutSide);
        dialog.setCancelable(isOutSide);
        Window window = dialog.getWindow();
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowManager.LayoutParams wl = window.getAttributes();

        wl.gravity = Gravity.CENTER;
        Rect rect = new Rect();
        View v = window.getDecorView();
        v.getWindowVisibleDisplayFrame(rect);
        wl.width = isFill ? ScrUtils.getScreenWidth(context) : ScrUtils.getScreenWidth(context) * 8 / 10;
        if (isFill) {
            wl.height = ScrUtils.getScreenHeight(context) - ScrUtils.getStatusHeight(context);
        }

//        window.setWindowAnimations(R.style.dialogAnimLeft);
        window.setAttributes(wl);
        dialog.setContentView(view);
        return dialog;
    }

    public static Dialog dialogCenter(Context context, View view, int width, boolean isOutSide) {
        Dialog dialog = new Dialog(context, R.style.dialogThemeFullScr);
        dialog.setCanceledOnTouchOutside(isOutSide);
        dialog.setCancelable(isOutSide);
        Window window = dialog.getWindow();
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        WindowManager.LayoutParams wl = window.getAttributes();

        wl.gravity = Gravity.CENTER;
        Rect rect = new Rect();
        View v = window.getDecorView();
        v.getWindowVisibleDisplayFrame(rect);
        wl.width = width;
//        window.setWindowAnimations(R.style.dialogAnimLeft);
        window.setAttributes(wl);
        dialog.setContentView(view);
        return dialog;
    }

    public static Dialog dialogCenter(Context context, View view, boolean isOutSide) {
        return dialogCenter(context, view, false, isOutSide);
    }


//    public static Dialog dialogCenterTwoBtn(Context context, String[] titles, final OnDialogResultTwo onDialogResultTwo) {
//        View view = View.inflate(context, R.layout.dialog_result_center_two, null);
//        TextView tvDTitle = (TextView) view.findViewById(R.id.tv_d_title);
//        TextView tvDOk = (TextView) view.findViewById(R.id.tv_d_ok);
//        TextView tvDCanncel = (TextView) view.findViewById(R.id.tv_d_canncel);
//
//        final Dialog dialog = dialogCenter(context, view, true);
//        if (titles != null && titles.length > 0) {
//            tvDTitle.setText(titles[0]);
//        }
//        if (titles != null && titles.length > 1) {
//            tvDOk.setText(titles[1]);
//        }
//        if (titles != null && titles.length > 2) {
//            tvDCanncel.setText(titles[2]);
//        }
//        tvDOk.setOnClickListener(news View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onDialogResultTwo != null) {
//                    onDialogResultTwo.onDialogOkFist();
//                }
//                dialog.dismiss();
//            }
//        });
//        tvDCanncel.setOnClickListener(news View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onDialogResultTwo != null) {
//                    onDialogResultTwo.onDialogOkTwo();
//                }
//                dialog.dismiss();
//            }
//        });
//
//        return dialog;
//    }
//
//    public static Dialog dialogCenterOneBtn(Context context, final OnDialogResultOne onDialogResultOne) {
//        View view = View.inflate(context, R.layout.dialog_result_center_one, null);
//        TextView tvDTitle = (TextView) view.findViewById(R.id.tv_d_title);
//        TextView tvDOk = (TextView) view.findViewById(R.id.tv_d_ok);
//
//        final Dialog dialog = dialogCenter(context, view, true);
//        tvDOk.setOnClickListener(news View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onDialogResultOne != null) {
//                    onDialogResultOne.onDialogOk();
//                }
//                dialog.dismiss();
//            }
//        });
//
//        return dialog;
//    }

    public static Dialog dialogBottom(Context context, final OnDialogResult onDialogResult) {
        View view = View.inflate(context, R.layout.dialog_result_bottom, null);
        ListView lvD = view.findViewById(R.id.lv);
        final Dialog dialog = dialogBottom(context, view, true);
        lvD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onDialogResult != null) {
                    onDialogResult.onDialogOk(position);
                }
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public static void setDialogContent(Context context, String title, final Dialog dialog, List<String> list, int heightLimit) {
        BaseTitle bt = dialog.findViewById(R.id.bt);
        bt.getvStatusBar().setVisibility(View.GONE);
        bt.getIvLeft().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.dimssDialog(dialog);
            }
        });
        bt.setTvTitle(title);
        ListView lvD = dialog.findViewById(R.id.lv);
        CommonAdapter<String> adapter = new CommonAdapter<String>(context, R.layout.item_result_bottom, list) {
            @Override
            protected void convert(ViewHolder holder, String item, int position) {
                TextView tv = holder.getView(R.id.tv);
                tv.setText(item);
            }
        };
        lvD.setAdapter(adapter);
        if (heightLimit > 0) {
            Tools.measureLVHeight(lvD, heightLimit);
        }
    }

    public interface OnDialogResult {
        void onDialogOk(int position);
    }


//    /**
//     * Popup
//     */
//    public static FixedPopWindow popueWindows(View view, View down) {
//        FixedPopWindow popupWindow = news FixedPopWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
//        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
//        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        popupWindow.setClippingEnabled(false);
////        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        popupWindow.setTouchable(true);
//        popupWindow.setTouchInterceptor(news View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//            }
//        });
////        ColorDrawable colorDrawable = news ColorDrawable();
////        colorDrawable.setColor(0x665a5551);
//        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
//        // 我觉得这里是API的一个bug
//        popupWindow.setBackgroundDrawable(news BitmapDrawable());
//        popupWindow.setOutsideTouchable(true);
//        // 设置好参数之后再show
//        popupWindow.showAsDropDown(down);
//        return popupWindow;
//    }

    /**
     * Popup
     */
    public static PopupWindow popueWindows(final Context context, int width, View view) {
        PopupWindow popupWindow = new PopupWindow(view, width, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
//        ColorDrawable colorDrawable = news ColorDrawable();
//        colorDrawable.setColor(0x665a5551);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context, 1f);
            }
        });
        // 设置好参数之后再show
//        popupWindow.showAsDropDown(down);
        return popupWindow;
    }

    public static PopupWindow popueWindows(final Context context, View view) {
        return popueWindows(context, ScrUtils.getScreenWidth(context), view);
    }

    public static void showPopupWindow(Context context, PopupWindow popupWindow, View viewDown, int gravity, boolean flag) {
        if (flag)
            backgroundAlpha((Activity) context, 0.7f);
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.showAsDropDown(viewDown);
        } else {
            int[] location = new int[2];
            viewDown.getLocationOnScreen(location);
            int y = location[1];
            popupWindow.showAtLocation(viewDown, gravity, 0, y + viewDown.getHeight());
        }
    }

    public static void showPopupWindow(Context context, PopupWindow popupWindow, View viewDown, int gravity) {
        showPopupWindow(context, popupWindow, viewDown, gravity, true);
    }

    /**
     * Popup
     */
    public static FixedPopWindow popueWindows(View view, View down) {
        FixedPopWindow popupWindow = new FixedPopWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setClippingEnabled(false);
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
//        ColorDrawable colorDrawable = news ColorDrawable();
//        colorDrawable.setColor(0x665a5551);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        // 设置好参数之后再show
        popupWindow.showAsDropDown(down);
        return popupWindow;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

}
