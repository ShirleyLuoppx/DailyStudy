package com.ppx.dailystudy.chatview

/**
 * Author: luoxia
 * Date: 2020/8/26 23:22
 * Description: 第二行代码-3.7-NinePatch的实例（本实例中并没有用到.9图相关的内容，只是单纯想实现一个这个聊天的样子而已）
 *
 * 测试git22222
 */
class ChatViewBean {
    var msg: String = ""
    var type: Int = -1
}

enum class ChatType(val type: Int) {
    TYPE_RECIVE(0),
    TYPE_SEND(1)
}