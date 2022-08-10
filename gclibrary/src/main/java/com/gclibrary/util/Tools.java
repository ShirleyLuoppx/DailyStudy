package com.gclibrary.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class Tools {

    public static void setTextViewStyles(TextView text) {
        LinearGradient linearGradient = new LinearGradient(
                0, 0, text.getPaint().measureText(text.getText().toString()), 0, new int[]{0xFFFAA347, 0xFFD53A63, 0xFF5063CF}, new float[]{0, 0.5f, 1}, Shader.TileMode.CLAMP);
        text.getPaint().setShader(linearGradient);
        text.invalidate();
    }


    public static void openBrowser(Context context, String url) {
//        if (TextUtils.isEmpty(url) || !url.startsWith("http")) return;
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(url);
            intent.setData(content_url);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort(context, "下载链接错误！");
        }
    }

    public static boolean isDestroy(Activity mActivity) {
        if (mActivity == null ||
                mActivity.isFinishing() ||
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && mActivity.isDestroyed())) {
            return true;
        } else {
            return false;
        }
    }

    public static void imageViewRotation(ImageView imageView, boolean isRota) {
        imageView.setPivotX(imageView.getWidth() / 2);
        imageView.setPivotY(imageView.getHeight() / 2);//支点在图片中心
        imageView.setRotation(!isRota ? 0 : 180);
    }

    /**
     * 改变图标颜色
     */
    public static void iconColorFilter(ImageView view, int color) {
        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        view.setColorFilter(colorFilter);
    }

    /**
     * 设置listview分割线
     */
    public static void setListViewDividerHeight(Context context, ListView lv, int height) {
        int tempHeight = ScrUtils.getRealWidth(context, height);
        lv.setDividerHeight((tempHeight == 0) ? 1 : tempHeight);
    }

    /**
     * 显示对话框
     */
    public static void showDialog(Dialog dialog) {
        if (dialog != null && !dialog.isShowing())
            try {
                dialog.show();
            } catch (Exception e) {
            }
    }

    /**
     * 关闭对话框
     */
    public static void dimssDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing())
            try {
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * 判断字符串是否全部为中文字符组成
     *
     * @param str 检测的文字
     * @return true：为中文字符串，false:含有非中文字符
     */
    public static boolean isChineseStr(String str) {
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        char c[] = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            Matcher matcher = pattern.matcher(String.valueOf(c[i]));
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 加载布局文件xml
     *
     * @param id
     * @return
     */
    public static View inflate(Context context, int id) {
        return View.inflate(context, id, null);
    }

    /**
     * 跳转市场
     */
    public static void goToMarket(Context context) {
        String appPkg = context.getPackageName();
        Uri uri = Uri.parse("market://details?id=" + appPkg);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 分享文件
     */
    public static void shareFile(Context context, File apkFile) {
        if (apkFile == null) return;
//        File apkFile = AppUtils.getApkFile(context);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(apkFile));
        context.startActivity(intent);
    }

    /**
     * 控制imageview 图片颜色
     */
    public static void setColorFilter(ImageView imageView, int color) {
        PorterDuffColorFilter colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        imageView.setColorFilter(colorFilter);
    }


    /**
     * 分页数组
     */
    public static <T> List<T> getPageData(List<T> list, int page, int pageSize) {
        if (list == null || list.size() == 0 || page <= 0 || pageSize <= 0)
            return new ArrayList<T>();
        int firtPos = (page - 1) * pageSize;
        int lastPos = page * pageSize;
        if (lastPos > list.size() - 1) lastPos = list.size() - 1;
        return list.subList(firtPos, lastPos);
    }

    /**
     * 获取地址栏上面参数的值
     */
    public static String getUrlValue(String url, String parm) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(parm)) return "";
        String parms = url.substring(url.indexOf("?") + 1, url.length());
        String[] strs = parms.split("&");
        for (String str : strs) {
            if (str.startsWith(parm)) {
                return str.substring(str.indexOf("=") + 1, str.length());
            }
        }
        return "";
    }

    /**
     * 复制文本
     */
    public static void copyToClipboard(Context context, String filePath) {
        ClipData clipData = ClipData.newPlainText("costom", filePath);
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setPrimaryClip(clipData);
        ToastUtils.showShort(context, "复制成功！");
    }

    /**
     * 控制圆形背景
     */
    public static void setBgCircle(View view, float radius, int color) {
        StateListDrawable bg = new StateListDrawable();
        GradientDrawable bgDrawable = new GradientDrawable();
        bgDrawable.setCornerRadius(ScrUtils.dpToPx(view.getContext(), radius));
        bgDrawable.setColor(view.getResources().getColor(color));
        bg.addState(new int[]{-android.R.attr.state_pressed}, bgDrawable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {//16
            view.setBackground(bg);
        } else {
            view.setBackgroundDrawable(bg);
        }

    }

    /**
     * 控制圆形边框背景
     */
    public static void setBgCircleBox(View view, float radius, float width, int forecolor, int bgColor) {
        StateListDrawable bg = new StateListDrawable();
        GradientDrawable bgDrawable = new GradientDrawable();
        bgDrawable.setCornerRadius(ScrUtils.dpToPx(view.getContext(), radius));
        bgDrawable.setColor(view.getResources().getColor(bgColor));
        int temp = ScrUtils.dpToPx(view.getContext(), width);
        bgDrawable.setStroke(temp == 0 ? 1 : temp, view.getResources().getColor(forecolor));
        bg.addState(new int[]{-android.R.attr.state_pressed}, bgDrawable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {//16
            view.setBackground(bg);
        } else {
            view.setBackgroundDrawable(bg);
        }

    }

    /**
     * 永久跑马灯
     */
    public static void setTextMarquee(TextView tv) {
        tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tv.setFocusable(true);
        tv.setFocusableInTouchMode(true);
        tv.setSingleLine();
        tv.setClickable(true);
        tv.requestFocus();
        tv.setMarqueeRepeatLimit(-1);
    }

    /**
     * 验证钱
     */
    public static void isMoney(EditText etMoney) {
        String s = etMoney.getText().toString();
        if (s.contains(".")) {
            if (s.length() - 1 - s.indexOf(".") > 2) {
                s = s.substring(0,
                        s.indexOf(".") + 3);
                etMoney.setText(s);
                etMoney.setSelection(s.length());
            }
        }
        if (s.trim().substring(0).equals(".")) {
            s = "0" + s;
            etMoney.setText(s);
            etMoney.setSelection(2);
        }

        if (s.startsWith("0")
                && s.trim().length() > 1) {
            if (!s.substring(1, 2).equals(".")) {
                etMoney.setText(s.subSequence(0, 1));
                etMoney.setSelection(1);
                return;
            }
        }
    }

    /**
     * 发短信
     */
    public static void sendMsg(Context context, String message) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", message);
//        sendIntent.putExtra("address",phoneNum);
        sendIntent.setType("vnd.android-dir/mms-sms");
        context.startActivity(sendIntent);
    }

    public static String getRelateTime(long time) {
        if (time < 0) return "0秒";
        long[] times = {1, 1 * 60, 1 * 60 * 60, 1 * 60 * 60 * 24, 1 * 60 * 60 * 24 * 30};
        String[] strTimes = {"秒", "分", "时", "天", "月"};
        long tempRelative = time;
        String strTime = "";
        for (int i = strTimes.length - 1; i >= 0; i--) {
            if (tempRelative / times[i] > 0) {
                long tempCount = tempRelative / times[i];
                strTime = strTime + tempCount + strTimes[i];
                if (i > 0) {
                    strTime = strTime + (tempRelative - tempCount * times[i]) / times[i - 1] + strTimes[i - 1];
                }
                return strTime;
            }
        }
        return "0秒";
    }

    /**
     * fragment更换
     */
    public static void switchFragmentContent(FragmentActivity activity, @IdRes int idRes, Fragment fragment) {
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(idRes, fragment);
        try {
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 手机号验证
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 关键文字变颜色
     */
    public static SpannableStringUtils setKeyWordColor(Context context, int color, String keyWord, String content) {
        return setKeyWordColor(context, null, color, keyWord, content);
    }

    /**
     * 关键文字变颜色
     */
    public static SpannableStringUtils setKeyWordColor(Context context, SpannableStringUtils spannableStringUtils, int color, String keyWord, String content) {
        if (spannableStringUtils == null) {
            spannableStringUtils = new SpannableStringUtils(context, content);
        }
        if (!TextUtils.isEmpty(keyWord)) {
            String regex = Pattern.quote(keyWord);
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(content);
            while (m.find()) {
                spannableStringUtils.setColor(color, m.start(), m.end());
            }
        }
        return spannableStringUtils;
    }

    /**
     * 关键文字变颜色
     */
    public static SpannableStringUtils setKeyWordColor(Context context, int color, String[] keyWords, String content) {
        if (TextUtils.isEmpty(content)) content = "";
        SpannableStringUtils spannableStringUtils = new SpannableStringUtils(context, content);
        if (keyWords == null) return spannableStringUtils;
        for (String keyWord : keyWords) {
            if (!TextUtils.isEmpty(keyWord)) {
                String regex = Pattern.quote(keyWord);
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(content);
                while (m.find()) {
                    spannableStringUtils.setColor(color, m.start(), m.end());
                }
            }
        }
        return spannableStringUtils;
    }

    /**
     * 拨打电话
     */
    public static void callPhone(Context context, String tel) {
        if (!TextUtils.isEmpty(tel)) {
            try {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri
                        .parse("tel:" + tel));
                context.startActivity(intent);
            } catch (Exception e) {
                ToastUtils.showShort(context, "电话不能拨打！");
            }
        } else {
            ToastUtils.showShort(context, "电话不能为空！");
        }
    }

    /**
     * 微信分享id转图片处理
     */
    public static Bitmap wxIdToBitmap(Context context, int idRes) {
        int width = 100;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), idRes);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = bitmap.getWidth() / width;
        bitmap = BitmapFactory.decodeResource(context.getResources(), idRes, options);
        return bitmap;
    }

    /**
     * 把bitmap转bitmap缩放处理
     */
    public static Bitmap BitmapToBitmap(Context context, Bitmap bitmap) {
        int width = 100;
        byte[] bytes = bitmap2Bytes(bitmap);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = bitmap.getWidth() / width;
        bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        return bitmap;
    }

    /**
     * bitmap转btye
     */
    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 设置列表的高度
     *
     * @param lv
     * @return
     */
    public static int measureLVHeight(ListView lv, int needCount) {
        // get ListView adapter
        ListAdapter adapter = lv.getAdapter();
        if (null == adapter) {
            return 0;
        }

        int totalHeight = 0;

        for (int i = 0, len = (((needCount != 0) && (needCount < adapter.getCount())) ? needCount : adapter.getCount()); i < len; i++) {
            View item = adapter.getView(i, null, lv);
            if (null == item)
                continue;
            // measure each item width and height
            item.measure(0, 0);
            // calculate all height
            totalHeight += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lv.getLayoutParams();

        if (null == params) {
            params = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        // calculate ListView height
        params.height = totalHeight + (lv.getDividerHeight() * ((((needCount != 0) && (needCount < adapter.getCount())) ? needCount : adapter.getCount()) - 1));

        lv.setLayoutParams(params);

        return params.height;
    }

    /**
     * 移除父控件
     *
     * @param v
     */
    public static void removeParent(View v) {
        //  先找到爹 在通过爹去移除孩子
        ViewParent parent = v.getParent();
        //所有的控件 都有爹  爹一般情况下 就是ViewGoup
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(v);
        }
    }

    /**
     * 邮箱格式验证
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 密码长度为6~20位并包含数字及字母
     *
     * @param pwd
     * @return
     */
    public static boolean isPwdMatcher(String pwd) {
        String str = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(pwd);
        return m.matches();
    }

    /**
     * 中英文，数字，还有‘-’，‘_’,
     */
    public static boolean isNameMatcher(String name) {
        String str = "^[\u4e00-\u9fa5a-zA-Z0-9_/-]+$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(name);
        return m.matches();
    }

}
