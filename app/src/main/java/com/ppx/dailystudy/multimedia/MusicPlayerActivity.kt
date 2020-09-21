package com.ppx.dailystudy.multimedia

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_music_player.*
import java.io.File

/**
 * Author: LuoXia
 * Date: 2020/9/21 13:11
 * Description: DESCRIPTION
 */
class MusicPlayerActivity : AppCompatActivity() {

    private var mediaPlayer : MediaPlayer?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)

        initEvent()
    }

    private fun initEvent() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        } else {
            initPlayer()
        }

        bt_play_music.setOnClickListener {
            mediaPlayer?.start()
        }
    }

    private fun initPlayer() {
        mediaPlayer = MediaPlayer()
//        val file = File(Environment.getExternalStorageDirectory(), "南拳妈妈 - 下雨天.mp3")
        mediaPlayer?.setDataSource("http://music.baidu.com/data/music/file? link=http://yinyueshiting.baidu.com/data2/music/3566287/29237101437253261128.mp3? xcode=fc3ef977c9fa9bdf4394f800f7f2550e&song_id=2923710")
        mediaPlayer?.prepare()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initPlayer()
            }else{
                Log.d("ippx", "onRequestPermissionsResult: 没有读写权限哟")
            }
        }
    }
}