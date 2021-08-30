package com.ppx.dailystudy.test

import android.util.Log

/**
 * @Author: LuoXia
 * @Date: 2021/8/27 17:47
 * @Description: 专用于测试kt的一些知识点
 */
object KotlinDemo {

    val intArray = listOf(1, 2, 3)

    /**
     * kt 推荐使用filter为不是foreach
     */
    fun filterTest() {
        intArray.filter {
            it == 1
        }.forEach {
            println("filterTest: $it")
        }
    }
}