package com.ppx.dailystudy.contentprovider

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ppx.dailystudy.R

/**
 * Author: luoxia
 * Date: 2020/9/9 10:16
 * Description: 联系人adapter
 */
class ContentResolverAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_sqlite_data, data) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_data, item)
    }

}