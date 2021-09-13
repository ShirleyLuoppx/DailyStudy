package com.ppx.dailystudy.thread

import android.util.Log

/**
 * @Author: LuoXia
 * @Date: 2021/9/13 11:22
 * @Description: ThreadLocal需要用到得item实体类
 */
class TaskItem : Comparable<Any> {
    var id: Long = 0L
    var name: String = ""
    var priority: Int = 0

    override fun compareTo(other: Any): Int {
        if (other is TaskItem) {
            val taskItem = other
            if (taskItem.priority > this.priority) {
                return -1
            } else if (taskItem.priority < this.priority) {
                return 1
            }
        }
        return 0
    }

    fun execTask() {
        Log.d("TaskItem", "execTask: id:$id,name:$name")
    }


}