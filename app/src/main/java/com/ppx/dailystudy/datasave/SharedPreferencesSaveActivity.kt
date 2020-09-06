package com.ppx.dailystudy.datasave

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_file_save.*

/**
 * Author: luoxia
 * Date: 2020/9/6 22:26
 * Description: SharedPreferences存储数据
 */
class SharedPreferencesSaveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_save)

        bt_save_data_to_file.setOnClickListener {
            val spData = getSharedPreferences("spData", Context.MODE_PRIVATE)
            val edit = spData.edit()
            val editData = et_text_file_save.text.toString()
            if (editData.isNotEmpty()) {
                edit.putString("spData", editData)
            } else {
                edit.putString("spData", "default data")
            }
            edit.apply()
        }

        bt_click_to_show_data.setOnClickListener {
            val spData = getSharedPreferences("spData", Context.MODE_PRIVATE)
            val data = spData.getString("spData", "")
            tv_show_data.text = data
        }
    }
}