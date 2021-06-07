package com.slime.dao.mapper;

import com.slime.pojo.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface administartorMapper {

    Administrator loginIn(String adminName, String password);

}
