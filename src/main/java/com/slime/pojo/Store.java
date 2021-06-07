package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    //商家ID
    private int storeID;
    //商家姓名
    private String storeName;
    //商家密码
    private String password;
    //商家性别
    private int gender;
    //商家手机号
    private String phoneNumber;
    //商家银行号
    private String bankNumber;
    //商家审核状态
    private String auditStatus;
    //商家钱包
    private float wallet;
    //商家店铺等级
    private String storelevel;
}
