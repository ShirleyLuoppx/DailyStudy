package com.ppx.dailystudy.di.dagger.daggerdi;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * 1、继承了DaggerApplication
 * 2、在applicationInjector 方法中构建了一个注射器
 */
public class MyApplication extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerCookAppComponent.builder().create(this);
    }

}
