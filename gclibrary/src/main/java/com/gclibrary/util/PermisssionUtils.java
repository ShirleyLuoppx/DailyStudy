package com.gclibrary.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by 12985 on 2016/12/1.
 */
public class PermisssionUtils {

    public static final int REQUEST_CODE_WRITE = 0;
    public static final int REQUEST_CODE_CAMERA = 1;
    public static final int RECORD_AUDIO = 2;//录音
    public static final int ACCESS_FINE_LOCATION = 3;//定位
    public static final int ACCESS_COARSE_LOCATION = 4;//定位

    public static String[] urls = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public static boolean isHasFileWritePermission(Activity activity) {
        if (activity == null || activity.isFinishing()) return false;
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            for (String str : permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(permissions, REQUEST_CODE_WRITE);
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isHasCameraPermission(Activity activity) {
        if (activity == null || activity.isFinishing()) return false;
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {Manifest.permission.CAMERA};
            for (String str : permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(permissions, REQUEST_CODE_CAMERA);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 申请单个
     */
    public static boolean checkSelfPermission(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {urls[requestCode]};
            for (String str : permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(permissions, requestCode);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 申请全部
     */
    public static boolean checkSelfPermissionAll(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            for (String str : urls) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(urls, 100);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 地图权限
     */
    public static boolean checkMapPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {urls[ACCESS_FINE_LOCATION], urls[ACCESS_COARSE_LOCATION], urls[REQUEST_CODE_WRITE]};
            for (String str : permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(permissions, 100);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 定位权限
     */
    public static boolean checkLocationPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] permissions = {urls[ACCESS_FINE_LOCATION], urls[ACCESS_COARSE_LOCATION]};
            for (String str : permissions) {
                if (activity.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    activity.requestPermissions(permissions, 100);
                    return false;
                }
            }
        }
        return true;
    }
}
