package com.ppx.dailystudy.chap11lbs

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.baidu.mapapi.SDKInitializer
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.map.MyLocationData
import com.baidu.mapapi.model.LatLng
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_location.*
import java.lang.StringBuilder

/**
 * Author: LuoXia
 * Date: 2020/10/11 16:48
 * Description: 百度地图以及定位Demo
 */
class LocationActivity : AppCompatActivity() {


    private lateinit var mLocationClient: LocationClient
    private lateinit var mBaiDuMap: BaiduMap
    private lateinit var baidu_mapview: MapView
    private var isFirstLocate: Boolean = true

    private val TAG = "LocationActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SDKInitializer.initialize(applicationContext)
        setContentView(R.layout.activity_location)

        baidu_mapview = findViewById(R.id.baidu_mapview)

        checkPermission()
    }

    /**
     * 将百度地图定位到指定经纬度
     * 需要注意的是：我在调用这个sdk去定位的时候，连接的公司的wifi，定位出来的地点跟实际偏差还比较大，已经跨区了，但是当我换成流量的时候，偏差就缩小到几百米
     */
    private fun navigateTo(bdLocation: BDLocation) {
        if (isFirstLocate) {
            mBaiDuMap = baidu_mapview.map
            //将百度地图的允许显示“我”的那个东西
            mBaiDuMap.isMyLocationEnabled = true

            val latLng = LatLng(bdLocation.latitude, bdLocation.longitude)
            var mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng)
            mBaiDuMap.animateMapStatus(mapStatusUpdate)

            //将地图进行缩放，float类型的值，范围在3-19，值越大，数据越细
            mapStatusUpdate = MapStatusUpdateFactory.zoomBy(12.5f)
            mBaiDuMap.animateMapStatus(mapStatusUpdate)
            isFirstLocate = false
        }

        val builder = MyLocationData.Builder()
        builder.latitude(bdLocation.latitude)
        builder.longitude(bdLocation.longitude)

        val myLocationData = builder.build()
        mBaiDuMap.setMyLocationData(myLocationData)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: $baidu_mapview")
        baidu_mapview.onResume()
    }

    override fun onPause() {
        super.onPause()
        baidu_mapview.onPause()
    }

    private fun checkPermission() {
        mLocationClient = LocationClient(this)
        mLocationClient.registerLocationListener(MyLocationListener())

        var permissionList = arrayListOf<String>()
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE)
        }

        if (permissionList.isNotEmpty()) {
            val strings: Array<String> = permissionList.toTypedArray()
            ActivityCompat.requestPermissions(this, strings, 1)
        } else {
            requestLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty()) {
                for (grantResult in grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "权限被拒绝了", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                requestLocation()
            }
        }
    }

    private fun requestLocation() {
        initLocation()
        mLocationClient.start()
    }

    private fun initLocation() {
        val locationClientOption = LocationClientOption()

        //每5s更新一次当前的位置
        locationClientOption.scanSpan = 5000

        /**
         * 可以通过locationClientOption的locationMode强制设置定位方式，有三种定位值
         * 1、Hight_Accuracy： 高精度模式，也是默认的，会优先使用精确度更高的Gps定位，如果Gps获取不到会自动转换为蓝牙 或者网络定位
         * 2、Battery_Saving：省电模式，也就是网络定位模式
         * 3、Device_Sensors：设备传感器模式，也就是Gps模式，室内一般是接收不到Gps信号的
         */
//        locationClientOption.locationMode = LocationClientOption.LocationMode.Device_Sensors//强行转换为Gps获取位置

        //设置是否可以直接获取到地址信息
        locationClientOption.setIsNeedAddress(true)

        mLocationClient.locOption = locationClientOption
    }

    inner class MyLocationListener : BDLocationListener {
        override fun onReceiveLocation(p0: BDLocation?) {
            val sb = StringBuilder()
            sb.append("纬度：${p0?.latitude} \n 经度：${p0?.longitude} \n 定位方式：")
            if (p0?.locType == BDLocation.TypeGpsLocation) {
                sb.append("GPS定位  \n")
            } else {
                sb.append("网络定位 \n")
            }
            sb.append("国家：${p0?.country}  \n")
            sb.append("省：${p0?.province}  \n")
            sb.append("市：${p0?.city}  \n")
            sb.append("区：${p0?.district}  \n")
            sb.append("街道：${p0?.street}  \n")
            tv_show_location.text = sb

            Log.d(TAG, "onReceiveLocation: 地址：$sb")//这个定位不准确啊，给我定到白云区去了

            if (p0?.locType == BDLocation.TypeGpsLocation || p0?.locType == BDLocation.TypeNetWorkLocation) {
                navigateTo(p0)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        //activity被销毁的时候需要停止mLocationClient，要不然定位会在后台一直运行严重消耗手机电量
        mLocationClient.stop()
        baidu_mapview.onDestroy()
        //将百度地图的取消显示“我”的那个东西
        mBaiDuMap.isMyLocationEnabled = false
    }
}