package com.ppx.dailystudy.di.dagger.daggerdi;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModules {

   /**
    * 使用  @ContributesAndroidInjector   注解来标记是哪个类需要依赖注入
    * @return
    */
   @ContributesAndroidInjector
   abstract DaggerMainActivity contributesMainActivity();
}
