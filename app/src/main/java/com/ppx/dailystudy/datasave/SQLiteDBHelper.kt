package com.ppx.dailystudy.datasave

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/**
 * Author: luoxia
 * Date: 2020/9/6 23:17
 * Description: SQLite 存储方式
 *
 * 继承SQLiteOpenHelper，添加一个四参的构造方法
 * 参数1：上下文
 * 参数2：数据库名
 * 参数3：cursor，目前传null就行
 * 参数4：版本号
 */
open class SQLiteDBHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    private var mContext: Context? = context

    /**
     * 构建一个sql
     * Android中的sqlite数据库的类型有以下几种
     * integer 整形
     * text 字符型
     * real 浮点型
     * blob 二进制型
     */
    val CREATE_FLOWER =
        "create table Flower (id integer primary key autoincrement,name text,number real)"

    val CREATE_GRASS =
        "create table Grass (id integer primary key autoincrement,name text,number real)"

    val CREATE_FLOWER_WITH_ID =
        "create table FlowerBeanWithId (id integer primary key autoincrement,name text,number real)"

    val CREATE_GRASS_WITH_ID =
        "create table GrassBeanWithId (id integer primary key autoincrement,name text,number real)"

    override fun onCreate(db: SQLiteDatabase?) {
        //执行创建表
        db?.execSQL(CREATE_FLOWER)
        db?.execSQL(CREATE_GRASS)
        db?.execSQL(CREATE_FLOWER_WITH_ID)
        db?.execSQL(CREATE_GRASS_WITH_ID)
//        Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Flower")
        db?.execSQL("drop table if exists Grass")
        db?.execSQL("drop table if exists FlowerBeanWithId")
        db?.execSQL("drop table if exists GrassBeanWithId")
        onCreate(db)
    }


}