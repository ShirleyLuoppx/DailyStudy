package com.gclibrary.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by guangchuan on 2016/8/3.
 */
public class Notifier {
    protected int notifyID = 3000;//3000-5000
    protected NotificationManager notificationManager = null;
    protected Context context;
    protected Vibrator vibrator;
    protected AudioManager audioManager;
    public static Notifier notifier;
    protected long lastNotifiyTime;
    Ringtone ringtone = null;
    List<Integer> notifyIDs = new CopyOnWriteArrayList<>();


    private Notifier(Context context) {
        this.context = context;
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            CharSequence channelName = "channelName";
            String channelDescription = "channelDescription";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            // 设置描述 最长30字符
            notificationChannel.setDescription(channelDescription);
            // 该渠道的通知是否使用震动
            notificationChannel.enableVibration(true);
            // 设置显示模式
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public static Notifier getInstance(Context context) {
        if (notifier == null) {
            notifier = new Notifier(context);
        }
        return notifier;
    }


    public void cancelNotificaton() {
        if (notificationManager != null) {
            notificationManager.cancel(notifyID);
        }
    }
    /**
     * 5.0的设配问题
     * 	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//悬挂式Notification，5.0后显示

     mBuilder.setContentText(string + "点击查看。").setFullScreenIntent(pendingIntent, true);
     mBuilder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
     mBuilder.setSmallIcon(R.drawable.jingbao1);// 设置通知小ICON（5.0必须采用白色透明图片）

     }else{

     mBuilder.setSmallIcon(R.drawable.ic_launcher);// 设置通知小ICON
     mBuilder.setContentText(string );

     }
     */

    /**
     * 发送通知
     */
    public void sendNotifier(String title, String content, String notifyText, Intent intent) {
        try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel("chat");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent tempIntent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                tempIntent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                tempIntent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                context.startActivity(tempIntent);
                Toast.makeText(context, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        }
            // create and send notificaiton
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
//                    .setChannel("chat")
                    .setChannelId("chat")
                    .setSmallIcon(context.getApplicationInfo().icon)
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, notifyID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentTitle(title);
            mBuilder.setTicker(notifyText);
            mBuilder.setContentText(content);
            mBuilder.setContentIntent(pendingIntent);
//            mBuilder.setNumber(notificationNum);
            Notification notification = mBuilder.build();
            notificationManager.notify(notifyID, notification);
            vibrateAndPlayTone(true, true);
            notifyID++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 声音和震动
     */
    public void vibrateAndPlayTone(boolean isVibrate, boolean isVoice) {

        if (System.currentTimeMillis() - lastNotifiyTime < 2000) {//间隔不能小于2秒
            return;
        }

        try {
            lastNotifiyTime = System.currentTimeMillis();
            if (audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {//声音是否为静音
                return;
            }
            /**
             * 是否开启震动
             */
            if (isVibrate) {
                long[] pattern = new long[]{0, 180, 80, 120};
                vibrator.vibrate(pattern, -1);
            }

            if (isVoice) {
                if (ringtone == null) {
                    Uri notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    ringtone = RingtoneManager.getRingtone(context, notificationUri);
                    if (ringtone == null) {
                        LogUtils.i("没有找到声音");
                        return;
                    }
                }

                if (!ringtone.isPlaying()) {
                    String vendor = Build.MANUFACTURER;

                    ringtone.play();
                    // for samsung S3, we meet a bug that the phone will
                    // continue ringtone without stop
                    // so add below special handler to stop it after 3s if
                    // needed
                    if (vendor != null && vendor.toLowerCase().contains("samsung")) {
                        Thread ctlThread = new Thread() {
                            public void run() {
                                try {
                                    Thread.sleep(3000);
                                    if (ringtone.isPlaying()) {
                                        ringtone.stop();
                                    }
                                } catch (Exception e) {
                                }
                            }
                        };
                        ctlThread.run();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
