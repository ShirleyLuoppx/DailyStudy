package com.ppx.dailystudy.chap12materialdesign

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ppx.dailystudy.R

/**
 * Author: LuoXia
 * Date: 2020/10/14 17:19
 * Description: cardView的Adapter
 */
class CardViewAdapter(data: MutableList<FruitBean>) :
    BaseQuickAdapter<FruitBean, BaseViewHolder>(R.layout.item_cardview, data) {
    override fun convert(holder: BaseViewHolder, item: FruitBean) {
        holder.setBackgroundResource(R.id.iv_img, item.img)
        holder.setText(R.id.tv_name, item.name)
    }
}