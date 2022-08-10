package com.gclibrary.http.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by Administrator on 2019/2/21 0021.
 */

public class Result<T> {
    public int code;
    public String massage;
    public String message;
    public T data;
    public List<T> list;
    public T info;
    public T zfb_info;
    public T wx_info;
    public T yhk_info;
    public List<T> calendar_list;
    public String token;
    public String phone;
    public String u_sn;//普通用户sn
    public String uag_sn;//代理商sn
    public int u_type;//类型 1普通 2代理商 根据type分配sn
    public int is_cation;//是否实名 0:审核中 1:已实名 2:未实名
    public int is_pay_key;//是否设置支付密码 0没有 1有
    public int is_address;//是否有授权 0没有 1有
    public String deal_sn;
    public String order_sn;
    public String ware_sn;
    public int ag_tame_count;
    public double ag_earnings;
    public String pagination;
    public String link;
    public int type;
    public String name;
    public String value;
    public String request_id;
    public String certify_id;
    public String appid;
    public String partnerid;
    public String prepayid;
    public String noncestr;
    public String timestamp;
    public String sign;
    public String out_trade_no;
    @JSONField(name="package")
    public String packageX;
}
