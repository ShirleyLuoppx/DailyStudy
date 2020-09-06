package com.ppx.dailystudy.broadcast

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Author: luoxia
 * Date: 2020/9/6 16:06
 * Description: 登录界面：用做广播案例的强制下线demo使用
 */
class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bt_login.setOnClickListener {
            val account = et_account.text.toString()
            val pwd = et_password.text.toString()
            if (account == "admin" && pwd == "123456") {
                startActivity(Intent(this, ForceOffLineActivity::class.java))
                finish()
            } else {
                Log.d("ippx", "onCreate: 登录失败，account:$account，pwd：$pwd")
            }
        }
    }
}