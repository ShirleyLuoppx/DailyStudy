package com.ppx.dailystudy.meeting.remoteview;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.ppx.dailystudy.MyApplication;
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
 * 参数解释：
 * context：上下文
 * requestCode：一般传0
 * intent：意图
 * flags：
 * FLAG_ONE_SHOT：当前描述的PendingIntent只能被使用一次，然后它就会被自动cancel
 * FLAG_UPDATE_CURRENT：如果当前描述的pendingIntent已经存在，他们会被更新
 * FLAG_CANCEL_CURRENT：如果描述的PendingIntent已经存在，那么他们会被cancel掉，然后系统会创建一个新的pendingIntent
 * FLAG_NO_CREATE：不常用，不介绍
 * 所谓描述，就是说当intent的ComponentName和intent-filter都相同既可以视为是相同的intent
 */
public class MyAppWidgetProvider extends AppWidgetProvider {
    private String TAG = "MyAppWidgetProvider";
    private String ACTION = "CLICK_REMOTE_VIEW";

    public MyAppWidgetProvider() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, "onReceive: action  :" + intent.getAction());

        if (ACTION.equals(intent.getAction())) {
            Log.d(TAG, "onReceive: action  ------------------   click in");
            Toast.makeText(context, "点击桌面小部件", Toast.LENGTH_SHORT).show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Intent intent1 = new Intent(MyApplication.getContext(), PendingIntentActivity.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent1);
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

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_content);
        Intent intent = new Intent();//context,PendingIntentActivity.class
        intent.setAction(ACTION);
        //8.0 以上必须加这个，要不然收不到广播
        intent.setComponent(new ComponentName(context, MyAppWidgetProvider.class));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.forward_button, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetIds[0], remoteViews);
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
