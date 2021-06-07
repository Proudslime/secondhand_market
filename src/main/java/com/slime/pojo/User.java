package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //用户ID
    private int userID;
    //用户姓名
    private String userName;
    //用户密码
    private String password;
    //用户性别
    private int gender;
    //用户生日
    private Date birth;
    //用户手机号
    private String phoneNumber;
    //用户银行号
    private String bankNumber;
    //用户所在城市
    private String city;
    //用户邮箱
    private String email;
    //用户钱包
    private float wallet;
    //用户积分
    private float integral;
}
