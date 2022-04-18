package com.ppx.dailystudy.broadcast

import android.app.Activity

/**
 * Author: luoxia
 * Date: 2020/9/6 16:24
 * Description: activity的管理类
 */
public class ActivityController {

    private var activities: MutableList<Activity> = mutableListOf()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }


    fun finishAllActivity() {
        activities.forEach {
            if (!it.isFinishing) {
                it.finish()
            }
        }
    }
}