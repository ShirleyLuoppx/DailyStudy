package com.ppx.dailystudy.chap14coolweather.util

import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.ppx.dailystudy.chap14coolweather.db.City
import com.ppx.dailystudy.chap14coolweather.db.County
import com.ppx.dailystudy.chap14coolweather.db.Province
import org.json.JSONArray
import java.lang.Exception

/**
 * Author: LuoXia
 * Date: 2020/11/7 20:41
 * Description: 解析Json数据工具类
 */
object JsonUtil {

    private val TAG = "JsonUtil"

    /**
     * 处理省数据
     */
    fun handleProvinceResponse(response: String): Boolean {
        if (!TextUtils.isEmpty(response)) {
            try {
                val array = JsonParser().parse(response).asJsonArray
                for (jsonElement in array) {
                    val pro = Gson().fromJson(jsonElement, Province::class.java)
                    pro.save()
                    Log.d(TAG, "handleProvinceResponse: 从服务器查询到的数据${pro.provinceName}--$pro")
                }


//                val jsonArr = Gson().fromJson(response, TypeToken<List<Province>>(){}.type()
//                val allProvinces = JSONArray(response)
//                for (i in 0..allProvinces.length()) {
//                    val jsonObject = allProvinces.getJSONObject(i)
//                    val province = Province()
//                    province.provinceName = jsonObject.getString("name")
//                    province.provinceCode = jsonObject.getInt("id")
//                    province.save()
//                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    /**
     * 处理市级数据
     */
    fun handleCityResponse(response: String, provinceId: Int): Boolean {
        if (!TextUtils.isEmpty(response)) {
            try {
                val allCities = JSONArray(response)
                for (i in 0..allCities.length()) {
                    val jsonObject = allCities.getJSONObject(i)
                    val city = City()
                    city.cityName = jsonObject.getString("name")
                    city.cityCode = jsonObject.getInt("id")
                    city.provinceId = provinceId
                    city.save()
                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    /**
     * 处理县级数据
     */
    fun handleCountyResponse(response: String, cityId: Int): Boolean {

        if (!TextUtils.isEmpty(response)) {
            val allCounties = JSONArray(response)
            try {
                for (i in 0..allCounties.length()) {
                    val jsonObject = allCounties.getJSONObject(i)
                    val county = County()
                    county.cityId = cityId
                    county.countyName = jsonObject.getString("name")
                    county.weatherId = jsonObject.getString("weather_id")
                    county.save()
                }
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

}