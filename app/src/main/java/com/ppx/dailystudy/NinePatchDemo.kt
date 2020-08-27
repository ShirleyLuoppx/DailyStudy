package com.ppx.dailystudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppx.dailystudy.chatview.ChatAdapter
import com.ppx.dailystudy.chatview.ChatType
import com.ppx.dailystudy.chatview.ChatViewBean
import kotlinx.android.synthetic.main.nide_patch.*

/**
 * Author: luoxia
 * Date: 2020/8/24 22:45
 * Description: .9图测试
 */
class NinePatchDemo : AppCompatActivity() {

    private lateinit var chatAdapter: ChatAdapter
    val dataList = mutableListOf<ChatViewBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nide_patch)

        initRV()
        initEvent()
    }

    private fun initRV() {

        val msg1 = ChatViewBean()
        msg1.type = ChatType.TYPE_SEND.type
        msg1.msg = "在搞啥子"

        val msg2 = ChatViewBean()
        msg2.type = ChatType.TYPE_RECIVE.type
        msg2.msg = "写bug啊  你呢"

        val msg3 = ChatViewBean()
        msg3.type = ChatType.TYPE_SEND.type
        msg3.msg = "我也是  哈哈..."

        dataList.add(msg1)
        dataList.add(msg2)
        dataList.add(msg3)

        chatAdapter = ChatAdapter(dataList)

        rv_msg.layoutManager = LinearLayoutManager(this)
        rv_msg.adapter = chatAdapter
    }

    private fun initEvent() {
        bt_send.setOnClickListener {
            val msg = et_msg.text.toString()
            if (msg.isNotEmpty()) {
                val bean = ChatViewBean()
                bean.msg = msg
                bean.type = ChatType.TYPE_SEND.type
                dataList.add(bean)
                chatAdapter.notifyDataSetChanged()
                et_msg.setText("")

                //将界面recyclerview定位到最后一行:scrollToPosition
                rv_msg.scrollToPosition(dataList.size - 1)
            }


        }
    }
}