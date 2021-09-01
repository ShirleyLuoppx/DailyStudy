package com.ppx.dailystudy.thread

import android.annotation.SuppressLint
import android.os.AsyncTask

/**
 * @Author: LuoXia
 * @Date: 2021/8/31 16:47
 * @Description: 学习AsyncTask异步任务
 * https://www.cnblogs.com/whoislcj/p/5614937.html
 */
class AsyncTaskDemo {
    var url = "www.xxx.jpg"

    //模拟下载
    fun test() {

        var asyncTask: AsyncTask<String, Int, String> = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<String, Int, String>() {

            //此函数是在任务没被线程池执行之前调用 运行在UI线程中 比如现在一个等待下载进度Progress
            override fun onPreExecute() {
                super.onPreExecute()
                println("onPreExecute")
            }

            //此函数是在任务被线程池执行时调用 运行在子线程中，在此处理比较耗时的操作 比如执行下载
            override fun doInBackground(vararg params: String?): String {
                val url = params[0]
                println("doInBackground  url-->$url")

                for (i in 0..100 step 20) {
                    if (isCancelled) {
                        return ""
                    }
                    try {
                        Thread.sleep(1000)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    publishProgress(i)
                    println("doInBackground progress -->  $i")
                }
                if (isCancelled) {
                    return "download cancel"
                }
                return "downLoad end"
            }

            //此函数是任务在线程池中执行处于Running状态，回调给UI主线程的进度 比如上传或者下载进度
            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate(*values)
                println("onProgressUpdate progress: ${values[0]}")
            }

            //此函数任务在线程池中执行结束了，回调给UI主线程的结果 比如下载结果
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                println("onPostExecute  result:$result")
            }

            //此函数表示任务关闭
            override fun onCancelled() {
                super.onCancelled()
                println("onCancelled")
            }

            //此函数表示任务关闭 返回执行结果 有可能为null
            override fun onCancelled(result: String?) {
                super.onCancelled(result)
                println("onCancelled  result:$result")
            }
        }



        asyncTask.execute(url)
        if (!asyncTask.isCancelled) {
            val isCancel = asyncTask.cancel(true)
            println("AsyncTask isCancel---->$isCancel")
        }
    }
}

//fun main() {
//    AsyncTaskDemo().test()
//}