package com.ppx.dailystudy.test.vrandgif

import android.content.Intent
import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import kotlinx.android.synthetic.main.activity_gif_main.*

/**
 *
 * @Author Shirley
 * @Date：2023/10/9
 * @Desc：
 */
class GifMainActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_gif_main
    }

    override fun initView() {
        bt_gif.setOnClickListener {
            startActivity(Intent(this, VrActivity::class.java))
        }
        bt_voice_ball.setOnClickListener {
            startActivity(Intent(this, VoiceGifActivity::class.java))
        }
    }
}