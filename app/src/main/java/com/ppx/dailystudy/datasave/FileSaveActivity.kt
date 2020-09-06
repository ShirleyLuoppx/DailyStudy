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
        var stringBuffer = StringBuffer()
        var bufferedReader: BufferedReader? = null
        try {
            val fileInputString = openFileInput("fileSave")
            bufferedReader = BufferedReader(InputStreamReader(fileInputString))

            var str = ""
            str = bufferedReader.readLine()
            while (str != null) {
                stringBuffer.append(str)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        Log.d("ippx", "onCreate: 获取到的数据为:${stringBuffer.toString()}")
        return stringBuffer.toString()
    }

    /**
     * 文件存储数据
     */
    private fun save(data: String) {
        var bufferedWriter: BufferedWriter? = null
//        val stringData = "this is a data for file saving"
        try {
            val outputStream = openFileOutput("fileSave", Context.MODE_PRIVATE)
            bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream))
            bufferedWriter.write(data)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}