package com.ppx.dailystudy.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.ppx.dailystudy.datasave.SQLiteDBHelper

/**
 * 自定义的ContentProvider用于外界程序跨进程访问自身程序数据
 */
class MyContentProvider : ContentProvider() {

    private lateinit var sqliteDbHelpter: SQLiteDBHelper
    //kt的常量声明方式  companion object{ const val aa=0  }
    companion object {
        const val FLOWER_DIR = 0
        const val FLOWER_ITEM = 1
        const val GRASS_DIR = 2
        const val GRASS_ITEM = 3
        private var uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private const val AUTHORITY = "com.ppx.dailystudy.contentprovider"

        //kt的静态代码块的声明  companion object{  init{....}   }
        init {
            //初始化一个uriMatcher
            //添加通配uri
            //期望访问的是FlowerBean的所有数据
            uriMatcher.addURI("com.ppx.dailystudy.contentprovider", "FlowerBeanWithId", FLOWER_DIR)
            //期望方位的是FlowerBean的具体id为某条的数据
            uriMatcher.addURI(
                "com.ppx.dailystudy.contentprovider",
                "FlowerBeanWithId/#",
                FLOWER_ITEM
            )
            uriMatcher.addURI("com.ppx.dailystudy.contentprovider", "GrassBeanWithId", GRASS_DIR)
            uriMatcher.addURI("com.ppx.dailystudy.contentprovider", "GrassBeanWithId/#", GRASS_ITEM)
        }
    }

    /**
     *  return：通常在这里完成数据库的初始化和升级，成功返回true否则false
     * 但是仅在有程序通过COntentResolver访问我们这个程序的数据的时候才会调用到OnCreate来
     */
    override fun onCreate(): Boolean {
        sqliteDbHelpter = SQLiteDBHelper(context, "FlowerBeanWithId", null, 3)
        return true
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
        val db = sqliteDbHelpter.writableDatabase
        var cursor: Cursor? = null

        when (uriMatcher.match(uri)) {
            FLOWER_DIR -> {
                cursor = db.query(
                    "FlowerBeanWithId",
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            FLOWER_ITEM -> {
                val id = uri.pathSegments[1]
                cursor = db.query(
                    "FlowerBeanWithId",
                    projection,
                    "id=?",
                    arrayOf(id),
                    null,
                    null,
                    sortOrder
                )
            }
            GRASS_DIR -> {
                cursor = db.query(
                    "GrassBeanWithId",
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }
            GRASS_ITEM -> {
                val id = uri.pathSegments[1]
                cursor = db.query(
                    "GrassBeanWithId",
                    projection,
                    "id=?",
                    arrayOf(id),
                    null,
                    null,
                    sortOrder
                )
            }
        }
        return cursor
    }

    /**
     * return : 返回这条新数据的uri
     */
    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = sqliteDbHelpter.writableDatabase
        var uriReturn: Uri? = null
        when (uriMatcher.match(uri)) {
            FLOWER_DIR, FLOWER_ITEM -> {
                val id = db.insert("FlowerBeanWithId", null, values)
                uriReturn = Uri.parse("content://$AUTHORITY/FlowerBeanWithId/$id")
            }
            GRASS_DIR, GRASS_ITEM -> {
                val id = db.insert("GrassBeanWithId", null, values)
                uriReturn = Uri.parse("content://$AUTHORITY/GrassBeanWithId/$id")
            }
        }
        return uriReturn
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
        val db = sqliteDbHelpter.writableDatabase
        var updateRows = 0
        when (uriMatcher.match(uri)) {
            FLOWER_DIR -> {
                updateRows = db.update("FlowerBeanWithId", values, selection, selectionArgs)
            }
            FLOWER_ITEM -> {
                val id = uri.pathSegments[0]
                updateRows = db.update("FlowerBeanWithId", values, "id=?", arrayOf(id))
            }
            GRASS_DIR -> {
                updateRows = db.update("GrassBeanWithId", values, selection, selectionArgs)
            }
            GRASS_ITEM -> {
                val id = uri.pathSegments[0]
                updateRows = db.update("GrassBeanWithId", values, "id=?", arrayOf(id))
            }
        }
        return updateRows
    }

    /**
     * return：返回被删除的行数
     */
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db = sqliteDbHelpter.writableDatabase
        var deletedRows = 0
        when (uriMatcher.match(uri)) {
            FLOWER_DIR -> {
                deletedRows = db.delete("FlowerBeanWithId", selection, selectionArgs)
            }
            FLOWER_ITEM -> {
                val id = uri.pathSegments[0]
                deletedRows = db.delete("FlowerBeanWithId", "id=?", arrayOf(id))
            }
            GRASS_DIR -> {
                deletedRows = db.delete("GrassBeanWithId", selection, selectionArgs)
            }
            GRASS_ITEM -> {
                val id = uri.pathSegments[0]
                deletedRows = db.delete("GrassBeanWithId", "id=?", arrayOf(id))
            }
        }
        return deletedRows
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
