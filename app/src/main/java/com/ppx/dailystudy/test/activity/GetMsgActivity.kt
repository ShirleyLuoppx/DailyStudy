package com.ppx.dailystudy.test.activity

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ppx.dailystudy.R
import com.ppx.dailystudy.test.MessageBR
import kotlinx.android.synthetic.main.activity_get_msg.*
import okhttp3.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * @Author: LuoXia
 * @Date: 2021/6/25 9:34
 * @Description: 获取短信数据主界面
 * TODO:需求不明确
 * 应该要有两个接口，一个接口用于发送，一个接口用于接收，因为是两个手机
 * 或者说只需要客户端发送接收到的数据给后台就行？
 */
class GetMsgActivity : AppCompatActivity() {

    private val TAG = "GetMsgActivity"
    private var httpUrl = ""
    private var phone = ""
    private var message = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_msg)

        requestPermission()
        clickEvent()
        EventBus.getDefault().register(this)
    }

    private fun clickEvent() {

        btn_edit.setOnClickListener { //可编辑状态
            et_http_address.isFocusable = true
            et_http_address.isFocusableInTouchMode = true
            et_http_address.requestFocus()
        }

        btn_sure.setOnClickListener { //不可编辑状态
            et_http_address.isFocusable = false
            et_http_address.isFocusableInTouchMode = false
        }

        et_http_address.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d(TAG, "onTextChanged: 请求地址：$p0")
                try {
                    httpUrl = p0.toString()
                    sendMsg(phone, message)
                } catch (e: Exception) {
                    Log.d(TAG, "onTextChanged: $e")
                }
            }
        })
    }

    /**
     * 收到短信数据的时候就发送
     */
    private fun sendMsg(phone: String, message: String) {
        //创建网络处理的对象
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .build()

        //post请求来获得数据
        //创建一个RequestBody，存放重要数据的键值对
        val body: RequestBody = FormBody.Builder()
            .add("phone", phone)
            .add("message", message).build()
        //创建一个请求对象，传入URL地址和相关数据的键值对的对象
        val request: Request = Request.Builder()
            .url(httpUrl)
            .post(body).build()

        //创建一个能处理请求数据的操作类
        val call: Call = client.newCall(request)

        //使用异步任务的模式请求数据
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("ippx", "错误信息：$e")
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("ippx", response.body.toString())
            }
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun funName(event: MessageBR.GetMessageBean) {
        message = event.message
        phone = event.phone

        tv_phone_content.text = phone
        tv_message_content.text = message

    }

    private fun requestPermission() {
        Log.i(TAG, "requestPermission")
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
            != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_SMS
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS),
                1001
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1001 -> {
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    Log.i(TAG, "onRequestPermissionsResult granted")
                } else {
                    Log.i(TAG, "onRequestPermissionsResult denied")
                    showWaringDialog()
                }
                return
            }
        }
    }

    private fun showWaringDialog() {
        val dialog: android.app.AlertDialog? = android.app.AlertDialog.Builder(this)
            .setTitle("警告！")
            .setMessage("请前往设置->应用->PermissionDemo->权限中打开相关权限，否则功能无法正常运行！")
            .setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which -> // 一般情况下如果用户不授权的话，功能是无法运行的，做退出处理
                finish()
            }).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}