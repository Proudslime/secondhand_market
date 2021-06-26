package com.slime.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 用户的收货地址
 * @author: Admin
 * @date: 2021年06月10日 18:49
 */
@Data
@NoArgsConstructor
public class AddressForUser implements Serializable {
    private static final long serialVersionUID = 5571028188038152315L;
    private int addressID;
    private String content;
    private String isMainAddress;
    private int userID;
}
