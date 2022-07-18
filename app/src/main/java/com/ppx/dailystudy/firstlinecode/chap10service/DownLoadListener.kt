package com.ppx.dailystudy.firstlinecode.chap10service

/**
 * Author: LuoXia
 * Date: 2020/9/29 12:25
 * Description: 下载接口
 */
interface DownLoadListener {
    fun onProgress(progress:Int)
    fun onSuccess()
    fun onFailed()
    fun onPause()
    fun onCanceled()
}