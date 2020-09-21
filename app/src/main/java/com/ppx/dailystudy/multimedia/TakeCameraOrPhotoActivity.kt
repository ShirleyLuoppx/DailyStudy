package com.ppx.dailystudy.multimedia

import android.Manifest
import android.app.Activity
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
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
                imageUri = FileProvider.getUriForFile(
                    this,
                    "com.ppx.dailystudy.multimedia.takecameraorphoto",
                    imageFile
                )
            } else {
                imageUri = Uri.fromFile(imageFile)
            }

            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            startActivityForResult(intent, 1)
        }

        bt_choose_photo.setOnClickListener {
            //判断权限
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 2)
            } else {
                openAlbum()
            }
        }
    }

    /**
     * 打开相册咯
     */
    private fun openAlbum() {
        val intent = Intent("android.intent.action.GET_CONTENT")
        intent.type = "image/*"
        startActivityForResult(intent, 3)
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
        } else if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {
                //4.4以上处理图片的方法，因为4.4以上的系统选中的图片返回的不是一个直接的uri，而是封装后的，所以需要解析
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImgOnKitkat(data)
                } else {
                    handleImageBeforeKitkat(data)
                }
            }
        }
    }

    private fun handleImageBeforeKitkat(data: Intent?) {
        data?.data?.let {
            displayImage(getImagePath(it, ""))
        }
    }

    private fun handleImgOnKitkat(data: Intent?) {
        var imagePath1 = ""
        val uri = data?.data
        //如果是document类型
        if (DocumentsContract.isDocumentUri(this, uri)) {
            val docId = DocumentsContract.getDocumentId(uri)
            //如果的media格式
            if ("com.android.providers.media.documents" == uri?.authority) {
                //解析出数字格式的id
                val id = docId.split(":")[1]
                val selection = MediaStore.Images.Media._ID + "=" + id
                imagePath1 = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection)
            } else if ("com.android.providers.downloads.documents" == uri?.authority) {

                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    docId.toLong()
                )
                imagePath1 = getImagePath(contentUri, "")
            }
        } else if ("content" == imageUri?.scheme) {//如果是uri类型
            imageUri?.let {
                imagePath1 = getImagePath(it, "")
            }

        } else if ("file" == imageUri?.scheme) {//如果是file类型
            imageUri?.let {
                imagePath1 = it.path.toString()
            }
        }
        displayImage(imagePath1)
    }

    /**
     * 展示图片
     */
    private fun displayImage(imagePath: String) {
//        AudioManager
        if (imagePath.isNotEmpty()) {
            val bitmap = BitmapFactory.decodeFile(imagePath)
            iv_photo.setImageBitmap(bitmap)
        } else {
            Log.d("ippx", "displayImage: 图片路径为空")
        }
    }

    /**
     * 通过uri和条件获取图片路径
     */
    private fun getImagePath(uri: Uri, selection: String): String {
        var imagePath = ""
        val cursor = contentResolver.query(uri, null, selection, null, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
        }
        return imagePath
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 2 && permissions.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openAlbum()
        } else {
            Log.d("ippx", "onRequestPermissionsResult: 没有相册的读写权限哦~~")
        }
    }
}