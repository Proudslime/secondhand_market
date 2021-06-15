package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrators {
    //管理员ID
    private int adminID;
    //管理员姓名
    private String adminName;
    //管理员密码
    private String adminPass;
}
