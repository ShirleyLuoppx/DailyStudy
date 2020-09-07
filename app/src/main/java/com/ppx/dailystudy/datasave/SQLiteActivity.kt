package com.ppx.dailystudy.datasave

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_sqlite.*
import org.litepal.LitePal
import org.litepal.crud.LitePalSupport
import org.litepal.tablemanager.Connector
import java.nio.channels.FileLock

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

        bt_add_data_by_sql.setOnClickListener { insertBySql() }
        bt_update_data_by_sql.setOnClickListener { updateBySql() }
        bt_delete_data_by_sql.setOnClickListener { deleteBySql() }
        bt_query_data_by_sql.setOnClickListener { queryBySql() }

        bt_create_table_by_litepal.setOnClickListener {
            //创建数据库
            Connector.getDatabase()
        }
        bt_add_data_by_litepal.setOnClickListener { insertByLitePal() }
        bt_update_data_by_litepal.setOnClickListener { updateByLitePal() }
        bt_delete_data_by_litepal.setOnClickListener { deleteByLitePal() }
        bt_query_data_by_litepal.setOnClickListener { queryByLitePal() }
        bt_set_default_by_litepal.setOnClickListener { updateToDefault() }

    }

    /**
     * 通过litepal的CRUD
     */
    private fun insertByLitePal() {
        val flowerBean = FlowerBean()
        flowerBean.name = "水仙花"
        flowerBean.number = 123
        flowerBean.save()

        val flowerBean1 = FlowerBean()
        flowerBean1.name = "塑料花"
        flowerBean1.number = 996
        flowerBean1.save()
    }

    private fun updateByLitePal() {
        val flowerBean = FlowerBean()
        flowerBean.number = 666
        flowerBean.updateAll("name =?", "水仙花")
    }

    /**
     * 将所有的FlowerBean的number列设置为默认值，number是int类型的，默认值为0，updateAll不传参，默认修改所有的FlowerBean的number
     */
    private fun updateToDefault() {
        val flowerBean = FlowerBean()
        flowerBean.setToDefault("number")
        flowerBean.updateAll()
    }

    private fun deleteByLitePal() {
        LitePal.deleteAll(FlowerBean::class.java, "number < 100")
    }

    private fun queryByLitePal() {
        val flowerList = LitePal.findAll(FlowerBean::class.java)
        initRV(flowerList)
    }

    /**
     * 直接写sql的CRUD
     */
    private fun insertBySql() {
        val db = sqLiteDBHelper?.writableDatabase
        db?.execSQL("insert into Flower (name,number) values(?,?)", arrayOf("霸王花", 1))
        db?.execSQL("insert into Flower (name,number) values(?,?)", arrayOf("皮皮花", 238))
    }

    private fun updateBySql() {
        val db = sqLiteDBHelper?.writableDatabase
        db?.execSQL("update Flower set number =? where name =?", arrayOf(20000, "皮皮花"))
    }

    private fun deleteBySql() {
        val db = sqLiteDBHelper?.writableDatabase
        db?.execSQL("delete from Flower where name =?", arrayOf("霸王花"))
    }

    private fun queryBySql() {
        val db = sqLiteDBHelper?.writableDatabase
        val cursor = db?.rawQuery("select * from Flower", null)
        setData(cursor)
    }

    /**
     * 查询数据
     */
    private fun queryData() {
        val sqLiteDatabase = sqLiteDBHelper?.writableDatabase
        //查询全部
        val dataCursor = sqLiteDatabase?.query("Flower", null, "", null, "", "", "")
        setData(dataCursor)
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
        rv_sqlite_data.layoutManager = LinearLayoutManager(this)
        rv_sqlite_data.adapter = sqliteAdapter
        sqliteAdapter.notifyDataSetChanged()
    }

    /**
     * 删除数据
     */
    private fun deleteData() {
        val sqLiteDatabase = sqLiteDBHelper?.writableDatabase
        sqLiteDatabase?.delete("Flower", "name=?", arrayOf("玫瑰花"))
    }

    /**
     * 修改数据
     */
    private fun updateData() {
        val sqLiteDatabase = sqLiteDBHelper?.writableDatabase
        val updatedFlowerData = ContentValues()
        updatedFlowerData.put("number", 100)
        sqLiteDatabase?.update("Flower", updatedFlowerData, "name=?", arrayOf("玫瑰花"))
    }

    /**
     * 添加数据
     */
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


}