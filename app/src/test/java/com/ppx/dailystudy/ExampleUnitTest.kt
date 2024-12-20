package com.ppx.dailystudy

import android.util.Log
import com.ppx.dailystudy.kotlin.KtTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val TAG = "ExampleUnitTest"

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    /**
     * 扩展函数
     */
    fun changeText() {
        println("changeText")
    }

    val ktTest = KtTest()
    @Test
    fun testExtendFun() {
        changeText()
    }
}