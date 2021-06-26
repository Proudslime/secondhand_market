package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 商家的商品的评价
 * @author: Admin
 * @date: 2021年06月07日 9:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Memorial implements Serializable {
    private static final long serialVersionUID = -4819373841127475221L;
    //评价ID
    private int memorialID;
    //评价标题
    private String title;
    //评价内容
    private String content;
    //评价用户ID
    private int userID;
    //评价的商品ID
    private int goodsID;
}
