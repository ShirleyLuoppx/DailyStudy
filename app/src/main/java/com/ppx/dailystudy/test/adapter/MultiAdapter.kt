package com.ppx.dailystudy.test.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ppx.dailystudy.R
import com.ppx.dailystudy.test.activity.MultiRvDemo.Companion.TYPE_HORIZONTAL
import com.ppx.dailystudy.test.activity.MultiRvDemo.Companion.TYPE_NORMAL
import kotlinx.android.synthetic.main.item_multi_rv.view.*
import kotlinx.android.synthetic.main.item_multi_rv_horizontal.view.*

/**
 * by:LuoXia
 * date:2020/11/25
 * desc:DESC
 */
class MultiAdapter(data: MutableList<DataType>) :
    BaseMultiItemQuickAdapter<MultiAdapter.DataType, BaseViewHolder>(data) {

    override fun convert(holder: BaseViewHolder, item: DataType) {

        addItemType(TYPE_HORIZONTAL, R.layout.item_multi_rv)
        addItemType(TYPE_NORMAL, R.layout.item_multi_rv_horizontal)

        when (item.itemType) {
            TYPE_HORIZONTAL -> {
                Glide.with(context).load(item.url).into(holder.itemView.iv_img)
                holder.itemView.tv_name.text = "apple"
            }
            TYPE_NORMAL -> {
                Glide.with(context).load(item.url).into(holder.itemView.iv_normal_img)
            }
        }
    }


    class DataType(val url: String = "", override val itemType: Int) : MultiItemEntity


}

