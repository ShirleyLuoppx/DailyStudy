package com.ppx.dailystudy.bluetooth

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.ppx.dailystudy.R


/**
 * Author: luoxia
 * Date: 2020/8/13 9:54
 * Description: DESCRIPTION
 */
class MyAdapter(context: Context?, private var mBluelist: List<BluetoothDevice>) :
    BaseAdapter() {

    fun MyAdapter(
        context: Context?,
        list: List<BluetoothDevice?>?
    ) {
        mBluelist = list as List<BluetoothDevice>
        layoutInflater = LayoutInflater.from(context)
    }

    private var layoutInflater: LayoutInflater
    override fun getCount(): Int {
        return mBluelist.size
    }

    override fun getItem(position: Int): Any {
        return mBluelist[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View? {
        var view: View? = view
        val viewHolder: ViewHolder
        if (view == null) {
            viewHolder = ViewHolder()
            view = layoutInflater.inflate(R.layout.list_device_item, null)
            viewHolder.deviceName = view.findViewById(R.id.device_name)
            viewHolder.deviceAddress = view.findViewById(R.id.device_address)
            viewHolder.deviceType = view.findViewById(R.id.device_type)
            viewHolder.deviceState = view.findViewById(R.id.device_state)
            view.setTag(viewHolder)
        } else {
            viewHolder = view.getTag() as ViewHolder
        }
        //详细参考：http://blog.csdn.net/mirkowu/article/details/53862842
        val blueDevice = mBluelist[position]
        //设备名称
        val deviceName = blueDevice.name
        viewHolder.deviceName!!.text = if (TextUtils.isEmpty(deviceName)) "未知设备" else deviceName
        //设备的蓝牙地（地址为17位，都为大写字母-该项貌似不可能为空）
        val deviceAddress = blueDevice.address
        viewHolder.deviceAddress!!.text = deviceAddress
        //设备的蓝牙设备类型（DEVICE_TYPE_CLASSIC 传统蓝牙 常量值：1, DEVICE_TYPE_LE  低功耗蓝牙 常量值：2
        //DEVICE_TYPE_DUAL 双模蓝牙 常量值：3. DEVICE_TYPE_UNKNOWN：未知 常量值：0）
        val deviceType = blueDevice.type
        if (deviceType == 0) {
            viewHolder.deviceType!!.text = "未知类型"
        } else if (deviceType == 1) {
            viewHolder.deviceType!!.text = "传统蓝牙"
        } else if (deviceType == 2) {
            viewHolder.deviceType!!.text = "低功耗蓝牙"
        } else if (deviceType == 3) {
            viewHolder.deviceType!!.text = "双模蓝牙"
        }
        //设备的状态（BOND_BONDED：已绑定 常量值：12, BOND_BONDING：绑定中 常量值：11, BOND_NONE：未匹配 常量值：10）
        val deviceState = blueDevice.bondState
        if (deviceState == 10) {
            viewHolder.deviceState!!.text = "未匹配"
        } else if (deviceState == 11) {
            viewHolder.deviceState!!.text = "绑定中"
        } else if (deviceState == 12) {
            viewHolder.deviceState!!.text = "已绑定"
        }
        //返回远程设备支持的UUID。此方法从远程设备检索UUID不启动服务。 而是返回服务UUID的本地缓存。
        //如果需要刷新UUID，使用fetchUuidsWithSdp（）方法
        //ParcelUuid[] deviceUuid = blueDevice.getUuids();
        //blueDevice.fetchUuidsWithSdp(); boolean类型
        return view
    }

    private inner class ViewHolder {
        var deviceName: TextView? = null
        var deviceAddress: TextView? = null
        var deviceType: TextView? = null
        var deviceState: TextView? = null
    }

    init {
        layoutInflater = LayoutInflater.from(context)
    }
}
