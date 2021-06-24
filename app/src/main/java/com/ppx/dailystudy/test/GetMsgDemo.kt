package com.ppx.dailystudy.test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log

/**
 * @Author: LuoXia
 * @Date: 2021/6/24 10:11
 * @Description: 获取短信数据
 */
class GetMsgDemo : BroadcastReceiver() {

    private val TAG = "GetMsgDemo"

    override fun onReceive(context: Context?, intent: Intent?) {

        //pdus短信单位pdu
        //解析短信内容


//        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
//        for (Object pdu : pdus) {
//            //封装短信参数的对象
//            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
//            String number = sms.getOriginatingAddress();
//            String body = sms.getMessageBody();
//            //写自己的处理逻辑
//        }

        val pdus = intent?.extras?.get("puds")
        val sms = SmsMessage.createFromPdu(pdus as ByteArray?)
        val number = sms.originatingAddress
        val body = sms.messageBody
        Log.d(TAG, "onReceive: $number---${body}")
    }
}