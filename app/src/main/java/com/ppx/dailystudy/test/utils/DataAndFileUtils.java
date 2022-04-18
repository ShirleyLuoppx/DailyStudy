package com.ppx.dailystudy.test.utils;

import android.util.Log;

public class DataAndFileUtils implements Runnable{
    public static void main(String[] args){
        intToByteArray1(2);
    }

    public static byte[] intToByteArray1(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    //将整数按照小端存放，低字节出访低位
    public static byte[] intToByteArray2(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    public static int byteArrayToInt1(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static int byteArrayToInt2(byte[] b) {
        return (b[3] & 0xFF) << 24 |
                (b[2] & 0xFF) << 16 |
                (b[1] & 0xFF) << 8 |
                (b[0] & 0xFF);
    }

    public static byte[] intToByteArray(int value) {
        int i = (byte)(value >> 24 & 0xFF);
        int j = (byte)(value >> 16 & 0xFF);
        int k = (byte)(value >> 8 & 0xFF);

        return new byte[] { (byte)(value & 0xFF), (byte) k, (byte) j, (byte) i};
    }
    public static int byteArrayToInt(byte[] date) {
        int start = 0;
        if (date.length < start + 4) {
            return 0;
        }
        return date[start] & 0xFF | date[(start + 1)] << 8 & 0xFF00 | date[(start + 2)] << 16 & 0xFF0000 | date[(start + 3)] << 24 & 0xFF000000;
    }

    @Override
    public void run() {
        Log.d("TAG", "run: okin------run()");
    }
}
