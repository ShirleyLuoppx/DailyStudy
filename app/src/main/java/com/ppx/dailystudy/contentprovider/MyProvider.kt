package com.ppx.dailystudy.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.ppx.dailystudy.datasave.GrassBean

/**
 * Author: luoxia
 * Date: 2020/9/9 10:37
 * Description: 通过之前的例子可知，如果通过ContentResolver访问自己程序的数据就需要自己创建一个内容提供器
 */
class MyProvider : ContentProvider() {

    //kt的常量声明方式  companion object{ const val aa=0  }
    companion object {
        const val FLOWER_DIR = 0
        const val FLOWER_ITEM = 1
        const val GRASS_DIR = 2
        const val GRASS_ITEM = 3
        private var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        //kt的静态代码块的声明  companion object{  init{....}   }
        init {
            //初始化一个uriMatcher
            //添加通配uri
            //期望访问的是FlowerBean的所有数据
            uriMatcher.addURI("com.ppx.dailystudy.datasave", "FlowerBeanWithId", FLOWER_DIR)
            //期望方位的是FlowerBean的具体id为某条的数据
            uriMatcher.addURI("com.ppx.dailystudy.datasave", "FlowerBeanWithId/#", FLOWER_ITEM)
            uriMatcher.addURI("com.ppx.dailystudy.datasave", "GrassBeanWithId", GRASS_DIR)
            uriMatcher.addURI("com.ppx.dailystudy.datasave", "GrassBeanWithId/#", GRASS_ITEM)
        }
    }

    /**
     * uri:查询指定uri中的哪一张表
     * projection：查询那几列
     * selection：查询的条件参数
     * selectionArgs：查询的条件参数
     * sortOrder：排序
     */
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            FLOWER_DIR -> {
            }
            FLOWER_ITEM -> {
            }
            GRASS_DIR -> {
            }
            GRASS_ITEM -> {
            }
        }
        TODO("Not yet implemented")
    }

    /**
     *  return：通常在这里完成数据库的初始化和升级，成功返回true否则false
     * 但是仅在有程序通过COntentResolver访问我们这个程序的数据的时候才会调用到OnCreate来
     */
    override fun onCreate(): Boolean {

        return false
    }

    /**
     * return : 返回这条新数据的uri
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    /**
     * return :返回受影响的行数
     */
    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    /**
     * return：返回被删除的行数
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    /**
     * return：根据传入的uri返回对应的MIME类型
     */
    override fun getType(uri: Uri): String? {
        var mimeStr = ""
        when (uriMatcher.match(uri)) {
            FLOWER_DIR -> {
                mimeStr =
                    "vnd.android.cursor.dir/vnd.com.ppx.dailystudy.contentprovider.FlowerBeanWithId"
            }
            FLOWER_ITEM -> {
                mimeStr =
                    "vnd.android.cursor.item/vnd.com.ppx.dailystudy.contentprovider.FlowerBeanWithId"
            }
            GRASS_DIR -> {
                mimeStr =
                    "vnd.android.cursor.dir/vnd.com.ppx.dailystudy.contentprovider.GrassBeanWithId"
            }
            GRASS_ITEM -> {
                mimeStr =
                    "vnd.android.cursor.item/vnd.com.ppx.dailystudy.contentprovider.GrassBeanWithId"
            }
        }
        return mimeStr
    }
}