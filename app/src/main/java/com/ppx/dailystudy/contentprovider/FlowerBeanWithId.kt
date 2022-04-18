package com.ppx.dailystudy.contentprovider

import org.litepal.crud.LitePalSupport

/**
 * Author: luoxia
 * Date: 2020/9/9 11:43
 * Description: DESCRIPTION
 */

class FlowerBeanWithId(
    var id: Int = 0,
    var name: String = "",
    var number: Int = 0
) : LitePalSupport()

data class GrassBeanWithId(
    var id: Int = 0,
    var name: String = "",
    var number: Int = 0,
    var type: String = ""
) : LitePalSupport()