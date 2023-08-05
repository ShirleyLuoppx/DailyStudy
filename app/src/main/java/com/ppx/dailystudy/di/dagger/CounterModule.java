package com.ppx.dailystudy.di.dagger;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;

/**
 * Dagger 依赖注入的初步使用
 */

/**
 * 第一步：使用@Module表明是哪个类需要注入
 */
@Module
class CounterModule {
    private String TAG = "CounterModule";
    int count = 100;

    /**
     * 第二步：使用@Provides表明本方法是提供给别人使用的方法，这里的返回值很重要，其他注入的参数的泛型是对应的这个类型的才能被注入进来
     * @return
     */
    @Provides
    Integer provideInteger() {
        Log.d(TAG, "provideInteger:  --");
        return count++;
    }
}

//Provider注入
class ProviderCount {
    private String TAG = "ProviderCount";

    /**
     * 第三步：使用@Inject 表明本参数是需要被注入的，这里是使用的Provider的注入方式，这种注入方式每次去获取的值都是不一样的，
     * 且这里的泛型需要跟@Provides方法的返回值类型一致才能被注入;
     */
    @Inject
    Provider<Integer> provider;
    @Inject
    Provider<Integer> integerProvider;

    void print() {
        Log.d(TAG, "print: --");
        Log.d(TAG, "print: " + provider.get());
        Log.d(TAG, "print: " + provider.get());
        Log.d(TAG, "print: " + provider.get());
//        Log.d(TAG, "print: " + integerProvider.get());
    }
}

//Lazy 注入
class LazyCounter {
    private String TAG = "LazyCounter";
    /**
     * 第三步：使用@Inject 表明本参数是需要被注入的，这里是使用的Lazy懒加载的方式来注入的，这里的参数泛型也是需要跟@Provides的返回值类型一致才能被注入
     */
    @Inject
    Lazy<Integer> lazy;

    void print() {
        Log.d(TAG, "print: ---");
        Log.d(TAG, "print: ---" + lazy.get());
        Log.d(TAG, "print: ---" + lazy.get());
        Log.d(TAG, "print: ---" + lazy.get());

    }
}
