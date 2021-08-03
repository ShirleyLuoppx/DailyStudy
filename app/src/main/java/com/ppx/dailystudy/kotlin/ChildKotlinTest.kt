package com.ppx.dailystudy.kotlin

/**
 * @Author: LuoXia
 * @Date: 2021/8/3 16:57
 * @Description: 子类测试
 */
//如果子类(KtTest)有主构造函数， 则基类必须在主构造函数中立即初始化。
class ChildKotlinTest(name: String) : KtTest(), AbstractInterface {

    override var testName: String = ""

    fun test() {
        val ktTest = KtTest()
    }
}