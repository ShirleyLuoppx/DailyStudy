package com.gclibrary.http;

import com.gclibrary.util.LogUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by 12985 on 2017/4/17.
 */
public class HttpHelper {
    private static final int DEFAULT_TIMEOUT = 20;

    public void get(final String url, final Map<String, Object> map, final HttpResult httpResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    LogUtils.i("地址：" + url);
                    StringBuilder sb = new StringBuilder();
                    sb.append(url);
                    if (map != null) {
                        sb.append("?");
                        Set<String> sets = map.keySet();
                        for (String key : sets) {
                            sb.append(key + "=");
                            sb.append(URLEncoder.encode(map.get(key).toString(), "utf-8") + "&");
                        }
                        if (sb.toString().endsWith("&")) {
                            sb.delete(sb.toString().length() - 1, sb.toString().length());
                        }
                    }
                    URL getUrl = new URL(sb.toString());
                    connection = (HttpURLConnection) getUrl
                            .openConnection();
                    connection.setConnectTimeout(DEFAULT_TIMEOUT * 1000);
                    connection.setReadTimeout(DEFAULT_TIMEOUT * 1000);
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    InputStream inputStream = null;
                    if (responseCode == 200) {
                        inputStream = new BufferedInputStream(connection.getInputStream());
                    } else {
                        inputStream = new BufferedInputStream(connection.getErrorStream());
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String lines;
                    while ((lines = reader.readLine()) != null) {
                        LogUtils.i("自定义网络返回结果：" + lines);
                        if (httpResult != null) {
                            httpResult.onSuccess(lines);
                        }
                    }
                    reader.close();
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (httpResult != null) {
                        httpResult.onFail(e);
                    }
                }
            }
        }).start();
    }

    public void post(final String url, final Map<String, Object> map, final HttpResult httpResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtils.i("地址：" + url);
                    URL postUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) postUrl
                            .openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(DEFAULT_TIMEOUT * 1000);
                    connection.setReadTimeout(DEFAULT_TIMEOUT * 1000);
                    connection.setUseCaches(false);
                    connection.setInstanceFollowRedirects(true);
                    connection.setRequestProperty("Content-Type",
                            "application/x-www-form-urlencoded");
                    connection.connect();
                    DataOutputStream out = new DataOutputStream(connection
                            .getOutputStream());
                    StringBuilder sb = new StringBuilder();
                    if (map != null) {
                        Set<String> sets = map.keySet();
                        for (String key : sets) {
                            sb.append(key + "=");
                            sb.append(URLEncoder.encode(map.get(key).toString(), "utf-8") + "&");
                        }
                        if (sb.toString().endsWith("&")) {
                            sb.delete(sb.toString().length() - 1, sb.toString().length());
                        }
                    }
                    LogUtils.i("请求数据:" + sb.toString());
                    out.writeBytes(sb.toString());
                    out.flush();
                    out.close();
                    int responseCode = connection.getResponseCode();
                    InputStream inputStream = null;
                    if (responseCode == 200) {
                        inputStream = new BufferedInputStream(connection.getInputStream());
                    } else {
                        inputStream = new BufferedInputStream(connection.getErrorStream());
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String lines;
                    while ((lines = reader.readLine()) != null) {
                        LogUtils.i("自定义网络返回结果：" + lines);
                        if (httpResult != null) {
                            httpResult.onSuccess(lines);
                        }
                    }
                    reader.close();
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (httpResult != null) {
                        httpResult.onFail(e);
                    }
                }
            }
        }).start();
    }

    public void postByJson(final String url, final String strJson, final HttpResult httpResult) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LogUtils.i("地址：" + url);
                    URL postUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) postUrl
                            .openConnection();
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(DEFAULT_TIMEOUT * 1000);
                    connection.setReadTimeout(DEFAULT_TIMEOUT * 1000);
                    connection.setUseCaches(false);
                    connection.setInstanceFollowRedirects(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.connect();
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
//                    String json = URLEncoder.encode(strJson, "utf-8");
                    LogUtils.i("请求数据:" + strJson);
                    out.write(strJson);
                    out.flush();
                    out.close();
                    int responseCode = connection.getResponseCode();
                    InputStream inputStream = null;
                    if (responseCode == 200) {
                        inputStream = new BufferedInputStream(connection.getInputStream());
                    } else {
                        inputStream = new BufferedInputStream(connection.getErrorStream());
                    }
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    String lines;
                    while ((lines = reader.readLine()) != null) {
                        LogUtils.i("自定义网络返回结果：" + lines);
                        if (httpResult != null) {
                            httpResult.onSuccess(lines);
                        }
                    }
                    reader.close();
                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (httpResult != null) {
                        httpResult.onFail(e);
                    }
                }
            }
        }).start();
    }

    public interface HttpResult {
        void onSuccess(String result);

        void onFail(Throwable e);
    }
}
