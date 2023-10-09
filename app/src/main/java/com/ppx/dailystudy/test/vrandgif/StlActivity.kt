package com.ppx.dailystudy.test.vrandgif

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import com.study.xuan.gifshow.widget.stlview.callback.OnReadCallBack
import com.study.xuan.gifshow.widget.stlview.widget.STLView
import com.study.xuan.gifshow.widget.stlview.widget.STLViewBuilder

/**
 *
 * @Author Shirley
 * @Date：2023/10/9
 * @Desc：stl 方式的vr
 */
class StlActivity : BaseActivity() {

    private var mStl: STLView? = null
    private var mContext: Context? = null
    private var mBar: ProgressDialog? = null
    private val bundle = Bundle()
    private val handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val bundle = msg.data
            val cur = bundle.getFloat("cur")
            val total = bundle.getFloat("total")
            val progress = cur / total
            Log.i("Progress", progress.toString() + "")
            mBar!!.progress = (progress * 100.0f).toInt()
        }
    }

    override fun initLayout(): Int {
        return R.layout.activity_stl
    }

    override fun initView() {
        mContext = this
        mStl = findViewById(R.id.stl) as STLView
        mBar = prepareProgressDialog(mContext as StlActivity)
        mStl?.setOnReadCallBack(object : OnReadCallBack() {
            override fun onStart() {
                Toast.makeText(mContext, "开始解析!", Toast.LENGTH_LONG).show()
                mBar!!.show()
            }

            override fun onReading(cur: Int, total: Int) {
                bundle.putFloat("cur", cur.toFloat())
                bundle.putFloat("total", total.toFloat())
                val msg = Message()
                msg.data = bundle
                handler.sendMessage(msg)
            }

            override fun onFinish() {
                mBar!!.dismiss()
            }
        })
        STLViewBuilder.init(mStl).Assets(this, "bai.stl").build()
        mStl?.setTouch(true)
        mStl?.setScale(true)
        mStl?.setRotate(true)
        mStl?.setSensor(true)
    }

    private fun prepareProgressDialog(context: Context): ProgressDialog? {
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle(R.string.stl_load_progress_title)
        progressDialog.max = 100
        progressDialog.setMessage(context.getString(R.string.stl_load_progress_message))
        progressDialog.isIndeterminate = false
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog.setCancelable(false)
        return progressDialog
    }
}