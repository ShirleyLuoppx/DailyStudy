package com.gclibrary.db.base;

import java.util.List;

/**
 * Created by guangchuan on 2016/3/16.
 */
public interface BaseDao<T> {

    void saveOrUpdate(T obj);

    void saveOrUpdate(List<T> objs);

    void save(T obj);

    void save(List<T> list);

    void update(T obj);

    void update(List<T> objs);

    void delete(String id);

    void delete(T obj);

    void delete(String[] ids);

    void deleteAll();

    T getById(String id);

    List<T> getByIds(String[] ids);

    List<T> findAll();

    List<T> queryByColumn(String columnName, String value);

    List<T> queryBtColumns(String[] columnNames, String[] values);

    List<T> queryBtColumns(List<String> columnNames, List<String> values);

    void deleteByColumn(String columnName, String value);

    List<String[]> queryBySql(String sql);
}
