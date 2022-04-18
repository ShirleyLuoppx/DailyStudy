package com.ppx.dailystudy.bluetooth

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothA2dp
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.BOND_NONE
import android.bluetooth.BluetoothProfile
import android.bluetooth.BluetoothProfile.ServiceListener
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_blue_tooth.*
import java.lang.reflect.Method

/**
 * Author: luoxia
 * Date: 2020/8/13 14:11
 * Description: 获取周围蓝牙设备列表测试类
 */
class BlueToothTest : AppCompatActivity() {

    private val TAG: String = "BlueToothTest"
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var devicesList = mutableListOf<BluetoothDevice>()
    private var blueToothListAdapter: BlueToothListAdapter = BlueToothListAdapter(mutableListOf())

    private var mA2dp: BluetoothProfile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue_tooth)

        checkPermission()
        devicesList.clear()
        initEvent()
    }

    /**
     * 获取权限
     * 这步很重要！！！没有获取到位置权限的话，就会获取不到周围的设备数据
     */
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查自身的某种权限
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //已经允许过位置请求权限
                //初始化蓝牙设备
                initBlueTooth()
            } else {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
            }
        }
    }

    private fun initEvent() {
        bt_search_again.setOnClickListener {
            devicesList.clear()
            searchDevices()
        }
    }

    /**
     * 请求权限的结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 2) {
            permissions.forEachIndexed { index, it ->
                when (it) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                            initBlueTooth()
                        }
                    }
                }
            }
        }
    }


    /**
     * 初始化蓝牙
     */
    private fun initBlueTooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show()
        } else {
            //启动蓝牙
            if (!bluetoothAdapter.isEnabled) {
                //如果没有开启蓝牙 的话 就强行启动....
                bluetoothAdapter.enable()
            }
            Log.d(TAG, "蓝牙已启动")

            searchDevices()
        }
    }


    /**
     * 搜索设备
     */
    private fun searchDevices() {
        val filter = IntentFilter()

        filter.apply {
            addAction("android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED")
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

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun onReceive(context: Context, intent: Intent) {
            val action: String? = intent.action
            when (action) {
                //A2DP播放状态改变
                BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED -> {
                    val state = intent.getIntExtra(
                        BluetoothA2dp.EXTRA_STATE,
                        BluetoothA2dp.STATE_NOT_PLAYING
                    )
                    Log.i(TAG, "ACTION_PLAYING_STATE_CHANGED--connect state=$state")
                }

                /**
                 * 搜索蓝牙的广播
                 */
                BluetoothDevice.ACTION_FOUND -> {
                    // Discovery has found a device. Get the BluetoothDevice object and its info from the Intent.
                    val device: BluetoothDevice =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device.name
                    val deviceHardwareAddress = device.address // MAC address
                    devicesList.add(device)

                    initRV(devicesList)
                    Log.d(
                        TAG,
                        "搜索到的蓝牙设备信息：deviceName：$deviceName，deviceHardwareAddress：$deviceHardwareAddress"
                    )
                }
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED -> {
                    Log.d(TAG, "搜索完成，当前周围有设备：${devicesList.size}台")
                }
                BluetoothAdapter.ACTION_DISCOVERY_STARTED -> {
                    Log.d(TAG, "正在扫描..........")
                }
                "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED" -> {
                    val device: BluetoothDevice =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    Log.d(TAG, "当前的a2dp的链接状态：${mA2dp?.getConnectionState(device)}")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initRV(data: MutableList<BluetoothDevice>) {

        //设置布局类型
        rv_blue_tooth.layoutManager = LinearLayoutManager(this)
        blueToothListAdapter = BlueToothListAdapter(data)
        rv_blue_tooth.adapter = blueToothListAdapter

        blueToothListAdapter.setOnItemClickListener { adapter, view, position ->
            initAdapterEvent(adapter, position)
        }

        list_view.visibility = View.GONE
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun initAdapterEvent(
        adapter: BaseQuickAdapter<*, *>,
        position: Int
    ) {
        //连接前先关闭搜索
        bluetoothAdapter?.cancelDiscovery()
        mConnectDevice = devicesList[position]

        mConnectDevice.fetchUuidsWithSdp()

        /**
         * 绑定设备
         * 一：反射
         * 二：官方API createBond，API>19 4.4
         * 目前车机系统版本6 API23，测试手机系统版本9 28
         */

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Log.d(TAG, "initAdapterEvent1: 绑定，${mConnectDevice.createBond()}")
        } else {
            val method: Method =
                BluetoothDevice::class.java.getMethod("createBond", BluetoothDevice::class.java)
            Log.d(TAG, "initAdapterEvent2: ${method.invoke(mConnectDevice)}")
        }

        Log.d(TAG, "当前点击的设备名称：${mConnectDevice.name}，MAC:${mConnectDevice.address}")
        bluetoothAdapter?.getProfileProxy(this, mListener, 11)
    }

    /**
     * 通过监听回调来获取a2dp对象
     */
    private val mListener: ServiceListener = object : ServiceListener {
        override fun onServiceDisconnected(profile: Int) {
            if (profile == BluetoothProfile.A2DP) {
                mA2dp = null
                Log.d(TAG, "ok==========连接失败")
            }
        }

        override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
            if (profile == 11) {
                mA2dp = proxy  //转换
                connectA2dp(mConnectDevice)
            }
        }
    }

    //要操作的设备
    private lateinit var mConnectDevice: BluetoothDevice

    /**
     * A2DP连接
     */
    private fun connectA2dp(device: BluetoothDevice) {
        //设置priority
//        setPriority(device, 100)
        try {
            val clas =
                ClassLoader.getSystemClassLoader().loadClass("android.bluetooth.BluetoothA2dpSink")
            val connmethod = clas.getMethod("connect", BluetoothDevice::class.java)

            connmethod.invoke(mA2dp, device)
            Log.d(TAG, "connectA2dp: clas:${clas.name}")
        } catch (e: Exception) {
            e.printStackTrace()
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

    /**
     * 设置优先级
     */
    fun setPriority(device: BluetoothDevice?, priority: Int) {
        if (mA2dp == null) return
        try { //通过反射获取BluetoothA2dp中setPriority方法（hide的），设置优先级
            val connectMethod: Method = BluetoothA2dp::class.java.getMethod(
                "setPriority",
                BluetoothDevice::class.java, Int::class.javaPrimitiveType
            )
            connectMethod.invoke(mA2dp, device, priority)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        bluetoothAdapter?.cancelDiscovery()
    }
}