package com.slime.dao.mapper;

import com.slime.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdministratorsMapper {

    Administrators loginIn(String adminName, String adminPass);

    int checkPass(String adminName, String adminPass);

    int isHaveThisAdmin(String adminName);

    List<User> getAllUser();

    List<Store> getAllStore();

    List<Dealing> getAllDealing();

    List<Warehouse_store> getAllWare();

    List<AddressForUser> getAllUserAddress();

    List<Goods> getAllGoods();

    List<Memorial> getAllMemorial();

}
