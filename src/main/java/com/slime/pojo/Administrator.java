package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
    //管理员ID
    private int id;
    //管理员姓名
    private String admName;
    //管理员密码
    private String admPass;
}
