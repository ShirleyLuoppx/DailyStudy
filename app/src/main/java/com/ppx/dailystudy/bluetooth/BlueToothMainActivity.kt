package com.ppx.dailystudy.bluetooth

import android.Manifest
import android.app.ProgressDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_blue_tooth.*
import java.lang.reflect.Field
import java.lang.reflect.Method


/**
 * Author: luoxia
 * Date: 2020/8/13 9:51
 * Description: 蓝牙预研主类
 */
class BlueToothMainActivity : AppCompatActivity() {
    private val mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private val mBlueList: MutableList<BluetoothDevice> = ArrayList()
    private var lisetView: ListView? = null
    private var textView1: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue_tooth)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),2)
        }

        lisetView = findViewById<View>(R.id.list_view) as ListView
        textView1 = findViewById<View>(R.id.textView1) as TextView
        Log.e(
            TAG,
            "onCreate: GPS是否可用：" + isGpsEnable(this)
        )
        init()
        initEvent()
    }

    private fun initEvent() {
        bt_search_again.setOnClickListener {
            startScanBluth()
        }
    }

    /**
     * 判断蓝牙是否开启
     */
    private fun init() {
        // 判断手机是否支持蓝牙
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "设备不支持蓝牙", Toast.LENGTH_SHORT).show()
            finish()
        }
        // 判断是否打开蓝牙
        if (!mBluetoothAdapter!!.isEnabled) {
            //弹出对话框提示用户是后打开
            val intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(intent, SEARCH_CODE)
        } else {
            // 不做提示，强行打开
            mBluetoothAdapter.enable()
        }
        startDiscovery()
        Log.e(TAG, "startDiscovery: 开启蓝牙")
    }

    /**
     * 注册异步搜索蓝牙设备的广播
     */
    private fun startDiscovery() {
        // 找到设备的广播
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        // 注册广播
        registerReceiver(receiver, filter)
        // 搜索完成的广播
        val filter1 = IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        // 注册广播
        registerReceiver(receiver, filter1)
        Log.e(TAG, "startDiscovery: 注册广播")
        startScanBluth()
    }

    /**
     * 广播接收器
     */
    private val receiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            // 收到的广播类型
            val action = intent.action
            Log.d(TAG,"广播类型：${action}")
            // 发现设备的广播
            if (BluetoothDevice.ACTION_FOUND == action) {
                // 从intent中获取设备
                val device =
                    intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                // 没否配对
                if (device.bondState != BluetoothDevice.BOND_BONDED) {
                    if (!mBlueList.contains(device)) {
                        mBlueList.add(device)
                    }
                    textView1!!.text =
                        "附近设备：" + mBlueList.size + "个\u3000\u3000本机蓝牙地址：" + bluetoothAddress
                    val adapter = MyAdapter(context, mBlueList)
                    lisetView?.adapter = adapter
                    Log.e(TAG, "onReceive: " + mBlueList.size)
                    Log.e(
                        TAG,
                        "onReceive: " + """${device.name}:${device.address} ：m
"""
                    )
                }
                // 搜索完成
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED == action) {
                // 关闭进度条
                progressDialog!!.dismiss()
                Log.e(TAG, "onReceive: 搜索完成")
                Log.d(TAG,"本机蓝牙地址：${bluetoothAddress}")
            }
        }
    }
    private var progressDialog: ProgressDialog? = null

    /**
     * 搜索蓝牙的方法
     */
    private fun startScanBluth() {
        // 判断是否在搜索,如果在搜索，就取消搜索
        if (mBluetoothAdapter!!.isDiscovering) {
            mBluetoothAdapter.cancelDiscovery()
        }
        // 开始搜索
        mBluetoothAdapter.startDiscovery()
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
        }
        progressDialog!!.setMessage("正在搜索，请稍后！")
        progressDialog!!.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        receiver?.let { unregisterReceiver(it) }
    }// 参数值为true，禁用访问控制检查
    //抛一个总异常省的一堆代码...

    /**
     * 获取本机蓝牙地址
     */
    private val bluetoothAddress: String?
        private get() {
            try {
                val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
                val field: Field = bluetoothAdapter.javaClass.getDeclaredField("mService")
                // 参数值为true，禁用访问控制检查
                field.setAccessible(true)
                val bluetoothManagerService: Any = field.get(bluetoothAdapter) ?: return null
                val method: Method = bluetoothManagerService.javaClass.getMethod("getAddress")
                val address: Any = method.invoke(bluetoothManagerService)
                return if (address != null && address is String) {
                    address
                } else {
                    null
                }
                //抛一个总异常省的一堆代码...
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SEARCH_CODE) {
            startDiscovery()
        }
        Log.e(TAG, "onActivityResult: $requestCode   $resultCode")
    }

    companion object {
        private const val SEARCH_CODE = 0x123
        private const val TAG = "BlueToothMainActivity"

        //gps是否可用(有些设备可能需要定位)
        fun isGpsEnable(context: Context): Boolean {
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val gps =
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val network =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            return if (gps || network) {
                true
            } else false
        }
    }

    /**
     * 查询已配对设备
     * 注意：执行设备发现将消耗蓝牙适配器的大量资源。在找到要连接的设备后，请务必使用 cancelDiscovery() 停止发现，然后再尝试连接。
     * 此外，您不应在连接到设备的情况下执行设备发现，因为发现过程会大幅减少可供任何现有连接使用的带宽
     */
    private fun getBoundDevice(){
        val pairedDevices: Set<BluetoothDevice>? = mBluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address
            Log.d(TAG,"已配对设备的name:$deviceName，MAC address :$deviceHardwareAddress")
        }
    }

    /**
     * 设置设备可被检测到的时间  为300s
     */
    private fun setDeviceDiscoverableTime(){
        val discoverableIntent: Intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).apply {
            putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300)
        }
        startActivity(discoverableIntent)
    }
}