package com.ppx.dailystudy.chatview

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ppx.dailystudy.R

/**
 * Author: luoxia
 * Date: 2020/8/26 23:26
 * Description: DESCRIPTION
 */
class ChatAdapter(data: MutableList<ChatViewBean>) :
    BaseQuickAdapter<ChatViewBean, BaseViewHolder>(R.layout.item_chat, data) {
    override fun convert(holder: BaseViewHolder, item: ChatViewBean) {
        when (item.type) {
            ChatType.TYPE_RECIVE.type -> {
                holder.setText(R.id.tv_left, item.msg)
                holder.setVisible(R.id.tv_right, false)
                holder.setVisible(R.id.tv_left, true)
            }
            ChatType.TYPE_SEND.type -> {
                holder.setText(R.id.tv_right, item.msg)
                holder.setVisible(R.id.tv_left, false)
                holder.setVisible(R.id.tv_right, true)
            }
        }
    }
}