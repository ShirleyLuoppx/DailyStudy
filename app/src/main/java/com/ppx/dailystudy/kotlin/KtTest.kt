package com.ppx.dailystudy.kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: LuoXia
 * @Date: 2021/8/3 10:41
 * @Description: kotlin测试类
 */
//类中含有一个主构造器 ，和一个或多个次构造器
//在这里constructor可以省略
open class KtTest : AppCompatActivity() {

    var name = "菜鸟教程"

    object DeskTop {
        var url = "www.runoob.com"
        fun showName() {
//            print { "desk legs $name" } // 错误，不能访问到外部类的方法和变量
        }
    }

    init {
        //初始化代码块可以放在这里
    }

    companion object {

    }

    /**
     * 伴生对象的扩展属性
     */
    private val KtTest.Companion.no: Int
        get() = 10

    /**
     * 伴生对象的扩展方法
     */
    private fun KtTest.Companion.testCompanionExtendingFunction() {
        Log.d(TAG, "testCompanionExtendingFunction: 伴生对象的扩展方法")
    }


    private val TAG = "KtTest"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        test(1, 2, 3)

        test()

        test1(8)

        Log.d(TAG, "testCompanionExtendingFunction:no : $no")
        KtTest.testCompanionExtendingFunction()

        useCopy()


        var site = KtTest()
//        site.DeskTop.url // 错误，不能通过外部类的实例访问到该对象
        KtTest.DeskTop.url // 正确

    }

    /**
     * 调用copy改变实体类的参数
     */
    fun useCopy() {
        val bean = DataBean("ppx", 1)
        val newBean = bean.copy(age = 2)
        Log.d(TAG, "useCopy: $bean \n $newBean")
    }


    /**
     * 可变长参数
     */
    fun test(vararg v: Int?): Unit {
        v.forEach {
            Log.d(TAG, "test: $it")
        }
    }

    /**
     * 位操作符
     */
    fun test1(a: Int) {
        //左移位  ，   shl   下相当于java的 <<
        Log.d(TAG, "test1: ${a.shl(1)}") // a * 2的1次方
        Log.d(TAG, "test1: ${a.shl(2)}") // a * 2的2次方
        Log.d(TAG, "test1: ${a.shl(3)}") // a * 2的3次方

        //右移位 ，   shl   下相当于java的 >>
        Log.d(TAG, "test1: ${a.shr(1)}") // a / 2的1次方
        Log.d(TAG, "test1: ${a.shr(2)}") // a / 2的2次方
        Log.d(TAG, "test1: ${a.shr(3)}") // a / 2的3次方

        //长字符串  trimMargin：删除多余空白
        val a = """  sdsdfsdfsdf 
            |
            |qweqwe请问下
        """.trimMargin()
    }


    private var no: Int = 0
        get() = field
        set(value) {
            field = if (value > 10) {
                -1
            } else {
                value
            }
        }


//    private var name: String = name
//        get() = field.toUpperCase()

    /**
     * 嵌套类
     */
    class TestClass {
        //嵌套类不能访问外部类的方法/属性
        val testClass = "TestClass"
    }

    /**
     * 测试嵌套类
     */
    fun testClass() {
        //访问嵌套类的方法/属性，须嵌套类中的方法/属性是public的
        Log.d(TAG, "testClass: ${KtTest.TestClass().testClass}")
    }

    /**
     * 内部类
     */
    inner class testInnerClass {
        val name1 = "name"
        fun test() {
            Log.d(TAG, "test: 内部类访问外部类成员变量:$name1")
        }
    }

    /**
     * 测试内部类
     */
    fun testInnerClassFun() {
        KtTest().testInnerClass().test()
    }

}