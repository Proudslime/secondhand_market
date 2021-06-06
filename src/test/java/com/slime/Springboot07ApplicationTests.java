package com.slime;

import com.slime.pojo.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

import com.slime.dao.mapper.*;

@SpringBootTest
class Springboot07ApplicationTests {

    @Autowired
    storeLoginMapper storeLogin;
    @Autowired
    userLoginMapper userLogin;
    @Autowired
    UserShopCartMapper cartMapper;

    @Test
    void contextLoads() throws SQLException {

        System.out.println(cartMapper.userCartCount(3));

    }

}
