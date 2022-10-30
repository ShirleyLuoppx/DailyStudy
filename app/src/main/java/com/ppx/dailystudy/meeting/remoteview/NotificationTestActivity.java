package com.ppx.dailystudy.meeting.remoteview;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.ppx.dailystudy.R;

/**
 * @Author: LuoXia
 * @Date: 2022/10/30 10:45
 * @Description: remoteview 用于自定义消息通知
 */
public class NotificationTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);
        findViewById(R.id.btn_click_show_notification).setOnClickListener(v -> defaultNotify());

        findViewById(R.id.btn_click_remote_view).setOnClickListener(v -> remoteViewNotify());
    }

    //remoteView的自定义通知
    private void remoteViewNotify() {
        Log.d("TAG", "remoteViewNotify: okin getPackageName:" + getPackageName());
        //注意：remoteViews的布局不能使用ConstraintLayout做根布局，否则无法弹出通知
        @SuppressLint("RemoteViewLayout")
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.layout_remote_view);
        remoteViews.setTextViewText(R.id.tv_title, "this is title");
        remoteViews.setTextViewText(R.id.tv_content, "this is content");
        remoteViews.setImageViewResource(R.id.iv_image, R.mipmap.cherry);
        PendingIntent pendingIntentRemoteView = PendingIntent.getActivity(this, 3, new Intent(this, RemoteViewPendingIntentActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.iv_image, pendingIntentRemoteView);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = null;
        Intent intent = new Intent(this, PendingIntentActivity.class);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 3,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.apple)
                .setContentTitle("remoteView")
                .setContentText("remoteViewContent")
                .setContentIntent(pendingIntent3)
                .setCustomContentView(remoteViews);
        //注意！！！Android 8.0以上需要设置渠道信息才能正常弹出通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("to-do"
                    , "待办消息",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{500});
            manager.createNotificationChannel(channel);
            builder.setChannelId("to-do");
            notify = builder.build();
        } else {
            notify = builder.build();
        }
        //使用默认的声音
        notify.defaults |= Notification.DEFAULT_SOUND;
        notify.defaults |= Notification.DEFAULT_VIBRATE;
//        notify.flags |= Notification.FLAG_AUTO_CANCEL; // 但用户点击消息后，消息自动在通知栏自动消失

        manager.notify(3, notify);// 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示
    }

    //默认notification样式
    public void defaultNotify() {
        Log.d("TAG", "defaultNotify: okin");
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = null;
        Intent intent = new Intent(this, PendingIntentActivity.class);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 1,
                intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.apple)
                .setContentTitle("title")
                .setContentText("text")
                .setContentIntent(pendingIntent3);
        //注意！！！Android 8.0以上需要设置渠道信息才能正常弹出通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("to-do"
                    , "待办消息",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{500});
            manager.createNotificationChannel(channel);
            builder.setChannelId("to-do");
            notify = builder.build();
        } else {
            notify = builder.build();
        }
        //使用默认的声音
        notify.defaults |= Notification.DEFAULT_SOUND;
        notify.defaults |= Notification.DEFAULT_VIBRATE;
//        notify.flags |= Notification.FLAG_AUTO_CANCEL; // 但用户点击消息后，消息自动在通知栏自动消失

        manager.notify(2, notify);// 步骤4：通过通知管理器来发起通知。如果id不同，则每click，在status哪里增加一个提示
    }
}
