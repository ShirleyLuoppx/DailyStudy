package com.ppx.dailystudy.firstlinecode.chap14coolweather.db

import org.litepal.crud.LitePalSupport

/**
 * Author: LuoXia
 * Date: 2020/11/7 20:14
 * Description: 城市实体
 */

/**
 * 省实体
 * 省id、省名称、省代号
 */
data class Province(
    var id: Int = -1,
    var name: String = "",
    var provinceCode: Int = -1
) : LitePalSupport()

/**
 * 市实体
 * 市id、市名称、市代号、该市对应的省id
 */
data class City(
    var cityId: Int = -1,
    var cityName: String = "",
    var cityCode: Int = -1,
    var provinceId: Int = -1
) : LitePalSupport()

/**
 * 县实体
 * 县id、县名称、该县对应的市id、该县的天气id
 */
data class County(
    var countyId: Int = -1,
    var countyName: String = "",
    var cityId: Int = -1,
    var weatherId: String = ""
) : LitePalSupport()