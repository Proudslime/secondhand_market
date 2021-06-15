package com.slime.dao.mapper.storeMappers;

import com.slime.pojo.Goods;
import com.slime.pojo.Warehouse_store;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StoreForGoodsMapper {

    /*
    商家仓库操作，添加和删除
     */
    //获取仓库中货品属性（数量状态）
    List<Warehouse_store> getGoodsFromWare(int storeID);
    //获取仓库货品种类数量
    int getIndexGoodsFromWare(int storeID);
    //获取指定的仓库ID
    int getWareID(int storeID, int goodsID);
    //获取当前商品的现存数量
    int getPresentFromWare(int wareID, int storeID);
    //更新商品的数量（用于添加）
    int updateWare(int wareID, int storeID, int present);
    //删除商家自己库中指定的货物
    int deleteIndexWare(int wareID, int storeID);
    //仓库清除
    int deleteAllWare(int storeID);
    //添加商品到仓库中（将商品表中的存入到仓库中）
    int addGoodsForWare(Warehouse_store warehouse_store);

    /*
    货品操作，添加自己的货物
     */
    //获取自己的商品信息
    List<Goods> getStoreGoodsList(int storeID);
    //获取自己商品的种类数量
    int getGoodsCount(int storeID);
    //更新商品信息
    int updateGoods(Goods goods);
    //删除商家指定的商品
    int deleteIndexGoods(int goodsID, int storeID);
    //删除商家全部商品（清库）
    int deleteAllGoods(int storeID);
    //添加商品到商品表中
    int addGoods(Goods goods);
}
