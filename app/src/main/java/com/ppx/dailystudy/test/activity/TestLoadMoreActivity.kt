package com.ppx.dailystudy.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import com.ppx.dailystudy.test.adapter.TestLoadMoreAdapter

/**
 * @Author: LuoXia
 * @Date: 2021/2/25 15:38
 * @Description: 学习BaseQuickAdapterHelper的分页加载（loadMore等）
 */
class TestLoadMoreActivity : AppCompatActivity() {

    private var adapter:TestLoadMoreAdapter = TestLoadMoreAdapter(mutableListOf())
    private val dataList: MutableList<Int> = mutableListOf()
    private val pageIndex: Int = 1
    private val pageSize: Int = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_rv)

        getData()
    }

    private fun getData() {
        for (i in 0..100) {
            dataList.add(i)
        }
    }

    private fun loadData(){

    }

}