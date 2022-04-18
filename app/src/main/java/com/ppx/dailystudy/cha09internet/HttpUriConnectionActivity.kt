package com.ppx.dailystudy.cha09internet

import HttpCallBackListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_httpurlconnection.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.URL
import java.net.URLConnection

/**
 * Author: luoxia
 * Date: 2020/9/21 18:42
 * Description: 此类主要是用于测试httpuriconnection的
 */
class HttpUriConnectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_httpurlconnection)

        bt_send_request.setOnClickListener {
            sendRequest()
        }
    }

    private fun sendRequestByHttpUtil() {
        val dataString = HttpUtil.sendHttpRequest("", object : HttpCallBackListener {
            override fun onSuccess(dataString: String) {
            }

            override fun onError(e: Exception) {
            }
        })
    }

    private fun sendRequest() {

        var bufferReader: BufferedReader? = null

        Thread(Runnable {
            var connection: URLConnection? = null
            try {
                val url =
                    URL("https://www.baidu.com/s?ie=UTF-8&wd=%E4%BF%AE%E6%94%B9androidstudio%E7%9A%84%E7%B1%BB%E7%9A%84%E6%B3%A8%E9%87%8A%E6%A8%A1%E6%9D%BF")
                connection = url.openConnection()
                connection.apply {
                    readTimeout = 8000
                    connectTimeout = 8000
                    //也没有设置post/get的方法了，估计是太就远了这个框架，已经废弃了很多了函数了
                    bufferReader = BufferedReader(InputStreamReader(getInputStream()))
                }

                val stringBuilder = StringBuilder()
                val str = bufferReader?.readLine().toString()
                if (str != null) {
                    stringBuilder.append(str)
                }

                showText(stringBuilder.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (bufferReader != null) {
                    try {
                        bufferReader?.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                if (connection != null) {
                    //                        connection.dis  没有断开连接的方法了
                }
            }
        }).start()
    }

    private fun showText(content: String) {
        //切回主线程修改ui
        tv_content.post {
            tv_content.text = content
        }
        //或者使用这种方法
//        runOnUiThread {
//            tv_content.text = content
//        }
    }
}