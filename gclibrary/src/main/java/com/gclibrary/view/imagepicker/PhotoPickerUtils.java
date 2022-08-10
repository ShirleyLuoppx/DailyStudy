package com.gclibrary.view.imagepicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.gclibrary.ui.activity.ShowImageActivity;

import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

/**
 * Created by 12985 on 2017/5/15.
 */

public class PhotoPickerUtils {
    /**
     * 打开图片activity
     */
    public static void openPhoto(Activity activity, int limitCount) {
        PhotoPicker.builder()
                .setPhotoCount(limitCount)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(true)
                .start(activity, PhotoPicker.REQUEST_CODE);
    }

    /**
     * 打开图片fragment
     */
    public static void openPhoto(Context context, Fragment fragment, int limitCount) {
        PhotoPicker.builder()
                .setPhotoCount(limitCount)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(true)
                .start(context, fragment, PhotoPicker.REQUEST_CODE);
    }

    /**
     * 打开图片activity没有相机
     */
    public static void openPhotoNoCamera(Activity activity, int limitCount) {
        PhotoPicker.builder()
                .setPhotoCount(limitCount)
                .setShowCamera(false)
                .setShowGif(true)
                .setPreviewEnabled(true)
                .start(activity, PhotoPicker.REQUEST_CODE);
    }

    /**
     * 打开图片fragment 没有相机
     */
    public static void openPhotoNoCamera(Context context, Fragment fragment, int limitCount) {
        PhotoPicker.builder()
                .setPhotoCount(limitCount)
                .setShowCamera(false)
                .setShowGif(true)
                .setPreviewEnabled(true)
                .start(context, fragment, PhotoPicker.REQUEST_CODE);
    }

    /**
     * 浏览所有图片
     */
    public static void showPhoto(Context context, ArrayList<String> photoPaths, int position) {
        if (photoPaths == null || photoPaths.size() == 0) return;
        for (int i = 0; i < photoPaths.size(); i++) {
            if (!TextUtils.isEmpty(photoPaths.get(i)) && !photoPaths.get(i).startsWith("http") && !photoPaths.get(i).startsWith("file://")) {
                photoPaths.set(i, photoPaths.get(i));
            }
        }
        ShowImageActivity.start(context, photoPaths, position);
    }

    public static void showPhoto(Context context, String image) {
        if (TextUtils.isEmpty(image)) return;
        if (image.startsWith("http") && !image.startsWith("file://")) {
            ShowImageActivity.start(context, image);
        } else {
            ShowImageActivity.start(context, image);
        }
    }

    /**
     * 选择的图片回调
     */
    public static ArrayList<String> onActivityResult(Context context, int requestCode, int resultCode, Intent data) {
        if (resultCode == ((Activity) context).RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                return data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
        }
        return null;
    }

}
