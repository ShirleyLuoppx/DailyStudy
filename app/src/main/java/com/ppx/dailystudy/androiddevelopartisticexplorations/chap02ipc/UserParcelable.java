package com.ppx.dailystudy.androiddevelopartisticexplorations.chap02ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author: LuoXia
 * @CreateDate: 2022/7/21 21:39
 * @Description:
 */
public class UserParcelable implements Parcelable {

    /**
     * 1、序列化
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(project);
    }

    /**
     * 2、反序列化
     * @param in
     */
    protected UserParcelable(Parcel in) {
        id = in.readInt();
        name = in.readString();
        project = in.readString();
    }

    /**
     * 3、内容描述
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserParcelable> CREATOR = new Creator<UserParcelable>() {
        @Override
        public UserParcelable createFromParcel(Parcel in) {
            return new UserParcelable(in);
        }

        @Override
        public UserParcelable[] newArray(int size) {
            return new UserParcelable[size];
        }
    };

    private int id;
    private String name;
    private String project;

    public UserParcelable(int id, String name, String project) {
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
