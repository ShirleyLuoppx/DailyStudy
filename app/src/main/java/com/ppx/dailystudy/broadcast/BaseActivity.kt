package com.ppx.dailystudy.broadcast

import android.app.Activity
import android.app.AlertDialog
import android.content.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R

/**
 * Author: luoxia
 * Date: 2020/9/6 16:06
 * Description: DESCRIPTION
 */
open class BaseActivity : AppCompatActivity() {

    private lateinit var forceOffLineBroadCastReceiver: ForceOffLineBroadCastReceiver
    private val forceOffLineIT = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityController().addActivity(this)
    }

    override fun onResume() {
        super.onResume()

        forceOffLineIT.addAction("com.ppx.force.offline")
        forceOffLineBroadCastReceiver = ForceOffLineBroadCastReceiver()
        registerReceiver(forceOffLineBroadCastReceiver, forceOffLineIT)
    }

    override fun onPause() {
        super.onPause()
        if (forceOffLineBroadCastReceiver != null) {
            unregisterReceiver(forceOffLineBroadCastReceiver)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityController().removeActivity(this)
    }

    class ForceOffLineBroadCastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            //关闭掉其他的activity，并跳到登录界面
            val alertDialog = AlertDialog.Builder(context)

            alertDialog.apply {
                setTitle("Info")
                setMessage("您再其他地方有 登录，所以对您进行强制下线！")
                //取消在其他地方点击消失dialog
                setCancelable(false)

                setPositiveButton("确定", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        Log.d("ippx", "onClick: 确定")
                        ActivityController().finishAllActivity()
                        context?.startActivity(Intent(context, LoginActivity::class.java))
                    }
                })

                setNegativeButton("取消", object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                            alertDialog.setOnDismissListener {
                                it.dismiss()
                            }
                        }
                    }
                })

                show()
            }
        }

    }

}