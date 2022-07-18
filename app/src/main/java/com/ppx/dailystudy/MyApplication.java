package com.ppx.dailystudy;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

/**
 * @Author: LuoXia
 * @CreateDate: 2022/7/18 22:38
 * @Description:
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApplication", "onCreate: " + Process.myPid());
        context = this;
        ////在使用SDK各组件之前初始化context信息，传入ApplicationContext
//        SDKInitializer.initialize(this)
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
//        SDKInitializer.setCoordType(CoordType.BD09LL)

        //初始化flysdk
//        FlySDKManager.getInstance().initialize(this, object : InitListener {
//            override fun onError() {}
//            override fun onSucceed() {
//                Log.d(TAG, "flysdk init succeed ")
//                //注册回调
//                FlySystemManager.getInstance().registerCallBackListener()
//                FlyRadioManager.getInstance().registerCallBackListener()
//                FlyAirManager.getInstance().registerCallBackListener()
//                FlyCarInfoManager.getInstance().registerCallBackListener()
//                FlyBluetoothManager.getInstance().registerCallBackListener()
//                FlyNewEnergyManager.getInstance().registerCallBackListener()
//            }
//        })
    }

    public static Context getContext(){
        return context;
    }
}
