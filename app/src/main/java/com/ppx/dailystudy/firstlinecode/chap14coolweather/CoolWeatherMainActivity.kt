package com.ppx.dailystudy.firstlinecode.chap14coolweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R

/**
 * Author: LuoXia
 * Date: 2020/11/7 20:07
 * Description: 项目开发：酷欧天气首页
 */
class CoolWeatherMainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cool_weather_main)

//        supportFragmentManager.beginTransaction().add(R.id.fragment_choose_area,ChooseAreaFragment()).commit()
    }

}