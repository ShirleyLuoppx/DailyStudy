package com.ppx.dailystudy.material

import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import com.ppx.dailystudy.common.CommonUtils
import kotlinx.android.synthetic.main.activity_chip.*

/**
 *
 * @Author Shirley
 * @Date：2023/9/28
 * @Desc：chip-瀑布式布局 的简单使用
 *
 * Chip-style="@style/Widget.MaterialComponents.Chip.Action"：默认效果，如果不给Chip设置style就会默认使用这种效果-可以点击，点击有水波纹效果，选中没有效果
 * Chip-style="@style/Widget.MaterialComponents.Chip.Filter"：点击后会在文本前面有一个勾，再次点击取消勾
 * Chip-style="@style/Widget.MaterialComponents.Chip.Entry"：文本后有一个×，点击会在文本前展示一个勾，再次点击勾取消
 * Chip-style="@style/Widget.MaterialComponents.Chip.Choice"：选中文本颜色和背景颜色变化
 *
 * ChipGroup：相当于一个ViewGroup，里面用来装Chip，当chip的内容占据超过一行时，默认会实现一个瀑布流式的布局（用来做app的音乐热门类型啊，之类的功能非常实用）
 */
class ChipActivity : BaseActivity() {
    val TAG = "ChipActivity"

    override fun initLayout(): Int {
        return R.layout.activity_chip
    }

    override fun initView() {
        chip_nothing.setOnClickListener {
            CommonUtils.showShortMsg("click chip_nothing ....")
        }

        chip_action.setOnClickListener {
            CommonUtils.showShortMsg("click chip action ....")
        }

        chip_entry.setOnCloseIconClickListener {
            CommonUtils.showShortMsg("click close icon")
            chip_entry.visibility = View.INVISIBLE
        }

        chip_choice.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
//                chip_choice.setBackgroundColor(resources.getColor(R.color.notification_alert_color))
                chip_choice.setTextColor(resources.getColor(R.color.notification_alert_color))
            } else {
//                chip_choice.setBackgroundColor(resources.getColor(R.color.biometric_dialog_accent))
                chip_choice.setTextColor(resources.getColor(R.color.biometric_dialog_accent))
            }
        }
    }
}