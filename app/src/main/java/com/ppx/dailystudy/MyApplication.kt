package com.ppx.dailystudy

import android.app.Application
import android.util.Log
import cn.flyaudio.sdk.FlySDKManager
import cn.flyaudio.sdk.InitListener
import cn.flyaudio.sdk.manager.*
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer

/**
 * Author: luoxia
 * Date: 2020/8/19 13:58
 * Description: DESCRIPTION
 */
class MyApplication : Application() {
    private val TAG = "MyApplication"

    override fun onCreate() {
        super.onCreate()

        ////在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this)
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL)

        //初始化flysdk
        FlySDKManager.getInstance().initialize(this, object : InitListener {
            override fun onError() {}
            override fun onSucceed() {
                Log.d(TAG, "flysdk init succeed ")
                //注册回调
                FlySystemManager.getInstance().registerCallBackListener()
                FlyRadioManager.getInstance().registerCallBackListener()
                FlyAirManager.getInstance().registerCallBackListener()
                FlyCarInfoManager.getInstance().registerCallBackListener()
                FlyBluetoothManager.getInstance().registerCallBackListener()
                FlyNewEnergyManager.getInstance().registerCallBackListener()
            }
        })
    }
}