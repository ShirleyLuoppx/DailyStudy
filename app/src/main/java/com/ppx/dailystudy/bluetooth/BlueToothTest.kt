package com.ppx.dailystudy.bluetooth

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothA2dp
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
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
import java.util.*


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
    private var blueToothListAdapter: BlueToothListAdapter = BlueToothListAdapter(mutableListOf())

    private lateinit var MY_UUID: UUID

    private  var mA2dp: BluetoothA2dp?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue_tooth)

        checkPermission()
        initEvent()


        //获取A2DP代理对象
        //getProfileProxy并不会直接返回A2DP代理对象，而是通过mListener中回调获取。


    }

    private val mListener: ServiceListener = object : ServiceListener {
        override fun onServiceDisconnected(profile: Int) {
            if (profile == BluetoothProfile.A2DP) {
                mA2dp = null
            }
        }

        override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
            if (profile == BluetoothProfile.A2DP) {
                mA2dp = proxy as BluetoothA2dp //转换
                connectA2dp(mConnectDevice)
            }
        }
    }

    //要操作的设备
    private lateinit var mConnectDevice: BluetoothDevice
    private fun initEvent() {
        Log.d(TAG, "okin======1")
    }

    /**
     * A2DP连接
     */
    private fun connectA2dp(device: BluetoothDevice) {

        setPriority(device, 100) //设置priority
        try {
            //通过反射获取BluetoothA2dp中connect方法（hide的），进行连接。
            val connectMethod: Method = BluetoothA2dp::class.java.getMethod(
                "connect",
                BluetoothDevice::class.java
            )
            connectMethod.invoke(mA2dp, device)
            //获取a2dp连接状态
            Log.d(TAG, "当前的a2dp的链接状态：${mA2dp?.getConnectionState(device)}")
        } catch (e: Exception) {
            e.printStackTrace()
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

    private inner class ConnectThread(device: BluetoothDevice) : Thread() {

//
//        private var mmSocket: BluetoothSocket? = null
//        private var mmDevice: BluetoothDevice? = null
//
//        fun ConnectThread(device: BluetoothDevice) {
//            mmDevice = device
//            var tmp: BluetoothSocket? = null
//            try {
//                tmp = device.createRfcommSocketToServiceRecord(
//                    MY_UUID
//                )
//            } catch (e: IOException) {
//            }
//            mmSocket = tmp
//        }
//
//        override fun run() {
//            name = "ConnectThread"
//            bluetoothAdapter?.cancelDiscovery()
//            try {
//                mmSocket!!.connect()
//            } catch (e: IOException) {
//                try {
//                    mmSocket!!.close()
//                } catch (e2: IOException) {
//                }
//                return
//            }
//            synchronized(this) { mConnectThread = null }
//            connected(mmSocket, mmDevice)
//        }
//
//        fun cancel() {
//            try {
//                mmSocket!!.close()
//            } catch (e: IOException) {
//            }
//        }
//        private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
//            device.createRfcommSocketToServiceRecord(MY_UUID)
//        }
//
//        public override fun run() {
//            // Cancel discovery because it otherwise slows down the connection.
//            bluetoothAdapter?.cancelDiscovery()
//
//            mmSocket?.use { socket ->
//                // Connect to the remote device through the socket. This call blocks
//                // until it succeeds or throws an exception.
//                //通过调用 connect() 发起连接。请注意，此方法为阻塞调用。
//                socket.connect()
//
//                // The connection attempt succeeded. Perform work associated with
//                // the connection in a separate thread.
//                manageMyConnectedSocket(socket)
//            }
//        }
//
//        // Closes the client socket and causes the thread to finish.
//        fun cancel() {
//            try {
//                mmSocket?.close()
//            } catch (e: IOException) {
//                Log.e(TAG, "Could not close the client socket", e)
//            }
//        }
    }

    /**
     * 获取权限
     * 这步很重要！！！没有获取到位置权限的话，就会获取不到周围的设备数据
     */
    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                initBlueTooth()
            } else {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2)
            }
        }
    }

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
            // Device doesn't support Bluetooth
            Toast.makeText(this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show()
        } else {
            //启动蓝牙
            if (!bluetoothAdapter.isEnabled) {
                bluetoothAdapter.enable()
//                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
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
                    Log.d(TAG, "正在扫描")
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

//                val connectThread = ConnectThread(device)
//                MY_UUID = device.address as UUID
//                connectThread.cancel()
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
        //请求连接
        mConnectDevice = adapter.getItem(position) as BluetoothDevice
        mConnectDevice.createBond()

        Log.d(TAG, "当前点击的设备名称：${mConnectDevice.name}，MAC:${mConnectDevice.address}")
//        bluetoothAdapter?.getProfileProxy(this, mListener, BluetoothProfile.A2DP)
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