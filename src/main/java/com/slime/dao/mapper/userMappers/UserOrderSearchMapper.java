package com.slime.dao.mapper.UserMappers;

import com.slime.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.slime.pojo.Dealing;

import java.util.List;

@Mapper
@Repository
public interface UserOrderSearchMapper {
    List<Dealing> getAllUserdealing(int userID);

    int getUserID(String userName);

    int updateDealing(String Address, int userID);

    int addDealing(Dealing dealing);

    int deleteIndexDealing(int dealID, int userID);

    int deleteAllDealing(int userID);

    int getUserDealingCount(int userID);

    Goods getGoodsById(int GoodsID);
}
