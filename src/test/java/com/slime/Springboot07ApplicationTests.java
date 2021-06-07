package com.slime;

import com.slime.dao.mapper.storeMappers.storeLoginMapper;
import com.slime.dao.mapper.userMappers.UserShopCartMapper;
import com.slime.dao.mapper.userMappers.userLoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

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
