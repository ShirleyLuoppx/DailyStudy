package com.ppx.dailystudy.material

import android.content.Intent
import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import com.ppx.dailystudy.material.btnaviview.BottomNavigationViewActivity
import com.ppx.dailystudy.material.drawerlayout.DrawerLayoutMainActivity
import kotlinx.android.synthetic.main.activity_material_widget_main.*

/**
 *
 * @Author Shirley
 * @Date：2023/10/7
 * @Desc：
 */
class MaterialWidgetMainActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_material_widget_main
    }

    override fun initView() {
        btn_bottom_navigation_view.setOnClickListener {
            startActivity(
                Intent(
                    this, BottomNavigationViewActivity::class.java
                )
            )
        }
        btn_drawerlayout.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    DrawerLayoutMainActivity::class.java
                )
            )
        }
        btn_chip_group.setOnClickListener { startActivity(Intent(this, ChipActivity::class.java)) }
        btn_palette.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PaletteDemoActivity::class.java
                )
            )
        }
        btn_search_view.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SearchActivity::class.java
                )
            )
        }
        btn_shape_imageview.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ShapeImageviewDemoActivity::class.java
                )
            )
        }
        btn_snackbar.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SnackBarDemoActivity::class.java
                )
            )
        }
        btn_text_input_layout.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    TextInputLayoutActivity::class.java
                )
            )
        }
        btn_toolbar.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ToolBarDemoActivity::class.java
                )
            )
        }


    }
}