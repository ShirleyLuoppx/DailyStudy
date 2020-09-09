package com.ppx.dailystudy

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_spannable_string.*

/**
 * Author: luoxia
 * Date: 2020/9/4 16:12
 * Description: 此类主要是想要测试，使用SpannableString设置过前景色的textview后能否用settextclor修改
 *
 * 结论：使用SpannableString设置了文字的前景色之后是不能使用settextcolor来修改的
 */
class SpannableStringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val spannableString = SpannableString(tv_test_span.text)
        spannableString.apply {
            setSpan(
                ForegroundColorSpan(resources.getColor(R.color.colorAccent)),
                0,
                tv_test_span.text.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
            setSpan(
                AbsoluteSizeSpan(70),
                0,
                tv_test_span.text.length,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }

        tv_test_span.setTextColor(resources.getColor(R.color.colorBlack))
        tv_test_span.textSize = 1f

        tv_test_span.text = spannableString
    }
}