package com.ppx.dailystudy.chap13advancedtechniques

import android.util.Log

/**
 * Author: LuoXia
 * Date: 2020/10/26 16:18
 * Description: 日志工具类：防止发布软件后代码中的打印数据被泄露
 * 开发的时候就让level=0，就可以打印所有日式了。上线的时候就让level=NOTHING，就可以屏蔽所有日志了
 */
class LogUtil {
    companion object {
        const val VERBOSE = 0
        const val DEBUG = 1
        const val INFO = 2
        const val WARM = 3
        const val ERROR = 4
        private const val NOTHING = 5

        const val level = NOTHING
    }

    fun v(tag: String, msg: String) {
        if (level <= VERBOSE) {
            Log.v(tag, "v: $msg")
        }
    }

    fun d(tag: String, msg: String) {
        if (level <= DEBUG) {
            Log.d(tag, "v: $msg")
        }
    }

    fun i(tag: String, msg: String) {
        if (level <= INFO) {
            Log.i(tag, "v: $msg")
        }
    }

    fun w(tag: String, msg: String) {
        if (level <= WARM) {
            Log.w(tag, "v: $msg")
        }
    }

    fun e(tag: String, msg: String) {
        if (level <= ERROR) {
            Log.e(tag, "v: $msg")
        }
    }


}