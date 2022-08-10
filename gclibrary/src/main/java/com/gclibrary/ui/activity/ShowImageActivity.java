package com.gclibrary.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gclibrary.R;
import com.gclibrary.R2;
import com.gclibrary.base.BaseActivity;
import com.gclibrary.glide.GlideUtils;
import com.gclibrary.ui.adapter.ShowImageAdapter;
import com.gclibrary.util.ScrUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.widget.TouchImageView;

/**
 * Created by 12985 on 2017/6/9.
 */

public class ShowImageActivity extends BaseActivity {

    @BindView(R2.id.vp)
    ViewPager vp;
    @BindView(R2.id.tv_count)
    TextView tvCount;
    //滚动展示图片的适配器
    private ShowImageAdapter adapter;
    //填装图片的容器集合
    private ArrayList<ImageView> images;
    private int pos = 0;
    //传入展示的图片链接的集合
    private List<String> picList = new ArrayList<>();

    public static void start(Context context, List<String> photos, int position) {
        Intent intent = new Intent(context, ShowImageActivity.class);
        intent.putExtra("position", position);
        intent.putStringArrayListExtra("data", (ArrayList<String>) photos);
        context.startActivity(intent);
    }

    public static void start(Context context, String image) {
        List<String> lPhoto = new ArrayList<>();
        lPhoto.add(image);
        start(context, lPhoto, 0);
    }

    @Override
    protected int getLayoutId() {
        baseTitle.setVisibility(View.GONE);
        return R.layout.activity_show_image;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//大于23
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        }
        vp = findViewById(R.id.vp);
        tvCount = findViewById(R.id.tv_count);
    }

    @Override
    protected void initData() {
        if (getIntent().hasExtra("data")) {
            picList = getIntent().getStringArrayListExtra("data");
        }
        if (getIntent().hasExtra("position")) {
            pos = getIntent().getIntExtra("position", 0);
            if (picList.size() > 0) {
                tvCount.setText((pos + 1) + "/" + picList.size());
            }
        }
        initListener();
        images = new ArrayList<>();
        adapter = new ShowImageAdapter(images);
        vp.setAdapter(adapter);
        //加载图片
        loadImage(picList);
    }


    private void initListener() {
        //设置pager的滑动监听
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pos = position;
                tvCount.setText((position + 1) + "/" + picList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        String spic = picList.get(mMvpShowIamgeWall.getCurrentItem());
//        if (!TextUtils.isEmpty(spic)) {
//            saveImage(spic);
//        }
    }

    /**
     * 加载图片资源
     */
    private void loadImage(List<String> list) {
        if (list != null) {
            if (list.size() > 0) {
                for (String url : list) {
                    TouchImageView imageView = new TouchImageView(context);
                    ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    int newHeight = ScrUtils.getScreenHeight(context);
                    lp.width = ScrUtils.getScreenWidth(context);
                    lp.height = newHeight;
                    imageView.setLayoutParams(lp);
                    if (url.startsWith("file://")) {
                        url = url.substring(7, url.length());
                        GlideUtils.loadImageFileOrg(context, url, imageView, 0);
                    } else {
                        GlideUtils.loadImageOrg(context, url, imageView, 0);
                    }
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });

//                    imageView.setOnLongClickListener(news View.OnLongClickListener() {
//                        @Override
//                        public boolean onLongClick(View v) {
//
//                            List<String> lPhoto = Arrays.asList("保存到手机", "转发给好友", "转发的群");
//                            Dialog dPhoto = DialogUtils.dialogBottom(context, news DialogUtils.OnDialogResult() {
//                                @Override
//                                public void onDialogOk(int position) {
//
//                                }
//                            });
//                            DialogUtils.setDialogContent(context, dPhoto, lPhoto, "");
//                            Tools.showDialog(dPhoto);
//                            return true;
//                        }
//                    });

                    images.add(imageView);
                }
                adapter.notifyDataSetChanged();
                vp.setCurrentItem(pos);
                tvCount.setText((pos + 1) + "/" + picList.size());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
