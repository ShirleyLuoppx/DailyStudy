package com.ppx.dailystudy.di.dagger.cook;

import java.util.Map;

import javax.inject.Inject;

class Menu {
    private Map<String, Boolean> menus;

    @Inject
    public Menu(Map<String, Boolean> menus) {
        this.menus = menus;
    }

    public Map<String, Boolean> getMenus() {
        return menus;
    }
}
