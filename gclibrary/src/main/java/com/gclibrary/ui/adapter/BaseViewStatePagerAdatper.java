package com.gclibrary.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 12985 on 2017/2/7.
 */
public class BaseViewStatePagerAdatper extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private List<String> titles;
    private FragmentManager fm;

    public BaseViewStatePagerAdatper(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles == null) return null;
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 方法一，这种方式可以移除所有fragment缓存
     **/
    public void removeAllFragment() {
        for (int i = fragments.size() - 1; i >= 0; i--) {
            Fragment fragment = fragments.get(i);
            //然后从List中移除
            fragments.remove(fragment);
            //先从Transaction移除
            removeFragmentInternal(fragment);
        }
        //最后刷新Adapter
        notifyDataSetChanged();
    }

    /**
     * 从Transaction移除Fragment
     *
     * @param fragment 目标Fragment
     */
    private void removeFragmentInternal(Fragment fragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.remove(fragment);
        transaction.commitNow();
    }

//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        // 覆写destroyItem并且空实现,这样每个Fragment中的视图就不会被销毁
//         super.destroyItem(container, position, object);
//    }
//
//    @Override
//    public int getItemPosition(Object object) {
//        return PagerAdapter.POSITION_NONE;
//    }
//
//    @Override
//    public void finishUpdate(ViewGroup container) {
//        try {
//            super.finishUpdate(container);
//        } catch (NullPointerException nullPointerException) {
//            System.out.println("Catch the NullPointerException in FragmentPagerAdapter.finishUpdate");
//        }
//    }

}
