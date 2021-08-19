package com.ppx.dailystudy.test

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_test.*

/**
 * @Author: LuoXia
 * @Date: 2021/8/18 17:42
 * @Description: 仿支付宝小鸡庄园的运动会列表的滑动效果
 */
class GlideDialog : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        ben_show_toast.setOnClickListener { glideFun() }
    }

    private fun glideFun() {
        val dialog = Dialog(this,R.style.GlideTheme)
        dialog.setContentView(R.layout.item_glide)
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.setCancelable(true)
        dialog.window?.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
        dialog.show()
    }
}