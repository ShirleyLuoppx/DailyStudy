package com.gclibrary.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.math.BigDecimal;

/**
 * SD卡相关的辅助类
 */
public class SDUtils {
    private SDUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用（可能sd卡移除或者没有sd卡）
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable());

    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 在缓存目录下，创建缓存文件
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (isSDCardEnable()) {
            //内部缓存
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            //外部缓存
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 计算缓存文件的大小
     */
    public static String getCacheSize(Context context) {
        String[] sUnit = {"K", "M", "G"};
        double[] iUnit = {1 * 1024, 1 * 1024 * 1024, 1 * 1024 * 1024 * 1024};
        double d = 0;
        try {
            String cachePath = context.getExternalCacheDir().getPath();
            String cachePath2 = context.getCacheDir().getPath();
            File file = new File(cachePath);
            File file2 = new File(cachePath2);
            d = getFileSize(file) + getFileSize(file2);
            for (int i = iUnit.length - 1; i >= 0; i--) {
                double relative = doubleTo(d / iUnit[i], 1);
//                L.i(i + ":" + d + "数值" + relative);
                if (relative > 0) {
                    return relative + "" + sUnit[i];
                }
            }
        } catch (Exception e) {
            return "0" + sUnit[0];
        }
        return doubleTo((double) (d / iUnit[0]), 1) + "" + sUnit[0];
    }

    /**
     * 删除缓存文件
     */
    public static boolean delCacheFile(Context context) {
        try {
            String cachePath = context.getExternalCacheDir().getPath();
            String cachePath2 = context.getCacheDir().getPath();
            delAllFile(cachePath);
            delAllFile(cachePath2);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 计算文件大小
     */
    public static double getFileSize(File file) {
        // 判断文件是否存在
        if (file.exists()) {
            // 如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double d = 0;
                for (File f : children)
                    d += getFileSize(f);

                return d;
            } else {// 如果是文件则直接返回其大小
                double d = (double) file.length();
                return d;
            }
        } else {
            Log.e("cachefile", "缓存文件没找到");
            return 0.0;
        }
    }

    /**
     * 删除所有文件
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
                Log.e("delete", temp.getAbsolutePath());
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 浮点数保留小数
     */
    public static float floatTo(float f, int n) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(n, BigDecimal.ROUND_HALF_UP).floatValue();//表明四舍五入
    }

    /**
     * 浮点数保留小数 向上取整
     */
    public static float floatToCell(float f, int n) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(n, BigDecimal.ROUND_CEILING).floatValue();//向上取整
    }

    /**
     * 双精度保留小数
     */
    public static Double doubleTo(Double f, int n) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(n, BigDecimal.ROUND_HALF_UP).doubleValue();//表明四舍五入
    }

    /**
     * 双精度保留小数 向上取整
     */
    public static Double doubleToCell(Double f, int n) {
        BigDecimal b = new BigDecimal(f);
        return b.setScale(n, BigDecimal.ROUND_CEILING).doubleValue();//向上取整
    }

    /***
     * 删除本地文件
     *
     * @param fileName
     */
    public static void DeleteFile(String fileName) {

        if (SDUtils.isSDCardEnable()) {
            File sdCardDir = Environment.getExternalStorageDirectory();
            String path = sdCardDir.getAbsolutePath() + "/Cells/" + fileName;

            File file = new File(path);
            // 判断目录或文件是否存在
            if (file.exists() && file.isFile()) { // 为文件且不为空则进行删除
                file.delete();
            }
        }
    }

    /**
     * 在SD卡下创建文件夹
     *
     * @return
     */
    public static File createFolders(String folder) {
        if (SDUtils.isSDCardEnable()) {

            File baseDir = Environment.getExternalStorageDirectory();

            if (baseDir == null)
                return Environment.getExternalStorageDirectory();

            File cellsFolder = new File(baseDir, folder);

            if (cellsFolder.exists())
                return cellsFolder;
            if (cellsFolder.mkdirs())
                return cellsFolder;

            return baseDir;
        }
        return null;
    }

    /**
     * 从路径获取文件名
     */
    public static String getFileName(String pathandname) {
        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }
    }

    /**
     * 文件的大小换算
     */
    public static String getNewSize(double d) {
        String[] sUnit = {"KB", "MB"};
        double[] iUnit = {1 * 1024, 1 * 1024 * 1024};
        try {
            for (int i = iUnit.length - 1; i >= 0; i--) {
                double relative = doubleTo(d / iUnit[i], 1);
                if (relative > 0) {
                    return relative + "" + sUnit[i];
                }
            }
        } catch (Exception e) {
            return "0" + sUnit[0];
        }
        return doubleTo((double) (d / iUnit[0]), 1) + "" + sUnit[0];
    }

    /**
     * 文件的大小换算
     */
    public static String getNewSize3(double d) {
        String[] sUnit = {"K", "M"};
        double[] iUnit = {1 * 1024, 1 * 1024 * 1024};
        try {
            for (int i = iUnit.length - 1; i >= 0; i--) {
                double relative = doubleTo(d / iUnit[i], 1);
                if (relative > 0) {
                    return relative + "" + sUnit[i];
                }
            }
        } catch (Exception e) {
            return "0" + sUnit[0];
        }
        return doubleTo((double) (d / iUnit[0]), 1) + "" + sUnit[0];
    }

    /**
     * 文件的大小换算
     */
    public static String getNewSize2(double d) {
        String[] sUnit = {"K/s", "M/s"};
        double[] iUnit = {1 * 1024, 1 * 1024 * 1024};
        try {
            for (int i = iUnit.length - 1; i >= 0; i--) {
                double relative = doubleTo(d / iUnit[i], 1);
                if (relative >= 1) {
                    return relative + "" + sUnit[i];
                }
            }
        } catch (Exception e) {
            return "0" + sUnit[0];
        }
        return doubleTo((d / iUnit[0]), 1) + "" + sUnit[0];
    }

    //删除文件夹和文件夹里面的文件
    public static boolean deleteDir(final String pPath) {
        try {
            File dir = new File(pPath);
            deleteDirWihtFile(dir);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
}
