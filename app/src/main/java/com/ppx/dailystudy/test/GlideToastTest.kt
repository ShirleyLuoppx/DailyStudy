package com.ppx.dailystudy.test

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_test.*
import java.util.*

/**
 * @Author: LuoXia
 * @Date: 2021/8/18 11:54
 * @Description: 滑动的Toast测试类
 */
open class GlideToastTest : AppCompatActivity() {

    private lateinit var tv_content: TextView
    private lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        ben_show_toast.setOnClickListener { showDialog() }
    }

    /**
     * 模仿GlideToast写一个简单的滑动toast
     * https://github.com/imjeevandeshmukh/GlideToast
     */
    private fun showDialog() {
        dialog = Dialog(this, R.style.ToastDialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.item_toast)
        dialog.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)

        tv_content = dialog.findViewById(R.id.tv_content)
        tv_content.text = "hello everyone i love u guys"
        dialog.show()

        dismissDialogAfterTime()
    }

    private fun dismissDialogAfterTime() {
        //1s后执行timerTask的内容
        Timer().schedule(object : TimerTask() {
            override fun run() {
                dialog.dismiss()
                Log.d("TAG", "run: 66666")
            }
        }, 1000)
    }
}