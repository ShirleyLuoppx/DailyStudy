package com.ppx.dailystudy.di.dagger.daggerdi;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ppx.dailystudy.R;
import com.ppx.dailystudy.di.dagger.cook.Chef;

import javax.inject.Inject;

import dagger.android.DaggerActivity;

/**
 * 这个dagger的例子的主要步骤：
 * 1、MyApplication 集成了 DaggerApplication构建了一个DaggerCookAppComponent注射器
 * ２、在注射器中CookAppComponent　中通过 @Component 声明了接收器　AndroidSupportInjectionModule.class, CookModules.class, ActivityModules.class
 * ３、在ActivityModules　中通过 @ContributesAndroidInjector 声明需要依赖注入数据的类是 DaggerMainActivity
 * ４、在DaggerMainActivity　中通过　@Inject　来声明需要注入是Chef
 * ５、在Chef中通过　@Inject　声明需要通过构造方法来给 menu 注入数据
 * 6、在Menu中通过  @Inject 声明需要通过构造方法来给 menus 注入数据
 * 7、在CookModules中通过 @Module、@Provides 注解来声明本类是通过@Provide方式来提供数据的
 */
public class DaggerMainActivity extends DaggerActivity {

    @Inject
    Chef chef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        findViewById(R.id.ben_show_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DaggerMainActivity.this, chef.cook(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
