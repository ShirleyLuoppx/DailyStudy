package com.ppx.dailystudy.thread

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

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
     * newFixedThreadPoolTest: pool-1-thread-2
    newFixedThreadPoolTest: pool-1-thread-4
    newFixedThreadPoolTest: pool-1-thread-1
    newFixedThreadPoolTest: pool-1-thread-3
    newFixedThreadPoolTest: pool-1-thread-5
    newFixedThreadPoolTest: pool-1-thread-3
    newFixedThreadPoolTest: pool-1-thread-1
    newFixedThreadPoolTest: pool-1-thread-2
    newFixedThreadPoolTest: pool-1-thread-4
    newFixedThreadPoolTest: pool-1-thread-2
    newFixedThreadPoolTest: pool-1-thread-1
    newFixedThreadPoolTest: pool-1-thread-3
    newFixedThreadPoolTest: pool-1-thread-5
    newFixedThreadPoolTest: pool-1-thread-3
    newFixedThreadPoolTest: pool-1-thread-1
    newFixedThreadPoolTest: pool-1-thread-2
    newFixedThreadPoolTest: pool-1-thread-4
    newFixedThreadPoolTest: pool-1-thread-2
    newFixedThreadPoolTest: pool-1-thread-1
    newFixedThreadPoolTest: pool-1-thread-3
    newFixedThreadPoolTest: pool-1-thread-5
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

    /**
     * 2、通过Executors提供的newCachedThreadPool，创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程
     * 运行结果：可以看出缓存线程池大小是不定值，可以根据需要创建不同数量的线程，在使用缓存线程池时，先查看池中有没有以前创建的线程，
     * 如果有，就复用.如果没有，就新建新的线程加入池中，缓存型池子通常用于执行一些生存期很短的异步型任务
     *
    run: pool-1-thread-64
    run: pool-1-thread-48
    run: pool-1-thread-53
    run: pool-1-thread-60
    run: pool-1-thread-37
    run: pool-1-thread-65
    run: pool-1-thread-25
    run: pool-1-thread-12
    run: pool-1-thread-25
    run: pool-1-thread-60
    run: pool-1-thread-65
    run: pool-1-thread-60
    run: pool-1-thread-37
    run: pool-1-thread-61
    run: pool-1-thread-61
    run: pool-1-thread-60
    run: pool-1-thread-12
    run: pool-1-thread-64
    run: pool-1-thread-48
     */
    fun newCachedThreadPoolTest() {
        val executorService = Executors.newCachedThreadPool()
        for (i in 0..100) {
            val runnable = Runnable { println("run: ${Thread.currentThread().name}") }
            executorService.execute(runnable)
        }
    }

    /**
     * 3、通过Executors提供的newScheduledThreadPool，创建一个定长线程池，支持定时及周期性任务执行
     * 运行结果和newFixedThreadPool类似，不同的是newScheduledThreadPool是延时一定时间之后才执行
     * pool-1-thread-1
    pool-1-thread-4
    pool-1-thread-1
    pool-1-thread-4
    pool-1-thread-4
    pool-1-thread-2
    pool-1-thread-5
    pool-1-thread-5
    pool-1-thread-3
    pool-1-thread-5
    pool-1-thread-5
    pool-1-thread-2
    pool-1-thread-4
    pool-1-thread-4
    pool-1-thread-1
    pool-1-thread-4
    pool-1-thread-4
    pool-1-thread-2
    pool-1-thread-5
    pool-1-thread-3
    pool-1-thread-1

     */
    fun newScheduledThreadPoolOneTimeTest() {
        val scheduledExecutorService = Executors.newScheduledThreadPool(5)
        for (i in 0..20) {
            val runnable = Runnable {
                println(Thread.currentThread().name)
            }
            //schedule(Runnable command,long delay, TimeUnit unit)创建并执行在给定延迟后启用的一次性操作
            scheduledExecutorService.schedule(runnable, 5000, TimeUnit.MILLISECONDS)
        }
    }

    /**
     * 使用newScheduledThreadPool+scheduleAtFixedRate执行延期循环操作
     */
    fun newScheduledThreadPoolDelayCircleTest() {
        val scheduledExecutorService = Executors.newScheduledThreadPool(5)
        for (i in 0..20) {
            val runnable = Runnable {
                println(Thread.currentThread().name)
            }
            //scheduleAtFixedRate(runnable, 5000, 2000, TimeUnit.MILLISECONDS) 5s后第一次执行，之后每隔2s执行一次
            scheduledExecutorService.scheduleAtFixedRate(runnable, 5000, 2000, TimeUnit.MILLISECONDS)
        }
    }

    /**
     * 4、通过Executors提供的newSingleThreadExecutor，创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     *
     * 运行结果：只会创建一个线程，当上一个执行完之后才会执行第二个
     * pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1
    pool-1-thread-1

     */
    fun newSingleThreadExecutorTest() {
        val executorService = Executors.newSingleThreadExecutor()
        for (i in 0..20) {
            val runnable = Runnable {
                println(Thread.currentThread().name)
            }
            executorService.execute(runnable)
        }
    }

    /**
     * 使用newSingleThreadScheduledExecutor创建单个延迟线程池
     */
    fun newSingleThreadScheduledExecutorTest() {
        val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
        val runnable = Runnable {
            println(Thread.currentThread().name)
        }
        scheduledExecutorService.schedule(runnable, 2, TimeUnit.SECONDS)
    }

    /**
     * newSingleThreadScheduledExecutor+scheduleAtFixedRate 创建单个循环延迟线程池
     */
    fun newSingleThreadScheduledExecutorCircleTest() {
        val scheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
        val runnable = Runnable {
            println(Thread.currentThread().name)
        }
        scheduledExecutorService.scheduleAtFixedRate(runnable, 2, 2, TimeUnit.SECONDS)
    }


}

fun main() {
//    ExecutorServiceDemo().newFixedThreadPoolTest()
//    ExecutorServiceDemo().newCachedThreadPoolTest()
//    ExecutorServiceDemo().newScheduledThreadPoolOneTimeTest()
//    ExecutorServiceDemo().newScheduledThreadPoolDelayCircleTest()
//    ExecutorServiceDemo().newSingleThreadExecutorTest()
//    ExecutorServiceDemo().newSingleThreadScheduledExecutorTest()
    ExecutorServiceDemo().newSingleThreadScheduledExecutorCircleTest()
}