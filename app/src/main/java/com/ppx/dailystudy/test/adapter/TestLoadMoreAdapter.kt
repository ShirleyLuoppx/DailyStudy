package com.ppx.dailystudy.test.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ppx.dailystudy.R

/**
 * @Author: LuoXia
 * @Date: 2021/2/25 15:37
 * @Description: DESCRIPTION
 */
class TestLoadMoreAdapter(dataList: MutableList<String>) : LoadMoreModule,
    BaseQuickAdapter<String, BaseViewHolder>(
        R.layout.item_test_load_more, dataList
    ) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_content, item)
    }
}