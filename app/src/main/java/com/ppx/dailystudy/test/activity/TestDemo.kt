package com.ppx.dailystudy.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activiyu_timepicker_test.*

/**
 * Author: LuoXia
 * Date: 2020/11/5 9:18
 * Description: 111
 */
class TestDemo : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiyu_timepicker_test)

        timepicker.setOnTimeChangedListener { view, hourOfDay, minute ->

        }
    }
}