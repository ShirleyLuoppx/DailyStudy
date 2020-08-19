package com.ppx.dailystudy.bluetooth

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cn.flyaudio.sdk.entity.Contact
import cn.flyaudio.sdk.listener.BluetoothListener
import cn.flyaudio.sdk.manager.FlyBluetoothManager
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_fly_bluetooth.*

/**
 * Author: luoxia
 * Date: 2020/8/17 16:42
 * Description: DESCRIPTION
 */
class FlyBlueToothDemoActivity : AppCompatActivity() {

    private val TAG = "FlyBlueToothDemoActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fly_bluetooth)


        FlyBluetoothManager.getInstance().bluetoothListener = object : BluetoothListener{
            override fun onCallInfo(p0: Int, p1: Contact?) {
            }

            override fun onPlayStatus(p0: Int) {
            }

            override fun onConnectDevice(p0: String?) {
            }

            override fun onConnectStatus(p0: Int) {
            }

            override fun onPowerStatus(p0: Int) {
            }

            override fun onMicStatus(p0: Boolean) {
            }

            override fun onContactDownloadStatus(p0: Int) {
            }

            override fun onPhoneAcceptTypeChanged(p0: Boolean) {
            }

            @SuppressLint("LongLogTag")
            override fun onBtMusicInfo(p0: String?, p1: String?, p2: String?) {
                Log.e(TAG, "onBtMusicInfo: okin==============$p0----$p1")
            }

        }

    }
}