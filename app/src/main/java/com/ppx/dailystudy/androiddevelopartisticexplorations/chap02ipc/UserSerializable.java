package com.ppx.dailystudy.androiddevelopartisticexplorations.chap02ipc;

import java.io.Serializable;

/**
 * @Author: LuoXia
 * @CreateDate: 2022/7/18 22:46
 * @Description: 用于   用Serializable实现序列化和反序列化
 *
 * Serializable和Parcelable的区别和联系：
 * 联系：都可用于序列化和反序列化
 * 区别：Serializable是Java提供的序列化方式，使用简单，但开销较大，过程需要大量的IO操作；
 *      Parcelable是Android提供的序列化方式，使用稍微难一点儿，但是效率很高，Android开发中，首选Parcelable。
 *      但是当
 */
public class UserSerializable implements Serializable {

    /**
     * serialVersionUID用于标识类的序列化
     */
    private static final long serialVersionUID = 1873187387686127368L;

    private int id;
    private String name;
    private String project;

    public UserSerializable(int id, String name, String project) {
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
