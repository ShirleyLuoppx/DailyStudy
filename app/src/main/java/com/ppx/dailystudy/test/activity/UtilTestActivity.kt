package com.ppx.dailystudy.test.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import com.ppx.dailystudy.test.utils.DataAndFileUtils

/**
 * @Author: LuoXia
 * @Date: 2021/7/29 16:46
 * @Description:测试文件转换util
 */
class UtilTestActivity : AppCompatActivity() {
    private val TAG = "UtilTestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testInt2ByteArray1()
    }

    fun testInt2ByteArray1() {
        Log.d(TAG, "testInt2ByteArray: ${DataAndFileUtils.intToByteArray(1)}")
        Log.d(TAG, "testInt2ByteArray1: ${DataAndFileUtils.intToByteArray1(1)}")
        Log.d(TAG, "testInt2ByteArray2: ${DataAndFileUtils.intToByteArray2(1)}")
        Log.d(TAG, "byteArrayToInt: ${DataAndFileUtils.byteArrayToInt(byteArrayOf(1))}")
    }
}