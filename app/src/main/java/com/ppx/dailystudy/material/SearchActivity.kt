package com.ppx.dailystudy.material

import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*

/**
 *
 * @Author Shirley
 * @Date：2023/9/28
 * @Desc：
 */
class SearchActivity : BaseActivity() {
    private val mStrs = arrayOf("aaa", "bbb", "ccc", "airsaid")

    override fun initLayout(): Int {
        return R.layout.activity_search
    }

    override fun initView() {
        initSearchView()
    }

    private fun initSearchView() {
        list_view.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mStrs)
        //isTextFilterEnabled 这个属性没啥用哇，true和false都能够正常过滤搜索
        list_view.isTextFilterEnabled = false
        //这个属性看起来也是没有什么作用...
        search_view.isIconified = true
        //isSubmitButtonEnabled 是否显示提交图标
        search_view.isSubmitButtonEnabled = true

        // 设置搜索文本监听
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // 当点击搜索按钮时触发该方法
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            // 当搜索内容改变时触发该方法
            override fun onQueryTextChange(newText: String): Boolean {
                (list_view.adapter as ArrayAdapter<String>).filter.filter(newText)
                return false
            }
        })
    }
}