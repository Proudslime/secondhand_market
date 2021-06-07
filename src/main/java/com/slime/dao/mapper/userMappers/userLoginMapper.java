package com.slime.dao.mapper.userMappers;

import com.slime.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface userLoginMapper {

    Integer isHavethisUser(String userName);

    Integer passwordCheck(String userName, String password);

    User loginin(String userName, String password);

    void register(User user);

}
