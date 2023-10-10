package com.ppx.dailystudy.test.vrandgif

import com.ppx.dailystudy.R
import com.ppx.dailystudy.common.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*

/**
 *
 * @Author Shirley
 * @Date：2023/10/10
 * @Desc：
 */
class WebViewActivity : BaseActivity() {
    override fun initLayout(): Int {
        return R.layout.activity_web_view
    }

    override fun initView() {
        var htmlContent = ""
        webview_3d.getSettings().setJavaScriptEnabled(true);
        webview_3d.loadUrl("file:///android_asset/index.html");
        webview_3d.loadDataWithBaseURL(null, htmlContent, "text/html", "UTF-8", null);
    }
}