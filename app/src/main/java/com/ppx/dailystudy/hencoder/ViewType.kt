package com.ppx.dailystudy.hencoder

/**
 * @Author: LuoXia
 * @Date: 2021/5/14 14:54
 * @Description: 自定义View的类型关键字
 */
enum class ViewType {
    /**
     * 自定义view1-1 的一些基础api
     */
    BASE_API,
    /**
     * 自定义view  1-1 的fill_type  部分
     */
    FILL_TYPE,
    /**
     * 自定义view  1-2  的paint 部分
     */
    PAINT,
    /**
     * 自定义View 1-3 的Text部分
     */
    TEXT,
    /**
     * 自定义View 1-4 Canvas 对绘制的辅助——范围裁切和几何变换
     */
    CANVAS;
}