package com.ppx.dailystudy.contentprovider

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_content_provider.*
import java.security.Permission


/**
 * Author: luoxia
 * Date: 2020/9/8 10:08
 * Description: 此类用于测试运行时权限的，打电话例子
 */
class CallActivity : AppCompatActivity() {

    private val REQUEST_CODE_CONTACT = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        bt_call_phone.setOnClickListener {
            callPhone()
        }
    }

    /**
     * 打电话了
     */
    private fun callPhone() {
        val intent = Intent()
        intent.data = Uri.parse("tel:10086")

//        android6.0以上需要动态申请权限
        if (Build.VERSION.SDK_INT >= 23) {
            //检查自身权限
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                //如果没有授过权，就去请求授权
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            } else {
                //如果授过权就直接跳转到拨号界面
                startActivity(intent)
            }
        } else {
//            6.0以下就直接跳转到拨号界面
            startActivity(intent)
        }
    }

    /**
     * 调用requestPermissions之后的回调
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone()
                } else {
                    Toast.makeText(this, "refuse", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}