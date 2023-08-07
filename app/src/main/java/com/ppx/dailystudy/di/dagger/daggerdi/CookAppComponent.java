package com.ppx.dailystudy.di.dagger.daggerdi;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * CookAppComponent相当于是一个注射器
 * AndroidSupportInjectionModule.class, CookModules.class, ActivityModules.class   相当于是接收器
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, CookModules.class, ActivityModules.class})
public interface CookAppComponent extends AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApplication> {
    }
}
