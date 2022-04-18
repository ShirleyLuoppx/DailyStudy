package com.ppx.dailystudy.bluetooth

import android.bluetooth.BluetoothDevice
import android.os.Build
import androidx.annotation.RequiresApi
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ppx.dailystudy.R

/**
 * Author: luoxia
 * Date: 2020/8/13 15:21
 * Description: 蓝牙适配器
 */
class BlueToothListAdapter(data: MutableList<BluetoothDevice>) :
    BaseQuickAdapter<BluetoothDevice, BaseViewHolder>(
        R.layout.list_device_item, data
    ) {

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun convert(holder: BaseViewHolder, item: BluetoothDevice) {
        holder.setText(R.id.device_name, item.name ?: "未知设备")
        holder.setText(R.id.device_address, item.address)
        when (item.type) {
            BluetoothDevice.DEVICE_TYPE_UNKNOWN -> {
                holder.setText(R.id.device_type, "未知类型")
            }
            BluetoothDevice.DEVICE_TYPE_CLASSIC -> {
                holder.setText(R.id.device_type, "传统蓝牙")
            }
            BluetoothDevice.DEVICE_TYPE_LE -> {
                holder.setText(R.id.device_type, "低功耗蓝牙")
            }
            BluetoothDevice.DEVICE_TYPE_DUAL -> {
                holder.setText(R.id.device_type, "双模蓝牙")
            }
        }

        when (item.bondState) {
            BluetoothDevice.BOND_NONE -> {
                holder.setText(R.id.device_state, "未匹配")
            }
            BluetoothDevice.BOND_BONDING -> {
                holder.setText(R.id.device_state, "绑定中")
            }
            BluetoothDevice.BOND_BONDED -> {
                holder.setText(R.id.device_state, "已绑定")
            }
        }

    }
}