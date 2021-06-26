package com.slime.controller;

import com.slime.dao.mapper.storeMappers.StoreForGoodsMapper;
import com.slime.dao.mapper.storeMappers.StoreLoginMapper;
import com.slime.dao.mapper.storeMappers.StoreOrderSearchMapper;
import com.slime.pojo.Goods;
import com.slime.pojo.ResultClass.Result;
import com.slime.pojo.ResultClass.ResultResponse;
import com.slime.pojo.Store;
import com.slime.pojo.User;
import com.slime.pojo.Warehouse_store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@RestController
public class StoreMainController {

    private static final String UPLOAD_PATH = "F:/Java_Idea_Programming/imgFile/";
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
//            @RequestBody Goods goods,
            @RequestParam("goodsName") String goodsName,
            @RequestParam("goodsType") String goodsType,
            @RequestParam("condition") String condition,
            @RequestParam("instrutions") String instrutions,
            @RequestParam("discount") String discount,
            @RequestParam("status") String status,
            @RequestParam("MaxExp") String MaxExp,
            @RequestParam("MiniExp") String MiniExp,
            @RequestParam("storeID") int storeID,
            @RequestParam("storeName") String storeName,
            @RequestParam("present") int present
    ) {
        if (present < 100) {
            if (loginMapper.isHavethisStore(storeName) > 0) {
                String result = "";
                Goods goods = new Goods();
                goods.setStoreID(storeID);
                goods.setMaxExp(Float.parseFloat(MaxExp));
                goods.setInstrutions(instrutions);
                goods.setMiniExp(Float.parseFloat(MiniExp));
                goods.setGoodsName(goodsName);
                goods.setGoodsType(goodsType);
                goods.setLaunchTime(new Date(System.currentTimeMillis()));
                goods.setDiscount(Integer.parseInt(discount));
                goods.setCondition(condition);
                goods.setStatus(status);
                result += "goods:" + goodsMapper.addGoods(goods);

                List<Goods> goodsList = goodsMapper.getStoreGoodsList(storeID);
                int goodsID = 0;
                for (Goods a : goodsList) {
                    if (a.getGoodsName().equals(goodsName)) {
                        goodsID = a.getGoodsID();
                    }
                }
                Warehouse_store ws = new Warehouse_store();
                ws.setStoreID(storeID);
                ws.setMaxStock(100);
                ws.setPresent(present);
                ws.setGoodsID(goodsID);
                result += "--ware:" + goodsMapper.addGoodsForWare(ws);
                return ResultResponse.makeOKRsp(result);
            } else {
                return ResultResponse.makeErrRsp("");
            }
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/upload")
    public Result<MultipartFile> singleFileUpload(
            @RequestParam("photo") MultipartFile file
            ) throws IOException {

//        String fileName = file.getOriginalFilename();
//
//        OutputStreamWriter op = new OutputStreamWriter(new FileOutputStream("./file/" + fileName), StandardCharsets.UTF_8);
//        InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
//        char[] bytes = new char[12];
//
//        while (inputStreamReader.read(bytes) != -1) {
//            op.write(bytes);
//        }
//
//        op.close();
//
//        inputStreamReader.close();
//
//        return ResultResponse.makeOKRsp();

        String fileName = file.getOriginalFilename() + System.currentTimeMillis();
        if (file.isEmpty()) {
            return ResultResponse.makeErrRsp("");
        }
        try {
            File f1 = new File("./file/" + fileName);
            file.transferTo(f1);
        } catch (IOException e) {
            return ResultResponse.makeErrRsp("");
        }
        return ResultResponse.makeOKRsp(file);
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
