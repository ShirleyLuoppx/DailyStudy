package com.gclibrary.util;

/**
 * Created by guangchuan on 2016/9/29.
 * a=(Vt-V0)/t,Vt=V0+at
 * s=V0*t+1/2*(a*t*t)
 * 2as=Vt*Vt-V0*V0
 * 当 a=1 ,t=3，v0=0,vt=3,s=4.5
 */
public class SpeedUtils {
    /**
     * 通过时间获得加速度
     */
    public static float getAByVVT(float v0, float v1, float t) {
        return (float) (v1 - v0) / t;
    }

    /**
     * 末速度
     */
    public static float getVByVAT(float v0, float a, float t) {
        return v0 + a * t;
    }

    /**
     * 得到时间
     */
    public static float getTByVVA(float v0, float v1, float a) {
        return (float) (v1 - v0) / a;
    }

    /**
     * 得到位移
     */
    public static float getSByVAT(float v0, float a, float t) {
        return v0 * t + (float) (a * t * t) / 2;
    }

    /**
     * 得到加速度
     */
    public static float getAByVVS(float v0, float v1, float s) {
        return (float) (v1 * v1 - v0 * v0) / (2 * s);
    }

    /**
     * 等到加速度
     */
    public static float getAByVST(float v0, float s, float t) {
        return (float) ((s - v0 * t) * 2) / (t * t);
    }
}
