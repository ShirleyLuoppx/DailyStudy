package com.ppx.dailystudy.firstlinecode.chap10service

import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile
import java.lang.Exception

/**
 * Author: LuoXia
 * Date: 2020/9/29 12:45
 * Description: 下载
 */
class DownLoadTask(downLoadListener: DownLoadListener) : AsyncTask<String, Int, Int>() {

    private val TAG = "DownLoadTask"
    private var downLoadListener: DownLoadListener? = null
    private var isPaused: Boolean = false
    private var isCanceled: Boolean = false
    private var lastProgress: Int = 0

    companion object {
        const val TYPE_SUCCESS = 0
        const val TYPE_FAILED = 1
        const val TYPE_PAUSED = 2
        const val TYPE_CANCELED = 3
    }

    init {
        this.downLoadListener = downLoadListener
    }

    override fun doInBackground(vararg params: String?): Int {
        var istream: InputStream? = null
        var saveFile: RandomAccessFile? = null
        var file: File? = null
        try {
            //记录已下载的文件长度，不知道这里的文件长度是指的，文件大小 吗，还是什么长度
            var downLoadLength: Long = 0
            val downLoadUrl = params[0]

            val fileName = downLoadUrl?.substring(downLoadUrl.lastIndexOf("/"))
            val directory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
            file = File(directory + fileName)

            if (file.exists()) {
                downLoadLength = file.length()
                Log.d(TAG, "doInBackground: $downLoadLength")
            }

            var contentLength: Long? = 0
            downLoadUrl?.let {
                contentLength = getContentLength(it)
            }
            //下载字节和文件总字节一致，就是下载完成了
            if (contentLength == downLoadLength) {
                return TYPE_SUCCESS
            } else if (contentLength?.toInt() == 0) {
                return TYPE_FAILED
            }

            //断点下载
            val okHttpClient = OkHttpClient()
            downLoadUrl?.let {
                val request = Request.Builder()
                    .addHeader("RANGE", "bytes=$downLoadLength-")
                    .url(it).build()
                val response = okHttpClient.newCall(request).execute()
                if (response != null) {
                    istream = response.body?.byteStream()
                    saveFile = RandomAccessFile("file", "rw")
                    //跳过已下载的字节
                    saveFile?.seek(downLoadLength)

                    var len = 0
                    var total = 0
                    val b: ByteArray = ByteArray(1024)
                    istream?.read(b)?.let {
                        len = it
                        while (len != -1) {
                            if (isCanceled) {
                                return TYPE_CANCELED
                            } else if (isPaused) {
                                return TYPE_PAUSED
                            } else {
                                total += len
                                saveFile?.write(b, 0, len)
                                contentLength?.let { contentLength ->
                                    {
                                        val progress =
                                            (total + downLoadLength) * 100 / contentLength
                                        publishProgress(progress.toInt())
                                    }
                                }
                            }
                        }
                    }
                    response.body?.close()
                    return TYPE_SUCCESS
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                if (istream != null) {
                    istream?.close()
                }
                if (saveFile != null) {
                    saveFile?.close()
                }
                if (file != null && isCanceled) {
                    file.delete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return TYPE_FAILED
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        val progress = values[0]
        if (progress != null) {
            if (progress > lastProgress) {
                downLoadListener?.onProgress(progress)
                lastProgress = progress
            }
        }
    }

    override fun onPostExecute(result: Int?) {
        super.onPostExecute(result)
        when (result) {
            TYPE_SUCCESS -> {
                downLoadListener?.onSuccess()
            }
            TYPE_FAILED -> {
                downLoadListener?.onFailed()
            }
            TYPE_PAUSED -> {
                downLoadListener?.onPause()
            }
            TYPE_CANCELED -> {
                downLoadListener?.onCanceled()
            }
        }
    }

    fun pause() {
        isPaused = true
    }

    fun cancel() {
        isCanceled = true
    }

    private fun getContentLength(url: String): Long? {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url)
            .build()
        val response = okHttpClient.newCall(request).execute()
        if (response.isSuccessful && response != null) {
            response.body?.let {
                return it.contentLength()
            }
        }
        return 0
    }
}