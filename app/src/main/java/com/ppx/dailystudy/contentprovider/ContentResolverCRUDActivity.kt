package com.ppx.dailystudy.contentprovider

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppx.dailystudy.R
import com.ppx.dailystudy.datasave.FlowerBean
import com.ppx.dailystudy.datasave.SqliteAdapter
import kotlinx.android.synthetic.main.activity_content_resolver.*
import kotlinx.android.synthetic.main.activity_content_resolver.bt_query_data

/**
 * Author: luoxia
 * Date: 2020/9/8 15:59
 * Description: 利用现有的内容提供器来进行CRUD操作其他程序应用的数据
 */
class ContentResolverCRUDActivity : AppCompatActivity() {

    private var flowerList = mutableListOf<FlowerBean>()
    private var sqliteAdapter = SqliteAdapter(mutableListOf())
    private val uri = Uri.parse("content://com.ppx.dailystudy.provider/FlowerBean")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_resolver)

        bt_query_data.setOnClickListener { queryByContentResolver() }
        bt_add_data.setOnClickListener { addByContentResolver() }
        bt_update_data.setOnClickListener { updateByContentResolver() }
        bt_delete_data.setOnClickListener { deleteByContentResolver() }
    }

    /**
     * 使用现有的内容提供器来进行CRUD操作
     */
    private fun deleteByContentResolver() {
        contentResolver.delete(uri, "name=?", arrayOf("塑料花"))
    }

    private fun updateByContentResolver() {
        val values = ContentValues()
        values.put("number", "1010")
        contentResolver.update(uri, values, "name=?", arrayOf("塑料花"))
    }

    private fun addByContentResolver() {
        val contentValues = ContentValues()
        contentValues.put("name", "塑料花")
        contentValues.put("number", "2020")
        contentResolver.insert(uri, contentValues)

        val contentValues1 = ContentValues()
        contentValues1.put("name", "牵牛花")
        contentValues1.put("number", "7788")
        contentResolver.insert(uri, contentValues1)
    }

    private fun queryByContentResolver() {
        val cursor = contentResolver.query(uri, null, null, null, null)

        setData(cursor)
    }


    private fun setData(dataCursor: Cursor?) {
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

        initRV(flowerList)
    }

    private fun initRV(dataList: MutableList<FlowerBean>) {
        sqliteAdapter = SqliteAdapter(dataList)
        rv_content_resolver_data.layoutManager = LinearLayoutManager(this)
        rv_content_resolver_data.adapter = sqliteAdapter
        sqliteAdapter.notifyDataSetChanged()
    }

}