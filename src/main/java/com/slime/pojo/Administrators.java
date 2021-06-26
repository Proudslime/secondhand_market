package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrators implements Serializable {
    private static final long serialVersionUID = -7535784424324223501L;
    //管理员ID
    private int adminID;
    //管理员姓名
    private String adminName;
    //管理员密码
    private String adminPass;
}
