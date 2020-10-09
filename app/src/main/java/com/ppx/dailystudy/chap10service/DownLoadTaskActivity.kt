package com.ppx.dailystudy.chap10service

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.widget.Toast

/**
 * Author: LuoXia
 * Date: 2020/9/27 21:21
 * Description: 一个封装过的异步处理操作的类
 */
/**
 * 参数一：需要传入的参数
 * 参数二：进度
 * 参数三：方法的执行结果返回的泛型
 */
class DownLoadTaskActivity(mcontext: Context) : AsyncTask<Unit, Int, Boolean>() {


    private val progressDialog: ProgressDialog = ProgressDialog(mcontext)

    /**
     * 在doInBackground()开始执行前调用，通常用于界面初始化，例如进度条的显示
     */
    override fun onPreExecute() {
        super.onPreExecute()
        progressDialog.show()
    }

    /**
     * 这个方法里面的所有内容都是在子线程中执行，所以用于执行一些耗时操作，例如网络请求等。
     * 因为是子线程，所以不可以进行ui操作
     * 如果要更新ui，可以通过，publishProgress()来更新
     * 任务完成后，可以通过return来返回执行结果，对应  AsyncTask<Unit, Int, Boolean>()   中的第三个参数Unit
     */
    override fun doInBackground(vararg params: Unit?): Boolean {

        mHandler.sendEmptyMessageDelayed(1, 1000)

        return true
    }

    /**
     * 当在doInBackground()中调用 publishProgress() 之后，就会进入到这个方法了，且传入的参数就是这个方法的参数，可以用于更新ui
     */
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        progressDialog.setMessage("当前进度：$values %")
    }

    /**
     * 当doInBackground()执行完且通过return返回了结果后，会调用onPostExecute()，参数对应
     * 该方法 用于取消进度条等
     */
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        progressDialog.cancel()
    }

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            var i = 0
            if (msg.what == 1) {
                if (i < 100) {
                    i += 1
                    publishProgress(i)
                }
            }
        }
    }
}