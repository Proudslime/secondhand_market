package com.slime.controller;

import com.slime.dao.mapper.UserMappers.UserAddressMapper;
import com.slime.dao.mapper.UserMappers.UserLoginMapper;
import com.slime.dao.mapper.UserMappers.UserOrderSearchMapper;
import com.slime.pojo.Dealing;
import com.slime.pojo.ResultClass.Result;
import com.slime.pojo.ResultClass.ResultResponse;
import com.slime.pojo.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户的商品提交，商品订单查询界面所需
 * @author: Admin
 * @date: 2021年06月15日 14:53
 */
@RestController
public class UserDealingController {

    final UserOrderSearchMapper dealingMapper;
    final UserLoginMapper loginMapper;
    final UserAddressMapper userAddressMapper;

    @Autowired
    public UserDealingController(
            UserOrderSearchMapper dealingMapper,
            UserLoginMapper loginMapper,
            UserAddressMapper userAddressMapper
    ) {
        this.dealingMapper = dealingMapper;
        this.loginMapper = loginMapper;
        this.userAddressMapper = userAddressMapper;
    }

    @PostMapping("/main/userDealing")
    public Result<List<Dealing>> getAllDealing(
        @RequestParam("userName") String userName
    ) {
        if (userName != null) {
            if (loginMapper.isHavethisUser(userName) > 0) {
                int userID = dealingMapper.getUserID(userName);
                return ResultResponse.makeOKRsp(dealingMapper.getAllUserdealing(userID));
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

    @PostMapping("/main/userDealing/edit")
    public Result<Integer> updateDealing(
        @RequestParam("dealing") Dealing dealing,
        @RequestParam("userName") String userName
    ) {

        if (userName != null) {
            if (loginMapper.isHavethisUser(userName) > 0) {
                int userID = dealingMapper.getUserID(userName);
                return ResultResponse.makeOKRsp(dealingMapper.updateDealing(dealing.getAddress(),userID));
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }

    }

    @PostMapping("/main/userDealing/add")
    public Result<Integer> addDealing(
            @RequestParam("cartList") List<ShoppingCart> cartList,
            @RequestParam("userName") String userName
    ) {
        if (userName != null) {

            if (loginMapper.isHavethisUser(userName) > 0) {

                List<Dealing> dealingList = new ArrayList<>();
                int userID = dealingMapper.getUserID(userName);
                int count = 0;
                for (ShoppingCart shoppingCart: cartList) {
                    Dealing dealing = new Dealing();
                    dealing.setIsSuccessfulDeal(0);
                    dealing.setDealTime(new Date(System.currentTimeMillis()));
                    dealing.setAmout(shoppingCart.getAmout());
                    dealing.setFinalPrice(dealingMapper.getGoodsById(shoppingCart.getGoodsID()).getPrice());
                    dealing.setAddress(userAddressMapper.getMainAddress(userID));
                    dealing.setStoreID(shoppingCart.getStoreID());
                    dealing.setUserID(userID);
                    dealing.setGoodsID(shoppingCart.getGoodsID());
                    dealingList.add(dealing);
                    count += dealingMapper.addDealing(dealing);
                }

                if (!dealingList.isEmpty()) {
                    return ResultResponse.makeOKRsp(count);
                } else {
                    return ResultResponse.makeErrRsp("订单提交失败，请稍后再添加");
                }

            } else {
                return ResultResponse.makeErrRsp("");
            }

        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/main/userDealing/delete")
    public Result<Integer> deleteIndexDealing(
            @RequestParam("dealID") int dealID,
            @RequestParam("userName") String userName
    ) {
        if (userName != null) {

            if (loginMapper.isHavethisUser(userName) > 0) {

                return ResultResponse.makeOKRsp(dealingMapper.deleteIndexDealing(dealID,dealingMapper.getUserID(userName)));

            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }

        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

}
