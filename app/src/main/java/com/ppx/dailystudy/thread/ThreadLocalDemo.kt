package com.ppx.dailystudy.thread

import java.util.*

/**
 * @Author: LuoXia
 * @Date: 2021/9/13 11:19
 * @Description: ThreadLocal线程本地变量demo
 *
 * test
 */
class ThreadLocalDemo {
    lateinit var a: PriorityQueue<Int>

    /**
     * ThreadLocal
     * 实现一个线程本地的存储，也就是说，每个线程都有自己的局部变量。所有线程都共享一个ThreadLocal对象，
     * 但是每个线程在访问这些变量的时候能得到不同的值，每个线程可以更改这些变量并且不会影响其他的线程，并且支持null值。
     */
    private val queueThreadLocal: ThreadLocal<PriorityQueue<TaskItem>> =
        object : ThreadLocal<PriorityQueue<TaskItem>>() {
            override fun initialValue(): PriorityQueue<TaskItem>? {
                return PriorityQueue(5)
            }
        }

    fun get(): PriorityQueue<TaskItem>? {
        return queueThreadLocal.get()
    }

    fun addItem(taskItem: TaskItem) {
        val getPriorityQueue = queueThreadLocal.get()
        getPriorityQueue?.add(taskItem)
    }

    fun removeItem(taskItem: TaskItem) {
        val getPriority = queueThreadLocal.get()
        if (getPriority != null) {
            if (getPriority.contains(taskItem)) {
                getPriority.remove(taskItem)
            }
        }
    }

    fun execTask() {
        val getPriority = queueThreadLocal.get()
        if (getPriority != null) {
            val taskItem = getPriority.poll()
            taskItem.execTask()
        }
    }

}