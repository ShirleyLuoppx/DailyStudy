package com.ppx.dailystudy.firstlinecode.cha09internet

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.ppx.dailystudy.R
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Author: luoxia
 * Date: 2020/9/21 18:20
 * Description: webview的简单使用
 */
class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        //让webview设置支持js脚本
        wb_webview.settings.javaScriptEnabled = true
        //使webview不跳转到浏览器，就在我们的app里面显示
        wb_webview.webViewClient = WebViewClient()
        wb_webview.loadUrl("https://fanyi.baidu.com/?aldtype=16047#auto/zh")
    }
}