package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dealing {
    //订单ID
    private int dealID;
    //交易是否完成判断
    private int isSuccessfulDeal;
    //订单完成时间
    private Date dealTime;
    //交易物品数量
    private int amout;
    //最终交易价格
    private float finalPrice;
    //收获地址
    private String Address;
    //货物ID
    private int GoodsID;
    //商家ID
    private int StoreID;
    //用户ID
    private int userID;
}
