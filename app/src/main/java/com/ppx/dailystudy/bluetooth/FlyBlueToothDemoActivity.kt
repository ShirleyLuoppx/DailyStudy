package com.ppx.dailystudy.bluetooth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
//import cn.flyaudio.sdk.entity.Contact
//import cn.flyaudio.sdk.listener.BluetoothListener
//import cn.flyaudio.sdk.manager.FlyBluetoothManager
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_fly_bluetooth.*

/**
 * Author: luoxia
 * Date: 2020/8/17 16:42
 * Description: DESCRIPTION
 */
@SuppressLint("LongLogTag")
class FlyBlueToothDemoActivity : AppCompatActivity() {

    private val TAG = "FlyBlueToothDemoActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fly_bluetooth)

//        initEvent()

//        FlyBluetoothManager.getInstance().bluetoothListener = object : BluetoothListener {
//            override fun onCallInfo(p0: Int, p1: Contact?) {
//            }
//
//            override fun onPlayStatus(p0: Int) {
//            }
//
//            override fun onConnectDevice(p0: String?) {
//            }
//
//            override fun onConnectStatus(p0: Int) {
//            }
//
//            override fun onPowerStatus(p0: Int) {
//            }
//
//            override fun onMicStatus(p0: Boolean) {
//            }
//
//            override fun onContactDownloadStatus(p0: Int) {
//            }
//
//            override fun onPhoneAcceptTypeChanged(p0: Boolean) {
//            }
//
//            override fun onBtMusicInfo(p0: String?, p1: String?, p2: String?) {
//            }
//
//            override fun onScanDeviceChange(p0: String?, p1: String?) {
//                Log.d(TAG, "onScanDeviceChange: $p0，$p1")
//            }
//
//            override fun onConnectPingStatus(p0: String?, p1: String?) {
//                Log.d(TAG, "onConnectPingStatus: $p0---$p1")
//                //那这个是拿来干什么的？？
////                FlyBluetoothManager.getInstance().connectPingCodeStatus(true)
//            }
//        }

    }

//    private fun initEvent() {
//        bt_open.setOnClickListener {
//            FlyBluetoothManager.getInstance().enableBTPower(true)
//        }
//        bt_close.setOnClickListener {
//            FlyBluetoothManager.getInstance().enableBTPower(false)
//        }
//        bt_startscan.setOnClickListener {
//            FlyBluetoothManager.getInstance().startBTScan()
//        }
//        bt_stopscan.setOnClickListener {
//            FlyBluetoothManager.getInstance().stopBTScan()
//        }
//        try_connect.setOnClickListener {
//            FlyBluetoothManager.getInstance().tryConnectDevice("8035c163902e")
//        }
//    }
}