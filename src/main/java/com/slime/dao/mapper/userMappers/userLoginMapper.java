package com.slime.dao.mapper.UserMappers;

import com.slime.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserLoginMapper {

    Integer isHavethisUser(String userName);

    Integer passwordCheck(String userName, String password);

    User loginin(String userName, String password);

    void register(User user);

}
