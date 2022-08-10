package com.gclibrary.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.gclibrary.R;
import com.gclibrary.R2;
import com.gclibrary.base.BaseActivity;
import com.gclibrary.util.Tools;
import com.gclibrary.util.WebUilts;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/3/22 0022.
 */

public class WebActivity extends BaseActivity {
    public static final String URL = "WEB_URL";
    @BindView(R2.id.pd)
    ProgressBar pd;
    @BindView(R2.id.wv)
    WebView wv;
    private String url = "http://www.baidu.com";
    public WebUilts webUilts;

    @Override
    protected int getLayoutId() {
        baseTitle.setVisibility(View.VISIBLE);
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        try {
            url = getBundle().getString(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        webUilts = new WebUilts(wv);
    }

    @Override
    protected void initData() {
        initListener();
        webUilts.setUrl(url);
//        wv.setBackgroundColor(0);
//        wv.getBackground().setAlpha(0);
//        webUilts.setHtml("<p style=\"text-indent: 2em;\"><span style=\"text-align: justify; text-indent: 2em;\">“有朋自远方来，不亦说乎”。</span><span style=\"text-align: justify; text-indent: 2em;\">2019</span><span style=\"text-align: justify; text-indent: 2em;\">年</span><span style=\"text-align: justify; text-indent: 2em;\">2</span><span style=\"text-align: justify; text-indent: 2em;\">月</span><span style=\"text-align: justify; text-indent: 2em;\">15</span><span style=\"text-align: justify; text-indent: 2em;\">日，由北京中银（南昌）律师事务所张艾主任带队，该所刑辩服务部、建筑施工法律服务部的相关负责人、青年律师来到我所，开启了重庆同行同业学习交流的第一站。学习交流会由儒泰律师事务所主任、办案大全团队核心创始人郑华友主持，联合创始人盛宏文、孙晓明、宫正，以及儒泰所相关部门负责人、律师参加会议。</span></p><p style=\"text-indent: 2em; text-align: justify;\">根据学习交流日程安排，张艾主任一行在郑华友主任的带领下，首先对儒泰律师事务所民事办公区、刑事办公区、会客服务区、法律文化区和法律服务荣誉室进行了参观，详细聆听我所的发展历程、人员组织架构、发展规划，并重点对各区域运行规范、组织领导程序进行了了解，对儒泰的发展理念和规划目标方向表示认可和赞赏。</p><p style=\"text-align: justify; text-indent: 0em;\"><img src=\"http://admin.badq.com.cn/src/Files/Pictures/Ueditor/20190218/6368608291115792261777325.png\" title=\"图片1.png\" alt=\"图片1.png\"/></p><p style=\"text-align: center; text-indent: 0em;\"><span style=\"color: rgb(122, 68, 66); font-family: -apple-system-font, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px; text-align: center; text-indent: 34px;\">（图一：学习交流会现场）</span></p><p style=\"text-indent: 2em; text-align: justify;\">交流会上，郑华友主任代表全所和办案大全团队，对张艾主任一行的到访表示了热烈的欢迎，围绕重庆市律师行业及我所发展模式、青年律师的培养和管理、案源拓展等内容进行了详细的介绍，办案大全团队联合创始人盛宏文、孙晓明、宫正也分别就内容运行、活动运营和品牌运营进行介绍，特别是针对办案大全平台与青年律师培养、案源连接上的工作经验进行了交流。</p><p style=\"text-align: justify; text-indent: 0em;\"><img src=\"http://admin.badq.com.cn/src/Files/Pictures/Ueditor/20190218/6368608297192803486574520.png\" title=\"图片2.png\" alt=\"图片2.png\"/></p><p style=\"text-align: center; text-indent: 0em;\"><span style=\"color: rgb(122, 68, 66); font-family: -apple-system-font, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px; text-align: center; text-indent: 34px;\">（图二：儒泰律师事务所盛宏文、郑华友、孙晓明（左起））</span></p><p style=\"text-align: justify; text-indent: 0em;\"><img src=\"http://admin.badq.com.cn/src/Files/Pictures/Ueditor/20190218/6368608300141955488266192.png\" title=\"图片4.png\" alt=\"图片4.png\"/></p><p style=\"text-align: center; text-indent: 0em;\"><span style=\"color: rgb(122, 68, 66); font-family: -apple-system-font, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px; text-align: center; text-indent: 34px;\">（图三：北京中银（南昌）律师事务所主任张艾）</span></p><p style=\"text-indent: 2em; text-align: justify;\">会上，张艾主任代表北京中银（南昌）律师事务所，对我所在刑辩、建筑施工领域的工作成绩表示充分肯定，就该所在民事建筑领域多年来如何做大做强，打造江西省知名的建筑领域专业法律服务团队进行了经验交流，并对刑事诉讼法律服务谈到了自己的独特理解。会议中，我所同北京中银（南昌）律师事务所还围绕律师师事务所合并过程中的管理及利益框架事项，公司化律师事务所的管理和分配制度，专业化律师的培养方向等核心问题进行了交流。双方分别从重庆和江西的地区环境、人文价值理念、法律专业领域的工作模式差异等方面，针对性对法律服务从案源培育、服务节点设置、绩效考核验收以及律所自身文化建设交换了意见。最后，双方还就如何在地区、领域间的合作达成了相关合作意向，为进一步在跨地区间的法律服务联动打下坚实基础。</p><p style=\"text-align: justify; text-indent: 0em;\"><img src=\"http://admin.badq.com.cn/src/Files/Pictures/Ueditor/20190218/6368608305738776444867881.png\" title=\"图片3.png\" alt=\"图片3.png\"/></p><p style=\"text-align: center; text-indent: 0em;\"><span style=\"color: rgb(122, 68, 66); font-family: -apple-system-font, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px; text-align: center; text-indent: 34px;\">（图四：学习交流现场）</span></p><p style=\"text-indent: 2em; text-align: justify;\">“东园之树，枝条载荣。竞用新好，以招余情。人亦有言，日月于征。安得促席，说彼平生。”2019年，儒泰律师事务所将强化办案大全平台作用，以“高层次、规模化、精细化”为发展目标，以“专业分工、团队合作、优质高效”为服务特色，以提供法律服务和科技创新连接并重发展，加强不同区域、不同领域的律师师事务所交流学习，向客户提供专业、高效的法律服务。&nbsp;</p><p style=\"text-align: justify; text-indent: 0em;\"><span style=\"color: rgb(122, 68, 66); font-family: -apple-system-font, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px; text-align: center; text-indent: 28px;\"><img src=\"http://admin.badq.com.cn/src/Files/Pictures/Ueditor/20190218/6368608312324942734248613.png\" title=\"图片5.png\" alt=\"图片5.png\"/></span></p><p style=\"text-align: center; text-indent: 0em;\"><span style=\"color: rgb(122, 68, 66); font-family: -apple-system-font, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei UI&quot;, &quot;Microsoft YaHei&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px; text-align: center; text-indent: 28px;\">（图五：会后合影）</span></p>");

    }

    private void initListener() {
        webUilts.setWebViewClientListener(new WebUilts.WebViewClientListener() {
            @Override
            public void onPageStarted() {
                if (isFinishing()) return;
                pd.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading() {
                return true;
            }

            @Override
            public void onPageFinished() {
                if (isFinishing()) return;
                pd.setVisibility(View.GONE);
            }
        });

        wv.setWebChromeClient(new WebChromeClient() {
            /**
             * 进度网页变化
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (isFinishing()) return;
                if (newProgress > pd.getProgress()) {
                    pd.setProgress(newProgress);
                }
            }

            /**
             * 获取网页标题
             */
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                baseTitle.setTvTitle(title);
                Tools.setTextMarquee(baseTitle.getTvTitle());
            }

            /**
             * 获取网页图标
             */
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        wv.onResume();
    }


    @Override
    protected void onDestroy() {
        wv.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (wv.canGoBack()) {
                wv.goBack();// 返回上一页面
                return true;
            } else {
                // System.exit(0);//退出程序
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
