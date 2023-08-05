package com.ppx.dailystudy.di.dagger;

import java.util.Map;

import javax.inject.Inject;

class Chef implements Cook {
    private Menu menu;

    @Inject
    public Chef(Menu menu) {
        this.menu = menu;
    }


    @Override
    public String cook() {
        StringBuilder sb = new StringBuilder();
        Map<String, Boolean> menus = menu.getMenus();
        for (Map.Entry<String, Boolean> menu : menus.entrySet()) {
            if (menu.getValue()) {
                sb.append(menu.getKey()).append("，");
            }
        }
        return sb.toString();
    }
}
