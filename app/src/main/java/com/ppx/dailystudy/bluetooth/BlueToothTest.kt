package com.ppx.dailystudy.bluetooth

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_blue_tooth.*

/**
 * Author: luoxia
 * Date: 2020/8/13 14:11
 * Description: 获取周围蓝牙设备列表测试类
 */
class BlueToothTest : AppCompatActivity() {

    private val TAG: String = "BlueToothTest"
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val REQUEST_ENABLE_BT = 1
    private var devicesList = mutableListOf<BluetoothDevice>()
    private lateinit var blueToothListAdapter: BlueToothListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue_tooth)

        checkPermission()
        initBlueTooth()
    }

    /**
     * 获取权限
     * 这步很重要，没有获取到位置权限的话，就会获取不到周围的设备数据
     */
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
        }
    }

    /**
     * 初始化蓝牙
     */
    private fun initBlueTooth() {
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show()
        } else {
            //启动蓝牙
            if (!bluetoothAdapter.isEnabled) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            } else {
                Log.d(TAG, "蓝牙已启动")
                searchDevices()
            }
        }
    }


    /**
     * 搜索设备
     */
    private fun searchDevices() {
        val filter = IntentFilter()

        filter.apply {
            //找到设备的广播
            addAction(BluetoothDevice.ACTION_FOUND)
            addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
            // 搜索完成的广播
            addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
            addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
            priority = IntentFilter.SYSTEM_HIGH_PRIORITY
        }
        registerReceiver(receiver, filter)

        if (bluetoothAdapter?.isDiscovering!!) {
            bluetoothAdapter.cancelDiscovery()
        } else {
            //开始搜索
            val findThreadSuccess = bluetoothAdapter.startDiscovery()
            Log.d(TAG, "搜索设备的进程是否已成功启动:$findThreadSuccess")
        }
    }

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            when (action) {
                BluetoothDevice.ACTION_FOUND -> {
                    // Discovery has found a device. Get the BluetoothDevice object and its info from the Intent.
                    val device: BluetoothDevice =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device.name
                    val deviceHardwareAddress = device.address // MAC address
                    devicesList.add(device)
                    initRV(devicesList)
                    Log.d(TAG, "搜索到的蓝牙设备信息：deviceName：$deviceName，deviceHardwareAddress：$deviceHardwareAddress"
                    )
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    Log.d(TAG, "搜索完成，当前周围有设备：${devicesList.size}台")
                }
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                    Log.d(TAG, "正在扫描")
                }
            }
        }
    }

    private fun initRV(data: MutableList<BluetoothDevice>){
        //设置布局类型
        rv_blue_tooth.layoutManager = LinearLayoutManager(this)
        blueToothListAdapter = BlueToothListAdapter(data)
        rv_blue_tooth.adapter = blueToothListAdapter
        list_view.visibility = View.GONE
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT && resultCode == Activity.RESULT_OK) {
            //开始搜索周围蓝牙设备..
            searchDevices()
        }
    }

    /**
     * 查询已配对设备
     * 注意：执行设备发现将消耗蓝牙适配器的大量资源。在找到要连接的设备后，请务必使用 cancelDiscovery() 停止发现，然后再尝试连接。
     * 此外，您不应在连接到设备的情况下执行设备发现，因为发现过程会大幅减少可供任何现有连接使用的带宽
     */
    private fun getBoundDevice() {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
            Log.d(TAG, "已配对设备的name:$deviceName，MAC address :$deviceHardwareAddress")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}