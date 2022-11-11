package com.ppx.dailystudy.view.straggergrid;

/**
 * @Author: LuoXia
 * @Date: 2022/11/11 18:12
 * @Description:
 */
public class StaggeredBook {
    private String name;
    private int height;

    public StaggeredBook() {
    }

    public StaggeredBook(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
