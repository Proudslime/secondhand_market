package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.MARSHAL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse_ALL {
    //仓库ID
    private int wareID;
    //该仓库种类
    private String wareType;
    //仓库最大容量
    private int MaxStock;
    //仓库现存量
    private int present;
    //仓库所存商品ID
    private int GoodsID;
}
