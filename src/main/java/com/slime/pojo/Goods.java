package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    private static final long serialVersionUID = 6585007098997122271L;
    //商品ID
    private int goodsID;
    //商品名称
    private String goodsName;
    //商品类型
    private String goodsType;
    //商品成色
    private String condition;
    //商品简述
    private String instrutions;
    //商品折扣
    private float discount;
    //商品状态
    private String status;
    //商品的上架时间
    private Date launchTime;
    //最大期望价格
    private float MaxExp;
    //最小期望价格
    private float MiniExp;
    //最合适的交易价格（系统自动生成，可以按照系统生成的交易，商家也可以自己确定）
    private float price;
    //商家仓库ID
    private int storeWareID;
    //全部仓库ID
    private int storeALLID;
    //商家ID
    private int storeID;
}