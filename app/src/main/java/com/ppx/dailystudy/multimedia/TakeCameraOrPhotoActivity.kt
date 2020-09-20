package com.ppx.dailystudy.multimedia

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_take_camera_photo.*
import java.io.File
import java.io.OutputStream

/**
 * Author: luoxia
 * Date: 2020/9/20 22:04
 * Description: 调用摄像头拍照并显示出来
 */
class TakeCameraOrPhotoActivity : AppCompatActivity() {

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_camera_photo)

        bt_take_a_photo.setOnClickListener {

            //file用来存放拍下来的图片
            val imageFile = File(externalCacheDir, "imageFile.jpg")
            if (imageFile.exists()) {
                imageFile.delete()
            }
            imageFile.createNewFile()

            if (Build.VERSION.SDK_INT >= 24) {
                //参数二，随便的唯一值，用于xml添加provider用的
                imageUri = FileProvider.getUriForFile(this, "com.ppx.dailystudy.multimedia.takecameraorphoto", imageFile)
            } else {
                imageUri = Uri.fromFile(imageFile)
            }

            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                imageUri?.let {
                    val imageBitmap =
                        BitmapFactory.decodeStream(contentResolver.openInputStream(it))
                    iv_photo.setImageBitmap(imageBitmap)
                }

            }
        }
    }
}