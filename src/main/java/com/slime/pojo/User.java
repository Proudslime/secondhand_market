package com.slime.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userID;
    private String userName;
    private String password;
    private int gender;
    private Date birth;
    private String phoneNumber;
    private String bankNumber;
    private String city;
    private String email;
    private float wallet;
    private float integral;

    public User(String userName,
                String password,
                int gender,Date birth,
                String phoneNumber,
                String bankNumber,
                String city,String email,
                float wallet,float integral) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.bankNumber = bankNumber;
        this.city = city;
        this.wallet = wallet;
        this.integral = integral;
    }
}
