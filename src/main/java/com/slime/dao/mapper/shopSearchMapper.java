package com.slime.dao.mapper;

import com.slime.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface shopSearchMapper {

    /*
    查询思路，标签优先，然后是内容模糊查询
    具体到数据库上我想的是使用简单的sql语句重复查询
    这样降低sql语句的重复性
     */
    List<Goods> getSearchResult();

}
