package com.ppx.dailystudy

import android.app.Application
import android.util.Log
import cn.flyaudio.sdk.FlySDKManager
import cn.flyaudio.sdk.InitListener
import cn.flyaudio.sdk.manager.*

/**
 * Author: luoxia
 * Date: 2020/8/19 13:58
 * Description: DESCRIPTION
 */
class MyApplication : Application() {
    private val TAG = "MyApplication"

    override fun onCreate() {
        super.onCreate()

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
            }
        })
    }
}