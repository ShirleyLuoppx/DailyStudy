package com.ppx.dailystudy.datasave

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ppx.dailystudy.R

/**
 * Author: luoxia
 * Date: 2020/9/7 15:29
 * Description: sqlite查询出来的数据展示
 */
class SqliteAdapter(data: MutableList<SQLiteActivity.FlowerBean>) :
    BaseQuickAdapter<SQLiteActivity.FlowerBean, BaseViewHolder>(R.layout.item_sqlite_data, data) {

    override fun convert(holder: BaseViewHolder, item: SQLiteActivity.FlowerBean) {
        holder.setText(R.id.tv_data, "花名：" + item.name + "       数量：" + item.number)
    }
}