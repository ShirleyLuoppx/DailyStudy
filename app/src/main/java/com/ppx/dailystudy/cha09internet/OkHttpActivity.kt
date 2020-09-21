import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_okhttp.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Author: LuoXia
 * Date: 2020/9/21 20:38
 * Description: 一个okhttp的简单测试类
 */
class OkHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)

        bt_send_okhttp_request.setOnClickListener {
            sendOkHttpRequest()
        }
    }

    private fun sendOkHttpRequest() {
        Thread(Runnable {
            val okHttpClient = OkHttpClient()

            val getRequest = Request.Builder().url("http://www.baidu.com").build()
            val getResponse = okHttpClient.newCall(getRequest).execute()
            val getStringData = getResponse.body?.string()

            val requestBody = FormBody.Builder()
                .add("name", "ppx")
                .add("pwd", "123123")
                .build()
            val postRequest =
                Request.Builder().url("http://www.baidu.com").post(requestBody).build()
            val postResponse = okHttpClient.newCall(postRequest).execute()

            tv_content.post {
                tv_content.text = postResponse.body?.string()
            }
        }).start()
    }
}