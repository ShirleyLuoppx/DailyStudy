package com.ppx.dailystudy.datasave

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_file_save.*
import java.io.*
import java.lang.Exception

/**
 * Author: luoxia
 * Date: 2020/9/6 21:42
 * Description: 此类主要是用于操作文件存储
 */
class FileSaveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_save)

        bt_save_data_to_file.setOnClickListener {
            Log.d("ippx", "onCreate: 要存储的数据为:${et_text_file_save.text.toString()}")
            save(et_text_file_save.text.toString())
        }

        bt_click_to_show_data.setOnClickListener {
            tv_show_data.text = getFileData()
        }
    }

    /**
     * 获取文件数据
     */
    private fun getFileData(): String {
        val stringBuilder = StringBuilder()
        var bufferedReader: BufferedReader? = null

        try {
            val fileInputString = openFileInput("fileSave")
            bufferedReader = BufferedReader(InputStreamReader(fileInputString))

            val str = bufferedReader.readLine()
            if (str != null) {
                stringBuilder.append(str)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        Log.d("ippx", "onCreate: 获取到的数据为:${stringBuilder.toString()}")
        return stringBuilder.toString()
    }

    /**
     * 文件存储数据
     * 这里存储的文件，可以在--View-Tool Windows-Device File Explorer-data-data-项目包名-files中找到-右键open就可以看到你刚刚写入的数据啦
     */
    private fun save(data: String) {
        var bufferedWriter: BufferedWriter? = null
//        val stringData = "this is a data for file saving"
        try {
            /**
             * 参数一：存储数据的文件的名字；
             * 参数二：文件的操作方式，
             * 有两种：MODE_PRIVATE，也是默认值，如果以前有重名的文件，里面的内容就会新的覆盖掉，如果没有就创建新的
             * MODE_APPEND：从名字就可以看出来，是追加的，就是如果没有这个文件就创建新的，如果有这个文件新内容就会追加的以前的内容上
             * 还有两个，MODE_WORLD_READABLE和MODE_WORLD_WRITEABLE，允许其他的app对我们的app进行读写文件操作，由于不安全，所以在Android4.2的时候被废弃了
             */
            val outputStream = openFileOutput("fileSave", Context.MODE_PRIVATE)
            bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))
            bufferedWriter.write(data)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                bufferedWriter?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}