package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse_store {
    private int wareID;
    private int MaxStock;
    private int present;
    private int StoreID;
    private int GoodsID;

    public Warehouse_store(int MaxStock,
                           int present,
                           int storeID,
                           int goodsID) {
        this.MaxStock = MaxStock;
        this.present = present;
        this.StoreID = storeID;
        this.GoodsID = goodsID;
    }
}
