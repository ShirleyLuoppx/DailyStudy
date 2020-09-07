package com.ppx.dailystudy.datasave

import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_sqlite.*

/**
 * Author: luoxia
 * Date: 2020/9/7 14:15
 * Description: 此类主要是用于测试sqlite操作的
 */
class SQLiteActivity : AppCompatActivity() {

    private var sqLiteDBHelper: SQLiteDBHelper? = null
    private var flowerList = mutableListOf<FlowerBean>()
    private var sqliteAdapter = SqliteAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite)

        sqLiteDBHelper = SQLiteDBHelper(this, "Flower.db", null, 2)

        bt_create_table.setOnClickListener { sqLiteDBHelper?.writableDatabase }

        bt_add_data.setOnClickListener { addData() }
        bt_update_data.setOnClickListener { updateData() }
        bt_delete_data.setOnClickListener { deleteData() }
        bt_query_data.setOnClickListener { queryData() }
    }

    private fun queryData() {
        val sqLiteDatabase = sqLiteDBHelper?.writableDatabase
        //查询全部
        val dataCursor = sqLiteDatabase?.query("Flower", null, "", null, "", "", "")
        flowerList.clear()
        dataCursor?.let {
            if (it.moveToNext()) {
                do {
                    val name = it.getString(it.getColumnIndex("name"))
                    val number = it.getInt(it.getColumnIndex("number"))
                    val flowerBean = FlowerBean()
                    flowerBean.name = name
                    flowerBean.number = number
                    flowerList.add(flowerBean)
                } while (it.moveToNext())
            }
            it.close()
        }

        sqliteAdapter = SqliteAdapter(flowerList)
        rv_sqlite_data.layoutManager = LinearLayoutManager(this)
        rv_sqlite_data.adapter = sqliteAdapter
        sqliteAdapter.notifyDataSetChanged()
    }

    private fun deleteData() {
        val sqLiteDatabase = sqLiteDBHelper?.writableDatabase
        sqLiteDatabase?.delete("Flower", "name=?", arrayOf("玫瑰花"))
    }

    private fun updateData() {
        val sqLiteDatabase = sqLiteDBHelper?.writableDatabase
        val updatedFlowerData = ContentValues()
        updatedFlowerData.put("number", 100)
        sqLiteDatabase?.update("Flower", updatedFlowerData, "name=?", arrayOf("玫瑰花"))
    }

    private fun addData() {
        val sqLiteDatabase = sqLiteDBHelper?.writableDatabase

        val flowerContentValue = ContentValues()
        flowerContentValue.put("name", "蛇皮花")
        flowerContentValue.put("number", "20")
        sqLiteDatabase?.insert("Flower", null, flowerContentValue)

        flowerContentValue.clear()
        flowerContentValue.put("name", "玫瑰花")
        flowerContentValue.put("number", "50")
        sqLiteDatabase?.insert("Flower", null, flowerContentValue)
    }


    class FlowerBean {
        var name: String = ""
        var number: Int = 0
    }
}