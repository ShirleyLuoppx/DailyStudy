package com.ppx.dailystudy.meeting.remoteview;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2022/10/30 18:36
 * @Description:remoteView作为桌面小部件 由demo可知两点：
 * 1、AppWidgetProvider实际上就是一个广播，因为AppWidgetProvider继承的BroadcastReceiver；
 * 2、由AppWidgetProvider源码可知，事件的分发就是通过AppWidgetProvider的onReceive的对应action来的。
 * <p>
 * PendingIntent：
 * PendingIntent.getActivity(Context context, int requestCode,Intent intent, @Flags int flags);     相当于startActivity
 * PendingIntent.getService(Context context, int requestCode,Intent intent, @Flags int flags);      相当于startService
 * PendingIntent.getBroadcast(Context context, int requestCode,Intent intent, @Flags int flags);    相当于startBroadcast
 *  参数解释：
 *          context：上下文
 *          requestCode：一般传0
 *          intent：意图
 *          flags：
 *              FLAG_ONE_SHOT：当前描述的PendingIntent只能被使用一次，然后它就会被自动cancel
 *              FLAG_UPDATE_CURRENT：如果当前描述的pendingIntent已经存在，他们会被更新
 *              FLAG_CANCEL_CURRENT：如果描述的PendingIntent已经存在，那么他们会被cancel掉，然后系统会创建一个新的pendingIntent
 *              FLAG_NO_CREATE：不常用，不介绍
 *              所谓描述，就是说当intent的ComponentName和intent-filter都相同既可以视为是相同的intent
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
    private String TAG = "AppWidgetProvider";
    private String ACTION = "CLICK_REMOTE_VIEW";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, "onReceive: action  :" + intent.getAction());

        if (ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "点击桌面小部件", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.apple);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                    for (int i = 0; i < 37; i++) {
                        float degree = (i * 37) % 360;

                        @SuppressLint("RemoteViewLayout")
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
                        remoteViews.setImageViewBitmap(R.id.iv_icon, rotateMatrix(bitmap, degree));

                        Intent intent1 = new Intent();
                        intent1.setAction(ACTION);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);
                        remoteViews.setOnClickPendingIntent(R.id.iv_icon, pendingIntent);

                        appWidgetManager.updateAppWidget(new ComponentName(context, MyAppWidgetProvider.class), remoteViews);
                        SystemClock.sleep(30);
                    }
                }
            }).start();
        }
    }

    /**
     * 小部件被添加时或者每次小部件更新时调用，更新周期取决于updatePeriodMillis
     *
     * @param context
     * @param appWidgetManager
     * @param appWidgetIds
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG, "onUpdate: " + appWidgetIds.length);

        for (int i = 0; i < appWidgetIds.length; i++) {
            int id = appWidgetIds[i];
            onWidgetUpdate(context, appWidgetManager, id);
        }
    }

    /**
     * 更新桌面小部件
     *
     * @param context
     * @param manager
     * @param id
     */
    private void onWidgetUpdate(Context context, AppWidgetManager manager, int id) {
        @SuppressLint("RemoteViewLayout")
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        Intent intent = new Intent();
        intent.setAction(ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_icon, pendingIntent);
//        remoteViews.setTextViewTextSize();
        manager.updateAppWidget(id, remoteViews);
    }

    /**
     * 旋转
     *
     * @param bitmap
     * @param degree 角度
     * @return
     */
    public Bitmap rotateMatrix(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(degree);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    /**
     * 删除小部件时被调用
     *
     * @param context
     * @param appWidgetIds
     */
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG, "onDeleted: ");
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(TAG, "onDisabled: ");
    }

    /**
     * 小部件第一次被添加到桌面史调用该方法
     *
     * @param context
     */
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(TAG, "onEnabled: ");
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Log.d(TAG, "onRestored: ");
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.d(TAG, "onAppWidgetOptionsChanged: ");
    }
}
