package com.gclibrary.config;

import java.util.List;

/**
 * Created by Administrator on 2019/3/8 0008.
 */

public class DbConfig {
    public String dbName = "GCDB.db";
    public List<Class> clzs;
    public int dbVersion = 1;

    @Override
    public String toString() {
        return "DbConfig{" +
                "dbName='" + dbName + '\'' +
                ", clzs=" + clzs +
                ", dbVersion=" + dbVersion +
                '}';
    }
}
