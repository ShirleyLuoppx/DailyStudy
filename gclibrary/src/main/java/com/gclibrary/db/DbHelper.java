package com.gclibrary.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gclibrary.GcHelper;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbHelper extends OrmLiteSqliteOpenHelper {
    //    private static final String DATABASE_NAME = SpUtils.getInstance().getAccountId() + Const.fileName + ".db";
    private Map<String, Dao> daos = new HashMap<>();

    public DbHelper(Context context) {
        super(context, GcHelper.getInstance().getDbConfig().dbName, null, GcHelper.getInstance().getDbConfig().dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            if (GcHelper.getInstance().getDbConfig().clzs != null) {
                for (Class clz : GcHelper.getInstance().getDbConfig().clzs) {
                    TableUtils.createTable(connectionSource, clz);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            if (GcHelper.getInstance().getDbConfig().clzs != null) {
                for (Class clz : GcHelper.getInstance().getDbConfig().clzs) {
                    TableUtils.dropTable(connectionSource, clz, true);
                }
            }
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DbHelper instance;

    public static DbHelper getHelper(Context context) {

        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DbHelper.class) {
                if (instance == null) {
                    instance = new DbHelper(context);
                }
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {

        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
