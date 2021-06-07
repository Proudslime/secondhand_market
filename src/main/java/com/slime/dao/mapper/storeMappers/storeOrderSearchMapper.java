package com.slime.dao.mapper.storeMappers;

import com.slime.pojo.Dealing;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface storeOrderSearchMapper {

    List<Dealing> getAllStoreDealing(int storeID);

    int getStoreID(String storeName);

    int getStoreDealingCount(int storeID);

    String getStoreDealingUserAddress(int storeID, int dealID);

}
