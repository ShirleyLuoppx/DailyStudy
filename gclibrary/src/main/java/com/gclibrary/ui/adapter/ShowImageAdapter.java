package com.gclibrary.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ShowImageAdapter extends PagerAdapter {

    private ArrayList<ImageView> mImageViews;

    public ShowImageAdapter(ArrayList<ImageView> imageViews) {

        this.mImageViews = imageViews;
    }

    @Override
    public int getCount() {

        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    /**
     * PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
     */
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {

        view.removeView(mImageViews.get(position));
    }

    /**
     * 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，
     * 我们将要显示的 ImageView 加入到 ViewGroup 中，然后作为返回值返回即可
     */
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        view.addView(mImageViews.get(position), 0);
        return mImageViews.get(position);
    }
}