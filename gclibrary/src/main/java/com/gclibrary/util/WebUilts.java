package com.gclibrary.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gclibrary.view.imagepicker.PhotoPickerUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/24 0024.
 */

public class WebUilts {
    private WebView webView;

    public WebUilts(WebView webView) {
        this.webView = webView;
        initWebData();
    }

    public void initWebData() {
//        webView.setFocusable(false);
        // 启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //是否展示缩放
        settings.setDisplayZoomControls(false);

        // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 缩小或放大工具
        settings.setBuiltInZoomControls(true);
        // 双击缩放
        settings.setUseWideViewPort(true);
        // 修改字体大小
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        //支持html5某些标签
        settings.setDomStorageEnabled(true);

        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setUseWideViewPort(false);//这个对适配很关键，如果为true，整体缩小
//        //缩小到容器最小宽度
        settings.setLoadWithOverviewMode(true);
        // /优先使用缓存
        // webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setPluginState(WebSettings.PluginState.ON);

//        getWebImgClick();
        initListener();

//        android:hardwareAccelerated="true" 设置才能播放视频
//        webView.resumeTimers()
//        webView.pauseTimers();

    }

    private void initListener() {
        /**
         * 加载开始
         */
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (webViewClientListener != null) {
                    webViewClientListener.onPageStarted();
                }
            }

            /**
             * 所有url都会进入true为内部加载，false为外部加载
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if (webViewClientListener != null) {
                    return webViewClientListener.shouldOverrideUrlLoading();
                }
                return true;
            }

            /**
             * 加载结束
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                setWebImageClick(view);
//                int w = View.MeasureSpec.makeMeasureSpec(0,
//                        View.MeasureSpec.UNSPECIFIED);
//                int h = View.MeasureSpec.makeMeasureSpec(0,
//                        View.MeasureSpec.UNSPECIFIED);
//                view.measure(w, h);
                if (webViewClientListener != null) {
                    webViewClientListener.onPageFinished();
                }
            }
        });
    }

    private WebViewClientListener webViewClientListener;

    public void setWebViewClientListener(WebViewClientListener webViewClientListener) {
        this.webViewClientListener = webViewClientListener;
    }

    public interface WebViewClientListener {
        void onPageStarted();

        boolean shouldOverrideUrlLoading();

        void onPageFinished();
    }

    /**
     * 设置图片点击回调监听
     */
    public void getWebImgClick() {
        webView.addJavascriptInterface(new OnClickImg() {
            @JavascriptInterface
            @Override
            public void showBigImg(int pos, String url) {
                if (!TextUtils.isEmpty(url)) {
                    ArrayList<String> list = new ArrayList<>();
                    String[] strs = url.split(",");
                    for (String str : strs) {
                        list.add(str);
                    }
                    PhotoPickerUtils.showPhoto(webView.getContext(), list, pos);
                }
            }

        }, "app");
    }

    /**
     * 设置网址中图片点击事件
     */
    public void setWebImageClick(WebView view) {
        String jsCode = "javascript:(function(){" +
                "var imgs=document.getElementsByTagName('img');" +
                "var imgUrls='';" +
                "for(var i=0;i<imgs.length;i++){" +
                "imgUrls=imgUrls+imgs[i].src+',';" +
                "imgs[i].index=i;" +
                "imgs[i].onclick=function(){" +
                "window.app.showBigImg(this.index,imgUrls);" +
                "}}})()";
        view.loadUrl(jsCode);
    }

    /**
     * Js調用Java接口
     */
    private interface OnClickImg {
        void showBigImg(int pos, String url);
    }

    public void setHtml(String html) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style> h4 p{display:inline;font-size:16px;color:#333} .content_title p {display: inline;margin: 0;} html,p{font-size:14px;color:#666;font-family: \"Microsoft YaHei\", Arial;} table {width: 100%!important;} a {text-decoration: none!important;border-bottom: 1px solid #4f81bd;color: #4f81bd;}  .exp0 { background-image: url(file:///android_asset/image/exp0.png);} .exp1 {background-image: url(file:///android_asset/image/exp1.png);}</style></head>");
        sb.append("<body style=\" padding-left:10px; padding-right: 5px;\">");
        sb.append(html);
        sb.append("</body>");
        sb.append("</html>");
        String newStr = sb.toString().replace("<img", "<img width=\"100%\"");
        webView.loadDataWithBaseURL("file:///android_asset/", newStr, "text/html", "UTF-8", null);
    }

    public void setUrl(String url) {
        webView.loadUrl(TextUtils.isEmpty(url) ? "http://www.baidu.com" : url);
    }

    public WebView getWebView() {
        return webView;
    }
}
