package com.ppx.dailystudy.material.btnaviview

import android.util.Log
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.ppx.dailystudy.MyApplication
import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import com.ppx.dailystudy.material.btnaviview.*
import kotlinx.android.synthetic.main.activity_bottom_navigation_view.*
import java.util.*

/**
 *
 * @Author Shirley
 * @Date：2023/9/27
 * @Desc：BottomNavigationView + Toolbar + ViewPager + Fragment 的简单使用
 */
class BottomNavigationViewActivity : BaseActivity() {

    val TAG: String = "BottomNavigationViewActivity"

    override fun initLayout(): Int {
        return R.layout.activity_bottom_navigation_view
    }

    override fun initView() {
        var fragmentList =
            Arrays.asList(Fragment1(), Fragment2(), Fragment3(), Fragment4(), Fragment5())
        viewpager.adapter = ViewPager2FragmentAdapter(this, fragmentList)
        viewpager.currentItem = 0

        bt_navigation_view.setBackgroundColor(resources.getColor(R.color.colorWhite))

        /**
         * 设置选项卡的几种显示方式
         * LABEL_VISIBILITY_AUTO ：默认效果，选项卡数量在3个以下的时候，正常显示；选项卡在3个以上的时候，只有选中的时候才会展示选项卡的title。
         * LABEL_VISIBILITY_SELECTED：只有选中的时候才显示选项卡的title；
         * LABEL_VISIBILITY_LABELED：永远都显示title，且选中的时候有动画；
         * LABEL_VISIBILITY_UNLABELED：永远都不显示title，且选中的时候没有动画。
         *
         */
        bt_navigation_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED

        bt_navigation_view.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            Toast.makeText(MyApplication.getContext(), menuItem.title, Toast.LENGTH_SHORT).show()

            toolbar.title = menuItem.title

            when (menuItem.itemId) {
                R.id.home -> {
                    viewpager.setCurrentItem(0, true)
                }
                R.id.message -> {
                    viewpager.setCurrentItem(1, true)
                }
                R.id.book -> {
                    viewpager.setCurrentItem(2, true)
                }
                R.id.movie -> {
                    viewpager.setCurrentItem(3, true)
                }
                R.id.user -> {
                    viewpager.setCurrentItem(4, true)
                }
            }
            true
        })


        viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                Log.d(TAG, "onPageSelected: $position")

                when (position) {
                    0 -> bt_navigation_view.selectedItemId = R.id.home
                    1 -> bt_navigation_view.selectedItemId = R.id.message
                    2 -> bt_navigation_view.selectedItemId = R.id.book
                    3 -> bt_navigation_view.selectedItemId = R.id.movie
                    4 -> bt_navigation_view.selectedItemId = R.id.user
                }
            }
        })
    }

}