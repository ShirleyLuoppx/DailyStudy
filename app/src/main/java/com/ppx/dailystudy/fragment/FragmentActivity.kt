package com.ppx.dailystudy.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_spannable_string.*

/**
 * Author: luoxia
 * Date: 2020/9/5 9:11
 * Description: 用于装载fragment的activity
 */
class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable_string)


        supportFragmentManager.beginTransaction().add(R.id.fl_fragment_demo, FragmentDemo()).commit()

        bt_click_to_start_fragment.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fl_fragment_demo, FragmentDemo2())
                .commit()
        }

    }
}