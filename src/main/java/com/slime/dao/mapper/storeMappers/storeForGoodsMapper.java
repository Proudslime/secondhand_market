package com.slime.dao.mapper.storeMappers;

import com.slime.pojo.Goods;
import com.slime.pojo.Warehouse_store;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface storeForGoodsMapper {

    List<Warehouse_store> getGoodsFromWare(int storeID);

    int getIndexGoodsFromWare(int storeID);

    int getPresentFromWare(int wareID, int storeID);

    void updateWare(int wareID, int storeID, int present);

    void deleteIndexWare(int wareID, int storeID);

    void deleteAllWare(int storeID);

    void addGoodsForWare(Warehouse_store warehouse_store);

}
