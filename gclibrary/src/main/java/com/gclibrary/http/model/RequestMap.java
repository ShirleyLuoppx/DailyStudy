package com.gclibrary.http.model;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 12985 on 2017/3/21.
 */
public class RequestMap {
    private Map<String, Object> map = new HashMap<>();

    public void put(String key, Integer value) {
        if (!TextUtils.isEmpty(key) && value != null) {
            map.put(key, value);
        }
    }

    public void put(String key, Long value) {
        if (!TextUtils.isEmpty(key) && value != null) {
            map.put(key, value);
        }
    }

    public void put(String key, Float value) {
        if (!TextUtils.isEmpty(key) && value != null) {
            map.put(key, value);
        }
    }

    public void put(String key, Double value) {
        if (!TextUtils.isEmpty(key) && value != null) {
            map.put(key, value);
        }
    }

    public void put(String key, char value) {
        if (!TextUtils.isEmpty(key)) {
            map.put(key, value);
        }
    }

    public void put(String key, String value) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
            map.put(key, value);
        }
    }

    public void put(String key, Boolean value) {
        if (!TextUtils.isEmpty(key)) {
            map.put(key, value);
        }
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public String getParams() {
        String strTemps = "";
        for (String key : map.keySet()) {
            strTemps = strTemps + key + "=" + map.get(key) + "&";
        }
        if (strTemps.endsWith("&")) {
            strTemps = strTemps.substring(0, strTemps.length() - 1);
        }
        return strTemps;
    }
}
