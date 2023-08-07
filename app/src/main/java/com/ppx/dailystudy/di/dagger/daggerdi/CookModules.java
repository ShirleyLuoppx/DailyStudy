package com.ppx.dailystudy.di.dagger.daggerdi;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Provides 注解表明该方法是提供数据的
 */
@Module
public class CookModules {
    @Singleton
    @Provides
    public Map<String, Boolean> providerMenus() {
        Map<String, Boolean> menus = new LinkedHashMap<>();
        menus.put("酸菜牛肉丝", true);
        menus.put("鱼香肉丝", true);
        menus.put("凉拌鸡丝", false);
        menus.put("酸辣土豆丝", true);
        return menus;
    }
}
