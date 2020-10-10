package com.ppx.dailystudy.chap10service

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_service.*
import java.security.Permissions

/**
 * Author: LuoXia
 * Date: 2020/10/10 19:39
 * Description: 第十章的下载的Demo
 */
class DownLoadActivity : AppCompatActivity() {
    private val TAG = "DownLoadActivity"
    private var downLoaderBinder: DownLoadService.DownLoadBinder? = null

    private val conn = object : ServiceConnection {
        /**
         * 和服务绑定取消的时候回调
         */
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        /**
         * 和服务绑定成功之后回调
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(TAG, "onServiceConnected: ")
            downLoaderBinder = service as DownLoadService.DownLoadBinder
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)

        val intent = Intent(this, DownLoadService::class.java)
        //开启服务，保证服务一直在后台运行
        startService(intent)
        //绑定服务，以至于能在activity里面调用service的方法
        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        //运行时的权限申请
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }

        initEvent()
    }

    private fun initEvent() {
        Log.d(TAG, "initEvent: ")
//        if (downLoaderBinder == null) {
//            return
//        }
        bt_start_download.setOnClickListener {
            val url =
                "http://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1602342134956&di=133e6c1708b00b4ea4688d07569c0089&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F52%2F52%2F01200000169026136208529565374.jpg"
            downLoaderBinder?.startDownLoad(url)
        }

        bt_pause_download.setOnClickListener {
            downLoaderBinder?.pauseDownLoad()
        }

        bt_cancel_download.setOnClickListener {
            downLoaderBinder?.cancelDownLoad()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: 读写权限被拒绝")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //如果activity被销毁了一定要对服务进行解绑，要不然可能会造成内存泄漏
        unbindService(conn)
    }

}