package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private int cartID;
    private int GoodsID;
    private int amout;
    private int userID;

    public ShoppingCart(int goodsID,
                        int amout,
                        int userID) {
        this.amout = amout;
        this.GoodsID = goodsID;
        this.userID = userID;
    }
}
