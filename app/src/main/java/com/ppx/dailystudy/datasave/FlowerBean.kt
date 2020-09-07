package com.ppx.dailystudy.datasave

import org.litepal.crud.LitePalSupport

/**
 * Author: luoxia
 * Date: 2020/9/7 16:35
 * Description: 使用litepal的时候，这就相当于是一张表，表名叫FlowerBean，列名有name和number
 */
class FlowerBean(
    var name: String = "",
    var number: Int = 0
) : LitePalSupport()

data class GrassBean(
    var name: String = "",
    var number: Int = 0,
    var type: String = ""
) : LitePalSupport()