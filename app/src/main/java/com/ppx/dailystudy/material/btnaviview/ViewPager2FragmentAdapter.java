package com.ppx.dailystudy.material.btnaviview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * Auth：Luoxia
 * Date：2023/6/13
 * DESC:
 */
public class ViewPager2FragmentAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList;

    public ViewPager2FragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
