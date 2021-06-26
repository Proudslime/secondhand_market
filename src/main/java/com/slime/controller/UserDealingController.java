package com.slime.controller;

import com.slime.dao.mapper.UserMappers.UserAddressMapper;
import com.slime.dao.mapper.UserMappers.UserLoginMapper;
import com.slime.dao.mapper.UserMappers.UserOrderSearchMapper;
import com.slime.pojo.Dealing;
import com.slime.pojo.ResultClass.Result;
import com.slime.pojo.ResultClass.ResultResponse;
import com.slime.pojo.ShoppingCart;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /*
    list是特殊的参数类型，所以我打算前端循环调用后端的提交
     */
    @PostMapping("/main/userDealing/add")
    public Result<Integer> addDealing(
            @RequestParam("price") String price,
            @RequestParam("storeID") String storeID,
            @RequestParam("goodsID") String goodsID,
            @RequestParam("amout") String amout,
            @RequestParam("userName") String userName
    ) {
        if (userName != null) {

            if (loginMapper.isHavethisUser(userName) > 0) {

                int userID = dealingMapper.getUserID(userName);
                Dealing dealing = new Dealing();
                dealing.setIsSuccessfulDeal(0);
                dealing.setDealTime(new Date(System.currentTimeMillis()));
                dealing.setAmout(Integer.parseInt(amout));
                dealing.setFinalPrice(Float.parseFloat(price));
                dealing.setAddress(userAddressMapper.getMainAddress(userID));
                dealing.setStoreID(Integer.parseInt(storeID));
                dealing.setUserID(userID);
                dealing.setGoodsID(Integer.parseInt(goodsID));
                if (dealingMapper.addDealing(dealing) > 0) {
                    return ResultResponse.makeOKRsp();
                } else {
                    return ResultResponse.makeErrRsp("订单提交失败，请稍后再添加");
                }

            } else {
                return ResultResponse.makeErrRsp("123");
            }

        } else {
            return ResultResponse.makeErrRsp("234");
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
