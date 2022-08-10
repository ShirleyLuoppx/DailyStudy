package com.gclibrary.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author way
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {
    /**
     * 时间戳转字符串日期yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String getLongToStringAll(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }

    /**
     * 时间戳转字符串日期yyyy-MM-dd HH:mm
     *
     * @param time
     * @return
     */
    public static String getLongToStringNoSecond(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date(time));
    }

    /**
     * 时间戳转字符串日期yyyy-MM-dd
     *
     * @param time
     * @return
     */
    public static String getLongToStringYearAndMonthAndDay(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(time));
    }

    /**
     * @param time
     * @return
     */
    public static String getLongToStringHourAndMinAndSecond(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date(time));
    }

    /**
     * 时间戳转字符串日期HH:mm
     *
     * @param time
     * @return
     */
    public static String getLongToStringHourAndMin(long time) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(new Date(time));
    }

    /**
     * 获取系统当前时间
     * <p/>
     * yyyy-MM-dd HH:mm:ss 格式
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String nowTimeAll() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String nowTime = formatter.format(curDate);
        return nowTime;
    }

    /**
     * 获取系统当前时间
     * <p/>
     * yyyy-MM-dd HH:mm 格式
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String nowTimeNoSecond() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String nowTime = formatter.format(curDate);
        return nowTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static String nowTimeYearAndMonthAndSecond() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String nowTime = formatter.format(curDate);
        return nowTime;
    }

    @SuppressLint("SimpleDateFormat")
    public static String nowTimeYearAndMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String nowTime = formatter.format(curDate);
        return nowTime;
    }

    /**
     * 获取系统当前年份
     * yyyy 格式
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String nowYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String nowYear = formatter.format(curDate);
        return nowYear;
    }


    @SuppressLint("SimpleDateFormat")
    public static String nowYearAndMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String nowYear = formatter.format(curDate);
        return nowYear;
    }


    /**
     * 将时间字符串转换成 MM月dd日EEEE HH:mm 格式的时间串
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String convertTime(Date date) {
        String convertTime = "";

        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日EEEE HH:mm");

        convertTime = formatter.format(date);

        return convertTime;
    }

    /**
     * 将时间字符串转换成 MM月dd日 格式的日期串
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String convertDate(Date date) {
        String convertDate = "";

        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");

        convertDate = formatter.format(date);

        return convertDate;
    }

    /**
     * 将时间字符串yyyy-MM-dd HH:mm:ss装换Long
     */
    public static long stringToLongAll(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将时间字符串yyyy-MM-dd HH:mm装换Long
     */
    public static long stringToLongNoSecond(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将时间字符串yyyy-MM-dd装换Long
     */
    public static long stringToLongYearAndMonthAndDay(String str) {
        if (TextUtils.isEmpty(str)) return 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将时间字符串yyyy装换Long
     */
    public static long stringToLongYear(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将时间字符串HH:mm:ss装换Long
     */
    public static long stringToLongHourAndMinAndSecond(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            Date date = sdf.parse(str);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 两个时间的差值
     */
    public static String getDateRelative(String str) {
        String sRelative = "刚刚";
        if (TextUtils.isEmpty(str)) return sRelative;
        long[] lRelatives = {1 * 1000, 1 * 60 * 1000, 1 * 60 * 60 * 1000, 1 * 24 * 60 * 60 * 1000, 1 * 7 * 24 * 60 * 60 * 1000,
                1 * 30 * 24 * 60 * 60 * 1000L, 12 * 30 * 24 * 60 * 60 * 1000L};
        String[] sRelatives = {"秒前", "分钟前", "小时前", "天前", "周前", "月前", "年前"};
        long nowTime = System.currentTimeMillis();
        long oldTime = stringToLongAll(str);
        long relativeTime = nowTime - oldTime;
        if (relativeTime > lRelatives[5]) return str.substring(0, 10);
        for (int i = lRelatives.length - 1; i >= 0; i--) {
            long relativeTime2 = (relativeTime / lRelatives[i]);
            if (relativeTime2 > 0) {
                sRelative = relativeTime2 + "" + sRelatives[i];
                return sRelative;
            }
        }
        return sRelative;
    }
    /**
     * 根据出生日期计算年龄
     */
    /**
     * 返回出生日期格式2010-06-25
     */
    public static int getAgeString(String str) {
        long year = 31536000000L;
        long nowTime = System.currentTimeMillis();
        long oldTime = stringToLongAll(str);
        return (int) ((nowTime - oldTime) / year);
    }

    /**
     * 计算倒计时
     */
    public static long[] getTimeAscLongs(Long endTime) {
        long[] times = new long[4];
        if (endTime == null || endTime <= 0) {
            return times;
        }
        if (endTime / (24 * 60 * 60 * 1000) != 0) {
            times[0] = endTime / (24 * 60 * 60 * 1000);
            endTime = endTime % (24 * 60 * 60 * 1000);
        }
        if (endTime / (60 * 60 * 1000) != 0) {
            times[1] = endTime / (60 * 60 * 1000);
            endTime = endTime % (60 * 60 * 1000);
        }
        if (endTime / (60 * 1000) != 0) {
            times[2] = endTime / (60 * 1000);
            endTime = endTime % (60 * 1000);
        }
        times[3] = endTime / 1000;
        return times;
    }

    /**
     * 倒计时
     */
    public static String getTimeAscString(Long time) {
        String str = "";
        Long[] times = {60 * 60 * 1L, 60 * 1L, 1L};
        Long temp = time / 1000;
        for (int i = 0; i < times.length; i++) {
            Long result = temp / times[i];
            if (result == 0) {
                str = str + ((i < times.length - 1) ? "00:" : "00");
            } else if (result < 10) {
                str = str + ((i < times.length - 1) ? "0" + result + ":" : "0" + result);
            } else if (result >= 10) {
                str = str + ((i < times.length - 1) ? result + ":" : +result);
            } else {
                str = "00:00:00";
            }

            if (temp / times[i] != 0) {
                temp = temp % times[i];
            }
        }
        return str;
    }


    public static String convertTimeMRSF(long time) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(new Date(time));
    }
}
