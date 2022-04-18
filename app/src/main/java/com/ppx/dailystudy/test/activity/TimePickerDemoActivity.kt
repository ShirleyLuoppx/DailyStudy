package com.ppx.dailystudy.test.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import com.ppx.dailystudy.chap13advancedtechniques.LongRunningService
import kotlinx.android.synthetic.main.activity_timepicker_demo.*

/**
 * Author: LuoXia
 * Date: 2020/10/27 10:06
 * Description: DESCRIPTION
 */
class TimePickerDemoActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timepicker_demo)

        timepicker.setOnTimeChangedListener { view, hourOfDay, minute ->
            Log.d("ippx", "onCreate: $hourOfDay---$minute")
        }

        startService(Intent(this,LongRunningService::class.java))
    }
}