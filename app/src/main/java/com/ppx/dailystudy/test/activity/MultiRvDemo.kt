package com.ppx.dailystudy.test.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import com.ppx.dailystudy.test.adapter.MultiAdapter
import kotlinx.android.synthetic.main.activity_multi_rv.*

/**
 * by:LuoXia
 * date:2020/11/25
 * desc:学习下多布局方式
 */
class MultiRvDemo : AppCompatActivity() {

    private lateinit var adapter : MultiAdapter
    private var list = mutableListOf<MultiAdapter.DataType>()

    companion object {
        const val TYPE_HORIZONTAL = 0
        const val TYPE_NORMAL = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_rv)

        adapter= MultiAdapter(list)

        rv_multi.adapter = adapter
        when(TYPE_NORMAL){

        }
    }
}