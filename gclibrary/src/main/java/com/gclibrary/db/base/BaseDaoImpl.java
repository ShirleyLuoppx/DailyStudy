package com.gclibrary.db.base;

import android.content.Context;

import com.gclibrary.db.DbHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by guangchuan on 2016/3/16.
 */
public class  BaseDaoImpl<T> implements BaseDao<T> {
    private DbHelper dbHelper;
    private Dao<T, String> daoOpe;
    private Context context;
    private Class clazz;

    public BaseDaoImpl(Context context) {
        ParameterizedType pt = (ParameterizedType) getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[0];
        this.context = context;
        try {
            dbHelper = DbHelper.getHelper(context);
            daoOpe = dbHelper.getDao(clazz);
//            AndroidDatabaseConnection androidDatabaseConnection = news AndroidDatabaseConnection(dbHelper.getWritableDatabase(), true);
//            daoOpe.setAutoCommit(androidDatabaseConnection, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void saveOrUpdate(T obj) {
        try {
            daoOpe.createOrUpdate(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void saveOrUpdate(final List<T> objs) {//性能会比较低
        try {
//            DatabaseConnection conn = getDaoOpe().startThreadConnection();
//            Savepoint savePoint = conn.setSavePoint(null);
            daoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (T obj : objs)
                        daoOpe.createOrUpdate(obj);
                    return null;
                }
            });
//            conn.commit(savePoint);
//            getDaoOpe().endThreadConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(T obj) {
        try {
            daoOpe.create(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void save(List<T> objs) {
        try {
            daoOpe.create(objs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T obj) {
        try {
            daoOpe.update(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void update(final List<T> objs) {//性能低
        try {
            daoOpe.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (T obj : objs) {
                        update(obj);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try {
            daoOpe.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T obj) {
        try {
            daoOpe.delete(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String[] ids) {
        List<String> list = new ArrayList<>();
        for (String id : ids) {
            list.add(id);
        }
        try {
            daoOpe.deleteIds(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try {
            daoOpe.deleteBuilder().delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public T getById(String id) {
        try {
            return daoOpe.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> getByIds(String[] ids) {
        List<T> list = new ArrayList<>();
        for (String id : ids) {
            list.add(getById(id));
        }
        return list;
    }

    @Override
    public List<T> findAll() {
        try {
            return daoOpe.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> queryByColumn(String columnName, String value) {
        try {
            return daoOpe.queryBuilder().where().eq(columnName, value).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> queryBtColumns(String[] columnNames, String[] values) {
        try {
            QueryBuilder<T, String> queryBuilder = daoOpe
                    .queryBuilder();
            Where<T, String> where = queryBuilder.where();
            for (int i = 0; i < columnNames.length; i++) {
                where.eq(columnNames[i], values[i]);
                if (i < columnNames.length - 1) {
                    where.and();
                }
            }
            return queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<T> queryBtColumns(List<String> columnNames, List<String> values) {
        try {
            QueryBuilder<T, String> queryBuilder = daoOpe
                    .queryBuilder();
            Where<T, String> where = queryBuilder.where();
            for (int i = 0; i < columnNames.size(); i++) {
                where.eq(columnNames.get(i), values.get(i));
                if (i < columnNames.size() - 1) {
                    where.and();
                }
            }
            return queryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void deleteByColumn(String columnName, String value) {
        try {
            daoOpe.deleteBuilder().where().eq(columnName, value).query();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String[]> queryBySql(String sql) {
        try {
            return daoOpe.queryRaw(sql).getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dao<T, String> getDaoOpe() {
        return daoOpe;
    }

    public void close() {
        dbHelper.close();
    }

    public T getSqlStringToObject(String[] strs) {
        try {
            Object object = clazz.newInstance();
            Field[] fields = clazz.getFields();
            int index = 0;
            for (int i = 0; i < fields.length; i++) {
                DatabaseField databaseField = fields[i].getAnnotation(DatabaseField.class);
                if (databaseField != null) {
                    Class type = fields[i].getType();
                    if (type == int.class || type == Integer.class) {
                        fields[i].set(object, Integer.parseInt(strs[index]));
                    }
                    if (type == String.class) {
                        fields[i].set(object, strs[index]);
                    }
                    if (type == boolean.class || type == Boolean.class) {
                        fields[i].set(object, Boolean.parseBoolean(strs[index]));
                    }
                    if (type == double.class || type == Double.class) {
                        fields[i].set(object, Double.parseDouble(strs[index]));
                    }
                    if (type == float.class || type == Float.class) {
                        fields[i].set(object, Float.parseFloat(strs[index]));
                    }
                    index++;
                }
            }
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getSqlStringToArray(List<String[]> list) {
        if (list == null) return new ArrayList<>();
        List<T> temps = new ArrayList<>();
        for (String[] strs : list) {
            T t = getSqlStringToObject(strs);
            if (t != null) {
                temps.add(t);
            }
        }
        return temps;
    }
}
