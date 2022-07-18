package com.ppx.dailystudy.androiddevelopartisticexplorations.chap02ipc;

import java.io.Serializable;

/**
 * @Author: LuoXia
 * @CreateDate: 2022/7/18 22:46
 * @Description: 用于   用Serializable实现序列化和反序列化
 */
public class User implements Serializable {

    /**
     * serialVersionUID用于标识类的序列化
     */
    private static final long serialVersionUID = 1873187387686127368L;

    private int id;
    private String name;
    private String project;

    public User(int id, String name, String project) {
        this.id = id;
        this.name = name;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
