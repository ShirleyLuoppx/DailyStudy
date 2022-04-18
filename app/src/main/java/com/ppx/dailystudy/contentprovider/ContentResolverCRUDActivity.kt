package com.ppx.dailystudy.contentprovider

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
        bt_query_contacts.setOnClickListener { getContacts() }
    }

    /**
     * 使用现有的内容提供器来获取手机通讯录的数据
     */
    private fun getContacts() {
        if (Build.VERSION.SDK_INT >= 23) {
            //注意这里的Manifest是android包下的 不要导错了
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    1
                )
            } else {
                queryContacts()
            }
        } else {
            queryContacts()
        }
    }

    var contentResolverAdapter = ContentResolverAdapter(mutableListOf())
    var contentResolverContactsList = mutableListOf<String>()
    private fun queryContacts() {
        val contactsCusor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        contactsCusor?.let {
            while (it.moveToNext()) {
                val name =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    it.getString(it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val str = "$name :  $number"
                contentResolverContactsList.add(str)
            }
            //记得关闭！！！
            it.close()
        }

        contentResolverAdapter = ContentResolverAdapter(contentResolverContactsList)
        rv_content_resolver_data.layoutManager = LinearLayoutManager(this)
        rv_content_resolver_data.adapter = contentResolverAdapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    queryContacts()
                } else {
                    Toast.makeText(this, "refuse", Toast.LENGTH_SHORT).show()
                }
            }
        }
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
            //记得要关掉哟
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