package com.ppx.dailystudy.chap11lbs

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_location.*
import java.lang.StringBuilder

/**
 * Author: LuoXia
 * Date: 2020/10/11 16:48
 * Description: 百度地图的定位Demo
 */
class LocationActivity : AppCompatActivity() {

    private lateinit var mLocationClient: LocationClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

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
        mLocationClient.start()
    }

    inner class MyLocationListener : BDLocationListener {
        override fun onReceiveLocation(p0: BDLocation?) {
            val sb = StringBuilder()
            sb.append("纬度：${p0?.latitude}，经度：${p0?.longitude}，定位方式：")
            if (p0?.locType == BDLocation.TypeGpsLocation) {
                sb.append("GPS定位")
            } else {
                sb.append("网络定位")
            }
            tv_show_location.text = sb
        }

    }
}