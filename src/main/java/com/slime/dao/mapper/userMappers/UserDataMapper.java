package com.slime.dao.mapper.UserMappers;

import com.slime.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDataMapper {

    User getUserData(String userName);

    int updateUserData(User user);

    int recharge(User user);

}
