package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.MARSHAL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse_ALL {
    private int wareID;
    private String wareType;
    private int MaxStock;
    private int present;
    private int GoodsID;

    public Warehouse_ALL(String wareType,
                         int MaxStock,
                         int present,
                         int goodsID) {
        this.wareType = wareType;
        this.MaxStock = MaxStock;
        this.present = present;
        this.GoodsID =goodsID;
    }
}
