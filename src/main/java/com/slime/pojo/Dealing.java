package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dealing {
    private int dealID;
    private Date dealTime;
    private float finalPrice;
    private String Address;
    private int GoodsID;
    private int StoreID;
    private int userID;

    public Dealing(Date dealTime,
                   float finalPrice,
                   String address,
                   int goodsID,
                   int storeID, int userID) {
        this.dealTime = dealTime;
        this.finalPrice = finalPrice;
        this.Address = address;
        this.GoodsID = goodsID;
        this.StoreID = storeID;
        this.userID = userID;
    }
}
