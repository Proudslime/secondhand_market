package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private int storeID;
    private String storeName;
    private String password;
    private int gender;
    private String phoneNumber;
    private String auditStatus;
    private float wallet;
    private String storelevel;

    public Store(String storeName,
                 String password,
                 int gender,String phoneNumber,
                 String auditStatus,
                 String storelevel,float wallet) {
        this.storeName = storeName;
        this.password = password;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.auditStatus = auditStatus;
        this.storelevel = storelevel;
        this.wallet = wallet;
    }
}
