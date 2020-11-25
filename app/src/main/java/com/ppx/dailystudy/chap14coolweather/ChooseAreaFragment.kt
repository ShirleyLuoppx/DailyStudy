package com.ppx.dailystudy.chap14coolweather

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppx.dailystudy.MyApplication
import com.ppx.dailystudy.R
import com.ppx.dailystudy.chap14coolweather.db.City
import com.ppx.dailystudy.chap14coolweather.db.County
import com.ppx.dailystudy.chap14coolweather.db.Province
import com.ppx.dailystudy.chap14coolweather.util.CommonUtil
import com.ppx.dailystudy.chap14coolweather.util.HttpUtil
import com.ppx.dailystudy.chap14coolweather.util.JsonUtil
import kotlinx.android.synthetic.main.activity_test_finish_function.*
import kotlinx.android.synthetic.main.fragment_choose_area.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.litepal.LitePal
import java.io.IOException
import java.lang.Exception

/**
 * by:LuoXia
 * date:2020/11/22
 * desc:DESC
 */
class ChooseAreaFragment : Fragment() {

    companion object {
        const val LEVEL_PROVINCE = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2

        const val TAG = "ChooseAreaFragment"
    }

    private lateinit var progressDialog: ProgressDialog
    private var provinceList = mutableListOf<Province>()
    private var cityList = mutableListOf<City>()
    private var countyList = mutableListOf<County>()
    private lateinit var selectedProvince: Province
    private lateinit var selectedCity: City
    private var currentLevel: Int = 0
    private lateinit var weatherAdapter: WeatherAdapter
    private var dataList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_area, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRV()
        initEvent()
        queryProvince()
    }

    private fun initEvent() {
        bt_back.setOnClickListener {
            when (currentLevel) {
                LEVEL_COUNTY -> {
                    queryCity()
                }
                LEVEL_CITY -> {
                    queryProvince()
                }
            }
        }
    }

    private fun initRV() {
        weatherAdapter = WeatherAdapter(dataList)
        rv_data.layoutManager = LinearLayoutManager(context)
        rv_data.adapter = weatherAdapter

        weatherAdapter.apply {
            setOnItemClickListener { adapter, view, position ->
                when (currentLevel) {
                    LEVEL_PROVINCE -> {
                        selectedProvince = provinceList[position]
                        queryCity()
                    }
                    LEVEL_CITY -> {
                        selectedCity = cityList[position]
                        queryCounty()
                    }
                }
            }
        }
    }

    /**
     * 查询全国的省，优先从数据库查，没有就去服务器查
     */
    private fun queryProvince() {
        tv_title.text = "中国"
        bt_back.visibility = View.GONE
        provinceList = LitePal.findAll(Province::class.java)
        if (provinceList.size > 0) {
            dataList.clear()
            for (data in provinceList) {
                dataList.add(data.provinceName)
                Log.d(TAG, "queryProvince: 省数据：${data.provinceName}")
            }
            weatherAdapter.notifyDataSetChanged()
            currentLevel = LEVEL_PROVINCE
            Log.d(TAG, "queryProvince: ----${provinceList.size}-----${provinceList[0].provinceName}---${provinceList[1].provinceName}")
        } else {
            Log.d(TAG, "queryProvince: else")
            val address = "http://guolin.tech/api/china"
            queryFromServer(address, "province")
        }
    }

    /**
     * 查询全国的市，优先从数据库查，没有就去服务器查
     */
    private fun queryCity() {
        tv_title.text = selectedProvince.provinceName
        bt_back.visibility = View.VISIBLE

        cityList = LitePal.where("provinceid=?", selectedProvince.provinceId.toString())
            .find(City::class.java)

        if (cityList.size > 0) {
            dataList.clear()
            for (city in cityList) {
                dataList.add(city.cityName)
            }
            weatherAdapter.notifyDataSetChanged()
            currentLevel = LEVEL_CITY
        } else {
            val address = "http://guolin.tech/api/china/${selectedProvince.provinceCode}"
            queryFromServer(address, "city")
        }
    }

    /**
     * 查询全国的县，优先从数据库查，没有就去服务器查
     */
    private fun queryCounty() {
        tv_title.text = selectedCity.cityName
        bt_back.visibility = View.VISIBLE
        countyList =
            LitePal.where("cityid=?", selectedCity.cityId.toString()).find(County::class.java)
        if (countyList.size > 0) {
            dataList.clear()
            for (county in countyList) {
                dataList.add(county.countyName)
            }
            weatherAdapter.notifyDataSetChanged()
            currentLevel = LEVEL_COUNTY
        } else {
            val address =
                "http://guolin.tech/api/china/${selectedProvince.provinceCode}/${selectedCity.cityCode}"
            queryFromServer(address, "county")
        }
    }

    /**
     * 从服务器查询数据
     */
    private fun queryFromServer(address: String, type: String) {
        try {
            HttpUtil.sendOkHttpRequest(address, object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d(TAG, "onFailure: $e")
                    activity?.runOnUiThread {
                        Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT)
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d(TAG, "onResponse: $response")
                    val responseString = response.body?.string()

                    var result = false
                    when (type) {
                        "province" -> {
                            responseString?.let {
                                result = JsonUtil.handleProvinceResponse(it)
                            }
                            Log.d(TAG, "onResponse: $result")
                        }
                        "city" -> {
                            responseString?.let {
                                result =
                                    JsonUtil.handleCityResponse(
                                        it,
                                        selectedProvince.provinceId
                                    )
                            }
                        }
                        "county" -> {
                            responseString?.let {
                                result =
                                    JsonUtil.handleCountyResponse(it, selectedCity.cityId)
                            }
                        }
                    }

                    if (result) {
                        activity?.runOnUiThread {
                            when (type) {
                                "province" -> {
                                    queryProvince()
                                }
                                "city" -> {
                                    queryCity()
                                }
                                "county" -> {
                                    queryCounty()
                                }
                            }
                        }
                    }
                }

            })
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "queryFromServer: ${e.message}")
        }

    }


}