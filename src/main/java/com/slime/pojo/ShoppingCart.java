package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 4721306229612606127L;
    //购物车ID
    private int cartID;
    //购物车商品ID
    private int GoodsID;
    //购物车该商品数量
    private int amout;
    //购物车的用户ID
    private int userID;
    //商品的商家ID
    private int storeID;
}
