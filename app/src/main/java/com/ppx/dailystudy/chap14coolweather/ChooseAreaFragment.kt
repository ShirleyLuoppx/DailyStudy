package com.ppx.dailystudy.chap14coolweather

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ppx.dailystudy.R
import com.ppx.dailystudy.chap14coolweather.util.HttpUtil
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Author: LuoXia
 * Date: 2020/11/8 15:48
 * Description: 显示省市县的数据
 */
class ChooseAreaFragment : Fragment() {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.choose_area, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    /**
     * 根据传输的地址从服务器获取数据
     */
    fun queryFromServer(address: String, type: String) {

        showDialog()

        HttpUtil.sendOkHttpRequest(address, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity?.runOnUiThread {
                    closeDialog()
                    Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {

            }

        })
    }

    /**
     * 开启弹框
     */
    fun showDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog.setMessage("正在加载...")
            progressDialog.setCanceledOnTouchOutside(false)
        }
        progressDialog.show()
    }

    /**
     * 关闭弹框
     */
    fun closeDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss()
        }
    }

}