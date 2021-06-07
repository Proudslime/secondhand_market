package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    //购物车ID
    private int cartID;
    //购物车商品ID
    private int GoodsID;
    //购物车该商品数量
    private int amout;
    //购物车的用户ID
    private int userID;
}
