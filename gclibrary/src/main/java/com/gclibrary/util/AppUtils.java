package com.gclibrary.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;
import java.util.List;

//跟App相关的辅助类
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取版本名称
     */
    public static String getVersionName(Context context) {
        PackageManager manager;

        PackageInfo info = null;

        manager = context.getPackageManager();

        try {

            info = manager.getPackageInfo(context.getPackageName(), 0);

        } catch (NameNotFoundException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
        return info.versionName;
    }

    public static boolean getVersionNameCompare(Context context, String versionName) {
        if (TextUtils.isEmpty(versionName)) return false;
        int nowVersion = Integer.parseInt(versionName.replace(".", ""));
        int lastVersion = Integer.parseInt(getVersionName(context).replace(".", ""));
        if (nowVersion > lastVersion) {
            return true;
        }
        return false;
    }

    /**
     * 获取版本号
     */
    public static int getVersion(Context context) {
        PackageManager manager;

        PackageInfo info = null;

        manager = context.getPackageManager();

        try {

            info = manager.getPackageInfo(context.getPackageName(), 0);

        } catch (NameNotFoundException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
        return info.versionCode;
    }

    /**
     * 跳转到应用市场，并且是已上架的市场
     */
    public static void openAppMarket(Context context) {
//        String appPkg = context.getPackageName();
//        // 扫描已经安装的市场包名
//        ArrayList<String> marketPkgs = MarketUtils.queryInstalledMarketPkgs(context);
//        Uri uri = Uri.parse("market://details?id=" + appPkg);
//        Intent intent = news Intent(Intent.ACTION_VIEW, uri);
//        // 指定市场
//        if (!TextUtils.isEmpty(marketPkg) && marketPkgs.contains(marketPkg)) {
//            intent.setPackage(marketPkg);
//        }
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
    }

    /**
     * 分享文件 伪代码
     */
    public static void shareFile(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
        context.startActivity(intent);
    }

    public static boolean isInstallApk(Context context, String pkgname) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            if (packageInfo.packageName.equals(pkgname)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }

//    public static String getPacakgeName(Context context) {
//
//        try {
//            ActivityManager am = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
//            ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
//            return cn.getPackageName();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }
}
