package com.ppx.dailystudy.firstlinecode.chap14coolweather.util

import android.widget.Toast
import com.ppx.dailystudy.MyApplication

/**
 * by:LuoXia
 * date:2020/11/22
 * desc:DESC
 */
object CommonUtil {


    fun showShortMsg(msg: String) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT)
    }

    fun showLongMsg(msg: String) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_LONG)
    }
}