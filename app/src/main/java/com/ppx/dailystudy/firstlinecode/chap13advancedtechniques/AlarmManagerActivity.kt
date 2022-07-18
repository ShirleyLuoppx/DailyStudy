package com.ppx.dailystudy.firstlinecode.chap13advancedtechniques

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R

/**
 * Author: LuoXia
 * Date: 2020/10/27 16:56
 * Description: 使用AlarmManager发起一个定时任务
 */
class AlarmManagerActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_manager)
    }
}