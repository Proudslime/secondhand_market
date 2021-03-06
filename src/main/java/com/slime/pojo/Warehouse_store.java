package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse_store implements Serializable {
    private static final long serialVersionUID = 5085087651208001735L;
    //仓库ID
    private int wareID;
    //最大存货量
    private int MaxStock;
    //现存量
    private int present;
    //商家ID
    private int StoreID;
    //商品ID
    private int GoodsID;
}
