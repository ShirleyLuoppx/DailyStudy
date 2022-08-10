package com.gclibrary;

import android.content.Context;

import com.gclibrary.config.DbConfig;

/**
 * Created by Administrator on 2019/2/21 0021.
 */

public class GcHelper {
    private static Context context;
    private static GcHelper gcHelper;
    private DbConfig dbConfig = new DbConfig();//默认，一般自己配置

    public GcHelper(Context context) {
        this.context = context;
        SpUtils.init(context);
    }

    public synchronized static void init(Context context) {
        if (gcHelper == null) {
            gcHelper = new GcHelper(context);
        }
    }

    public static GcHelper getInstance() {
        return gcHelper;
    }

    public Context getContext() {
        return context;
    }

    public void configDb(DbConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public DbConfig getDbConfig() {
        return dbConfig;
    }
}
