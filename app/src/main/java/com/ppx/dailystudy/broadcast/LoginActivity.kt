package com.ppx.dailystudy.broadcast

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
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

        val loginSp = getSharedPreferences("loginSp", Context.MODE_PRIVATE)
        if (loginSp.getBoolean("isRemember", false)) {
            var account = loginSp.getString("account", "")
            var pwd = loginSp.getString("pwd", "")
            account?.let { para1 ->
                pwd?.let { para2 ->
                    if (para1.isNotEmpty() && para2.isNotEmpty()) {
                        et_account.setText(account)
                        et_password.setText(pwd)
                        cb_remember_data.isChecked = true
                    }
                }
            }
        }



        bt_login.setOnClickListener {
            val account = et_account.text.toString()
            val pwd = et_password.text.toString()
            val isRemember = cb_remember_data.isChecked

            val edit = loginSp.edit()

            //            val account = et_account.text.toString()
            //            val pwd = et_password.text.toString()
            if (account == "admin" && pwd == "123456") {
                if (isRemember) {//记住密码
                    //保存密码
                    edit.putString("account", account)
                    edit.putString("pwd", pwd)
                    edit.putBoolean("isRemember", isRemember)
                } else {
                    edit.clear()
                }
                edit.apply()

                startActivity(Intent(this, ForceOffLineActivity::class.java))
                finish()
            } else {
                Log.d("ippx", "onCreate: 登录失败，account:$account，pwd：$pwd")
            }

        }
    }
}