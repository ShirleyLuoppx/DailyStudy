package com.ppx.dailystudy.chap12materialdesign

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_collaspcing_toolbar_layout.*
import java.lang.StringBuilder

/**
 * Author: LuoXia
 * Date: 2020/10/15 11:09
 * Description: CollapsingTooBarLayout的简单使用
 */
class CollaspcingToobarActivity : AppCompatActivity() {

    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collaspcing_toolbar_layout)

        val fruitName = intent.getStringExtra(FRUIT_NAME)
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)

//        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        collapsing_layout.title = fruitName
        //这里本来是用的Glide，但是我没有网，所以直接用img的吧
        iv_fruit.setImageResource(fruitImageId)
        tv_card_view.text = generateFruitName(fruitName)
    }

    private fun generateFruitName(fruitName: String): String {
        val name = StringBuilder()
        for (i in 0..500) {
            name.append(fruitName)
        }
        return name.toString()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}