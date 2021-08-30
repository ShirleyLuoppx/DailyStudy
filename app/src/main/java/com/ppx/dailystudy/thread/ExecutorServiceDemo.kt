package com.ppx.dailystudy.thread

import android.util.Log
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @Author: LuoXia
 * @Date: 2021/8/30 17:13
 * @Description: 学习ExecutorService线程池相关内容
 * https://www.cnblogs.com/whoislcj/p/5607734.html
 */

/**
 *
 * 为什么要引入线程池？
1.）new Thread()的缺点
每次new Thread()耗费性能
调用new Thread()创建的线程缺乏管理，被称为野线程，而且可以无限制创建，之间相互竞争，会导致过多占用系统资源导致系统瘫痪。
不利于扩展，比如如定时执行、定期执行、线程中断
2.）采用线程池的优点
重用存在的线程，减少对象创建、消亡的开销，性能佳
可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞
提供定时执行、定期执行、单线程、并发数控制等功能
 */
class ExecutorServiceDemo {
    private val TAG = "ExecutorServiceDemo"

    /**
     * 通过Executors提供四种线程池，newFixedThreadPool、newCachedThreadPool、newSingleThreadExecutor、newScheduledThreadPool。
     */

    /**
     * 1、通过Executors提供的newFixedThreadPool，创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程
     * 运行结果：总共只会创建5个线程， 开始执行五个线程，当五个线程都处于活动状态，再次提交的任务都会加入队列等到其他线程运行结束，当线程处于空闲状态时会被下一个任务复用
     */
    fun newFixedThreadPoolTest() {
        val executorService = Executors.newFixedThreadPool(5)


        for (i in 0..20) {
            val runnable = object : Runnable {
                override fun run() {
                    println("newFixedThreadPoolTest: ${Thread.currentThread().name}")
                }
            }
            executorService.execute(runnable)
        }
    }


}

fun main() {
    ExecutorServiceDemo().newFixedThreadPoolTest()
}