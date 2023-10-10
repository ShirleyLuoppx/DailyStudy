package com.ppx.dailystudy.test.vrandgif.stlrender.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by gw on 2016/7/1.
 */

public class FragmentUtils {

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int frameId, boolean addToBackStacks){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, fragment.getClass().getSimpleName());
        if(addToBackStacks) transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int frameId, boolean addToBackStacks){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        if(addToBackStacks) transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
    }

}
