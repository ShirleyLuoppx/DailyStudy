package com.gclibrary.manager;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.gclibrary.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class XActivityManager {
    static XActivityManager single = null;
    private ArrayList<Activity> activityList = null;
    private Activity curryRunningActivity = null;

    public synchronized static XActivityManager getInstance() {
        if (single == null) {
            single = new XActivityManager();
        }
        return single;
    }

    public XActivityManager() {
        activityList = new ArrayList<>();
    }

    /**
     * 添加activity管理
     *
     * @param activity
     */
    public void addToActivityList(Activity activity) {
        synchronized (activityList) {
            if (!activityList.contains(activity)) {
                activityList.add(activity);
                //设置当前运行的activity
                this.setCurryRunningActivity(activity);
            }
        }
    }

    /**
     * 移除activity
     *
     * @param activity BaseActivity必须继承
     */
    public void removeToActivityList(Activity activity) {
        if (activityList.contains(activity)) {
            activityList.remove(activity);
        }
    }

    /**
     * 设置当前运行的activity
     *
     * @param activity BaseActivity必须继承
     */
    public void setCurryRunningActivity(Activity activity) {
        if (activity != null) {
            curryRunningActivity = activity;
        }
    }

    /**
     * 获取指定的acitivity
     *
     * @param activity activity
     * @return BasessActivity
     */
    public Activity getActivityByClass(Class<? extends Activity> activity) {
        String name = activity.getSimpleName();
        for (Activity ac : activityList) {
            String name1 = ac.getClass().getSimpleName();
            if (name.equals(name1)) {
                return ac;
            }
        }
        return null;
    }

    /**
     * 获取指定的acitivity
     *
     * @param activity activity
     * @return BasessActivity
     */
    public Activity getActivityByClassEnd(Class<? extends Activity> activity) {
        String name = activity.getSimpleName();
        int size = activityList.size();
        int end = -1;
        for (int i = 0; i < size; i++) {
            Activity ac = activityList.get(i);
            String name1 = ac.getClass().getSimpleName();
            if (name.equals(name1)) {
                end = i;
            }
        }
        if (end == -1) {
            return null;
        }
        return activityList.get(end);
    }

    /**
     * 设置当前运行的activity
     *
     * @return BasessActivity
     */
    public Activity getCurryRunningActivity() {
        return curryRunningActivity;
    }

    /**
     * finish掉指定的activity
     *
     * @param activity
     */
    public void finishActivity(Class<? extends Activity> activity) {
        Activity ac = getActivityByClassEnd(activity);
        if (ac != null) ac.finish();
    }

    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    //检查应用是否在后台运行
    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (TextUtils.equals(appProcess.processName, context.getPackageName())) {
                boolean isBackground = (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE);
                boolean isLockedState = keyguardManager.inKeyguardRestrictedInputMode();
                return isBackground || isLockedState;
            }
        }
        return false;
    }

    public static boolean isBackground2(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_BACKGROUND) {
                    LogUtils.i("后台", appProcess.processName);
                    return true;
                }else{
                    LogUtils.i("前台", appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }

    public void wakeUpAndUnlock(Context context) {
        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
        //获取电源管理器对象
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
        //点亮屏幕
        wl.acquire();
        //释放
        wl.release();
    }

}
