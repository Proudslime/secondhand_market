package com.slime.controller;

import com.slime.dao.mapper.storeMappers.StoreForGoodsMapper;
import com.slime.dao.mapper.storeMappers.StoreLoginMapper;
import com.slime.dao.mapper.storeMappers.StoreOrderSearchMapper;
import com.slime.pojo.Goods;
import com.slime.pojo.ResultClass.Result;
import com.slime.pojo.ResultClass.ResultResponse;
import com.slime.pojo.Warehouse_store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreMainController {

    final StoreLoginMapper loginMapper;
    final StoreForGoodsMapper goodsMapper;

    @Autowired
    public StoreMainController(
            StoreLoginMapper loginMapper,
            StoreForGoodsMapper goodsMapper
    ) {
        this.loginMapper = loginMapper;
        this.goodsMapper = goodsMapper;
    }

    @PostMapping("/storeMain/goods")
    public Result<List<Goods>> getGoodsData(
            @RequestParam("storeName") String storeName,
            @RequestParam("storeID") int storeID
    ) {
        if (loginMapper.isHavethisStore(storeName) > 0) {
            return ResultResponse.makeOKRsp(goodsMapper.getStoreGoodsList(storeID));
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/storeMain/ware")
    public Result<List<Warehouse_store>> getWareData(
            @RequestParam("storeName") String storeName,
            @RequestParam("storeID") int storeID
    ) {
        if (loginMapper.isHavethisStore(storeName) > 0) {
            return ResultResponse.makeOKRsp(goodsMapper.getGoodsFromWare(storeID));
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/storeMain/ware/present")
    public Result<Integer> getPresentFromWare(
            @RequestParam("storeID") int storeID,
            @RequestParam("storeName") String storeName,
            @RequestParam("wareID") int wareID
    ) {
        if (loginMapper.isHavethisStore(storeName) > 0) {
            return ResultResponse.makeOKRsp(goodsMapper.getPresentFromWare(wareID,storeID));
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/storeMain/addGood")
    public Result<String> addGood(
            @RequestBody Goods goods,
            @RequestParam("storeID") int storeID,
            @RequestParam("storeName") String storeName,
            @RequestParam("present") int present
    ) {
        if (present < 100) {
            if (loginMapper.isHavethisStore(storeName) > 0) {
                String result = "";
                result += "goods:" + goodsMapper.addGoods(goods);
                Warehouse_store ws = new Warehouse_store();
                ws.setStoreID(storeID);
                ws.setMaxStock(100);
                ws.setPresent(present);
                ws.setGoodsID(goods.getGoodsID());
                result += "--ware:" + goodsMapper.addGoodsForWare(ws);
                return ResultResponse.makeOKRsp(result);
            } else {
                return ResultResponse.makeErrRsp("");
            }
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/storeMain/goods/update")
    public Result<Integer> updateGood(
            @RequestBody Goods goods,
            @RequestParam("storeID") int storeID,
            @RequestParam("storeName") String storeName
    ) {
        if (loginMapper.isHavethisStore(storeName) > 0) {
            return ResultResponse.makeOKRsp(goodsMapper.updateGoods(goods));
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/storeMain/ware/addPresent")
    public Result<Integer> addPresent(
            @RequestParam("wareID") int wareID,
            @RequestParam("present") int present,
            @RequestParam("storeID") int storeID,
            @RequestParam("storeName") String storeName
    ) {
        if (loginMapper.isHavethisStore(storeName) > 0) {
            return ResultResponse.makeOKRsp(goodsMapper.updateWare(wareID, storeID, present));
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/storeMain/goods/delete")
    public Result<String> deleteIndexGoods(
            @RequestBody Goods goods,
            @RequestParam("storeName") String storeName,
            @RequestParam("storeID") int storeID
    ) {
        if (loginMapper.isHavethisStore(storeName) > 0) {

            String result = "";
            result += "ware:" + goodsMapper.deleteIndexWare(goodsMapper.getWareID(storeID,goods.getGoodsID()),storeID);
            result += "goods:" + goodsMapper.deleteIndexGoods(goods.getGoodsID(),storeID);
            return ResultResponse.makeOKRsp(result);

        } else {
            return ResultResponse.makeErrRsp("");
        }
    }
}
