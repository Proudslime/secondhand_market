package com.slime.controller;

import com.slime.dao.mapper.storeMappers.StoreLoginMapper;
import com.slime.dao.mapper.storeMappers.StoreOrderSearchMapper;
import com.slime.pojo.Dealing;
import com.slime.pojo.ResultClass.Result;
import com.slime.pojo.ResultClass.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 商家处理没有交易的订单
 * @author: Admin
 * @date: 2021年06月15日 20:45
 */
@RestController
public class StoreDealingController {

    final StoreLoginMapper loginMapper;
    final StoreOrderSearchMapper dealingMapper;

    @Autowired
    public StoreDealingController(StoreLoginMapper loginMapper, StoreOrderSearchMapper dealingMapper) {
        this.loginMapper = loginMapper;
        this.dealingMapper = dealingMapper;
    }

    @PostMapping("/storeMain/storeDealing")
    public Result<List<Dealing>> getAllDealing(
            @RequestParam("storeName") String storeName,
            @RequestParam("storeID") int storeID
    ) {

        if (storeName != null) {
            if (loginMapper.isHavethisStore(storeName) > 0) {
                return ResultResponse.makeOKRsp(dealingMapper.getAllStoreDealing(storeID));
            } else {
                return ResultResponse.makeErrRsp("");
            }
        } else {
            return ResultResponse.makeErrRsp("");
        }

    }

    @PostMapping("/storeMain/storeDealing/access")
    public Result<Integer> accessDealing(
//        @RequestBody Dealing dealing,
            @RequestParam("dealID") String dealID,
            @RequestParam("price") float price,
            @RequestParam("storeName") String storeName,
            @RequestParam("storeID") int storeID
    ) {
        if (loginMapper.isHavethisStore(storeName) > 0) {
            Dealing dealing = dealingMapper.getIndexOfDealing(Integer.parseInt(dealID));
            dealing.setFinalPrice(price);
            dealing.setIsSuccessfulDeal(1);
            System.out.println(dealing.toString());
            return ResultResponse.makeOKRsp(dealingMapper.accessDealing(dealing));
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

}
