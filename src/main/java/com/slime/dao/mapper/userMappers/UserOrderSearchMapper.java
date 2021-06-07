package com.slime.dao.mapper.userMappers;

import com.slime.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.slime.pojo.Dealing;

import java.util.List;

@Mapper
@Repository
public interface UserOrderSearchMapper {
    List<Dealing> getAllUserdealing(int userID);

    int getUserID(String userName);

    void updateDealing(String Address, int userID);

    void addDealing(Dealing dealing);

    void deleteIndexDealing(int dealID, int userID);

    void deleteAllDealing(int userID);

    int getUserDealingCount(int userID);
}
