import android.widget.Toast
import com.ppx.dailystudy.MyApplication
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

/**
 * Author: LuoXia
 * Date: 2020/9/23 23:03
 * Description: 网络连接封装类
 */
object HttpUtil {

    fun sendOkHttpConnection(address: String, callback: Callback) {
        val client = OkHttpClient()
        val request = Request.Builder().url(address).build()
        client.newCall(request).enqueue(callback)
    }

    /**
     * 封装了一个httpuriconnection的网络连接
     */
    fun sendHttpRequest(address: String, httpCallBackListener: HttpCallBackListener): String {
        var connection: HttpURLConnection? = null
        var bufferReader: BufferedReader? = null
        val stringBuilder = StringBuilder()

        if (!isNetWork()){
            Toast.makeText(MyApplication.getContext(),"网络不可用",Toast.LENGTH_SHORT).show()
        }

        Thread(object : Runnable {
            override fun run() {

                try {
                    val url = URL(address)
                    connection = url.openConnection() as HttpURLConnection
                    connection?.apply {
                        readTimeout = 8000
                        connectTimeout = 8000
                        //也没有设置post/get的方法了，估计是太就远了这个框架，已经废弃了很多了函数了
                        bufferReader = BufferedReader(InputStreamReader(getInputStream()))
                    }

                    val str = bufferReader?.readLine().toString()
                    if (str != null) {
                        stringBuilder.append(str)
                    }
                    if (httpCallBackListener != null) {
                        httpCallBackListener.onSuccess(stringBuilder.toString())
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                    httpCallBackListener.onError(e)
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

            }
        }).start()
        return stringBuilder.toString()
    }

    fun isNetWork() : Boolean{
        /**
         * 具体实现
         */
        return true
    }
}