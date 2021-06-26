package com.slime;

import com.slime.dao.mapper.AdministratorsMapper;
import com.slime.dao.mapper.UserMappers.*;
import com.slime.dao.mapper.storeMappers.StoreForGoodsMapper;
import com.slime.dao.mapper.storeMappers.StoreLoginMapper;
import com.slime.dao.mapper.storeMappers.StoreOrderSearchMapper;
import com.slime.pojo.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

@SpringBootTest
class Springboot07ApplicationTests {

    final UserLoginMapper userLogin;
    final ShopSearchMapper shopSearch;
    final UserOrderSearchMapper userOrderSearch;
    final UserShopCartMapper userShopCart;
    final StoreForGoodsMapper storeForGoods;
    final StoreLoginMapper storeLogin;
    final StoreOrderSearchMapper storeOrderSearch;
    final AdministratorsMapper administrator;
    final UserAddressMapper userAddress;

    @Autowired
    public Springboot07ApplicationTests(
            UserLoginMapper userLogin,
            ShopSearchMapper shopSearch,
            UserOrderSearchMapper userOrderSearch,
            UserShopCartMapper userShopCart,
            StoreForGoodsMapper storeForGoods,
            StoreLoginMapper storeLogin,
            StoreOrderSearchMapper storeOrderSearch,
            AdministratorsMapper administrator,
            UserAddressMapper userAddress) {
        this.userLogin = userLogin;
        this.shopSearch = shopSearch;
        this.userOrderSearch = userOrderSearch;
        this.userShopCart = userShopCart;
        this.storeForGoods = storeForGoods;
        this.storeLogin = storeLogin;
        this.storeOrderSearch = storeOrderSearch;
        this.administrator = administrator;
        this.userAddress = userAddress;
    }

    @Test
    void contextLoads() {

        Store s1 = storeLogin.loginin("test1","111");

        User u1 = userLogin.loginin("test11","111");

        Dealing dealing = storeOrderSearch.getIndexOfDealing(5);
        System.out.println(dealing.toString());
        dealing.setIsSuccessfulDeal(1);
        dealing.setFinalPrice(400);
        storeOrderSearch.accessDealing(dealing);

    }

}
