package com.slime.controller;

import com.slime.dao.mapper.*;
import com.slime.pojo.Result;
import com.slime.pojo.ResultResponse;
import com.slime.pojo.Store;
import com.slime.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@RestController
public class LoginController {

    final userLoginMapper userLogin;
    final storeLoginMapper storeLogin;

    @Autowired
    public LoginController(
            userLoginMapper userLogin,
            storeLoginMapper storeLogin) {
        this.userLogin = userLogin;
        this.storeLogin = storeLogin;
    }

    @PostMapping("/userLogin")
    public Result<User> userLoginIn(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password
    ) {

        if (userLogin.isHavethisUser(userName) != 0) {
            if (userLogin.passwordCheck(userName,password) != 0) {
                return ResultResponse.makeOKRsp(userLogin.loginin(userName, password));
            } else {
                return ResultResponse.makeErrRsp("密码输入错误");
            }
        } else {
            return ResultResponse.makeErrRsp("没有该用户");
        }
    }

    @PostMapping("/storeLogin")
    public Result<Store> storeLoginIn(
            @RequestParam("storeName") String storeName,
            @RequestParam("password") String password
    ) {

        if (storeLogin.isHavethisStore(storeName) != 0) {
            if (storeLogin.passwordCheck(storeName,password) != 0) {
                return ResultResponse.makeOKRsp(storeLogin.loginin(storeName, password));
            } else {
                return ResultResponse.makeErrRsp("密码输入错误");
            }
        } else {
            return ResultResponse.makeErrRsp("用户名不存在");
        }
    }

    @PostMapping("/userRegister")
    public Result<User> userRegister(
            @RequestParam("userName") String userName,
            @RequestParam("password") String password,
            @RequestParam("gender") int gender,
            @RequestParam("birth") Date birth,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "bankNumber", required = false) String bankNumber,
            @RequestParam(value = "city",required = false) String city,
            @RequestParam(value = "email",required = false) String email
            ) {

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setGender(gender);
        user.setBirth(birth);
        user.setPhoneNumber(phoneNumber);
        user.setBankNumber(bankNumber);
        user.setCity(city);
        user.setEmail(email);

        if (userLogin.isHavethisUser(userName) == 0) {
            userLogin.register(user);
            return ResultResponse.makeOKRsp(user);
        } else {
            return ResultResponse.makeErrRsp("注册失败，已有该用户");
        }
    }

    @PostMapping("/storeRegister")
    public Result<Store> storeRegister(
            Store store
    ) {

        if (storeLogin.isHavethisStore(store.getStoreName()) == 0) {
            storeLogin.register(store);
            return ResultResponse.makeOKRsp(store);
        } else {
            return ResultResponse.makeErrRsp("注册失败，已有该商家");
        }
    }

}
