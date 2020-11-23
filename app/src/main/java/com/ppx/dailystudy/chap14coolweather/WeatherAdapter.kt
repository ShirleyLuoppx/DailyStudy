package com.ppx.dailystudy.chap14coolweather

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ppx.dailystudy.R

/**
 * by:LuoXia
 * date:2020/11/22
 * desc:城市适配器
 */
class WeatherAdapter(data: MutableList<String>) :
    BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_weather, data) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_name, item)
    }
}