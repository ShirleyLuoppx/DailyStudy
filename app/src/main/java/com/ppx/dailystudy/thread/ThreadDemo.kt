package com.ppx.dailystudy.thread

import java.lang.Exception
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.locks.ReentrantLock

/**
 * @Author: LuoXia
 * @Date: 2021/8/30 15:09
 * @Description: 学习新建线程的三种方式，同步线程的四种方式
 * https://www.cnblogs.com/whoislcj/p/5603277.html
 */

/**
 *
Thread主要函数
    run()//包含线程运行时所执行的代码

    start()//用于启动线程

    sleep()/sleep(long millis)//线程休眠，交出CPU，让CPU去执行其他的任务，然后线程进入阻塞状态，sleep方法不会释放锁

    yield()//使当前线程交出CPU，让CPU去执行其他的任务，但不会是线程进入阻塞状态，而是重置为就绪状态，yield方法不会释放锁

    join()/join(long millis)/join(long millis,int nanoseconds)//等待线程终止，直白的说 就是发起该子线程的线程 只有等待该子线程运行结束才能继续往下运行

    wait()//交出cpu，让CPU去执行其他的任务，让线程进入阻塞状态，同时也会释放锁

    interrupt()//中断线程，自stop函数过时之后，我们通过interrupt方法和isInterrupted()方法来停止正在运行的线程，注意只能中断已经处于阻塞的线程

    getId()//获取当前线程的ID

    getName()/setName()//获取和设置线程的名字

    getPriority()/setPriority()//获取和这是线程的优先级 一般property用1-10的整数表示，默认优先级是5，优先级最高是10，优先级高的线程被执行的机率高

    setDaemon()/isDaemo()//设置和判断是否是守护线程

    currentThread()//静态函数获取当前线程

Thread线程主要状态
    （1） New  一旦被实例化之后就处于new状态

    （2） Runnable 调用了start函数之后就处于Runnable状态

    （3） Running 线程被cpu执行 调用run函数之后 就处于Running状态

    (4)   Blocked 调用join()、sleep()、wait()使线程处于Blocked状态

    (5)   Dead    线程的run()方法运行完毕或被中断或被异常退出，线程将会到达Dead状态
 */
class ThreadDemo {

    private val TAG = "ThreadDemo"
    private var isRunning = false

    /**
     * 创建thread的方法：1、继承Thread 2、实现Runnable 3、实现callable
     */
    inner class MyThread(name: String?) : Thread(name) {
        override fun run() {
            super.run()

            while (isRunning) {
//                countFun()
//                syncCountCodes()
                syncCountFunByReentrantLock()
            }
        }
    }

    /**
     * 创建thread的方法：2、实现Runnable
     */
    class MyThread2 : Runnable {
        override fun run() {
            TODO("Not yet implemented")
        }
    }

    /**
     * 创建thread的方法：3、实现Callable
     */
    val excutors = Executors.newSingleThreadExecutor()
    val future: Future<String> = excutors.submit(object : Callable<String> {
        override fun call(): String {
            return ""
        }
    })

    var myThread: Thread? = MyThread("线程一")

    /**
     * 销毁线程
     */
    fun destroyThread() {
        try {
            if (null != myThread && Thread.State.RUNNABLE == myThread?.state) {
                Thread.sleep(500)
                myThread?.interrupt()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            myThread = null
        }

    }

    var count = 100

    fun testSync() {
        isRunning = true
        val thread1 = MyThread("线程1")
        val thread2 = MyThread("线程2")
        val thread3 = MyThread("线程3")

        thread1.start()
        thread2.start()
        thread3.start()
    }

    /**
     * 未加同步的代码：打印错乱
     */
    fun countFun() {
        if (count > 0) {
            println("count:  ${Thread.currentThread().name} -->  $count")
            count--
        } else {
            isRunning = false
        }
    }

    /**
     * 加了同步的代码：按顺序打印
     *
     * 线程同步的几种方式：
     * 1、同步代码块
     * 2、同步哈桑农户
     * 3、使用特殊域变量(volatile)实现线程同步 java中是：  private volatile int count = 1000;
     * 4、使用重入锁实现线程同步
     */
    fun syncCountCodes() {
        synchronized(this) {
            if (count > 0) {
                println("count:  ${Thread.currentThread().name} -->  $count")
                count--
            } else {
                isRunning = false
            }
        }
    }

    //kotlin中的貌似只能通过注解的方式 Volatile来实现线程同步 ， java中是使用关键字
    @Volatile
    var a = 1

    /**
     * 同步方法   kotlin好像不能同步方法...如果是java的话,只需要 private synchronized void count() {}
     */
    fun syncCountFun() {
        if (count > 0) {
            println("count:  ${Thread.currentThread().name} -->  $count")
            count--
        } else {
            isRunning = false
        }
    }

    private val reentrantLock = ReentrantLock()

    /**
     * 使用ReentrantLock重入锁实现线程同步
     */
    fun syncCountFunByReentrantLock() {
        //获取锁
        reentrantLock.lock()
        if (count > 0) {
            println("count:  ${Thread.currentThread().name} -->  $count")
            count--
        } else {
            isRunning = false
        }
        //释放锁
        reentrantLock.unlock()
    }


}

fun main() {
    ThreadDemo().testSync()
}