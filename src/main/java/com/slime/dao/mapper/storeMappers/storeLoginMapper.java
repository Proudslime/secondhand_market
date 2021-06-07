package com.slime.dao.mapper.storeMappers;

import com.slime.pojo.Store;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface storeLoginMapper {
    int isHavethisStore(String storename);

    int passwordCheck(String storeName, String password);

    Store loginin(String storename, String password);

    void register(Store store);
}
