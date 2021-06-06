package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private int goodsId;
    private String goodsName;
    private String condition;
    private String instrutions;
    private float discount;
    private String status;
    private Date launchTime;
    private float MaxExp;
    private float MiniExp;
    private float price;
    private String evaluate;

    public Goods(String goodsName,
                 String condition,
                 String instrutions, float discount,
                 String status,
                 Date launchTime,
                 float maxExp, float miniExp,
                 float price, String evaluate) {
        this.goodsName = goodsName;
        this.condition = condition;
        this.instrutions = instrutions;
        this.discount = discount;
        this.status = status;
        this.launchTime = launchTime;
        this.MaxExp = maxExp;
        this.MiniExp = miniExp;
        this.evaluate = evaluate;
        this.price = price;
    }
}
