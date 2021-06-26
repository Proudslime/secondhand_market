package com.slime.dao.mapper.UserMappers;

import com.slime.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShopSearchMapper {

    /*
    查询思路，标签优先，然后是内容模糊查询
    具体到数据库上我想的是使用简单的sql语句重复查询
    这样降低sql语句的重复性
    这几个搜索功能就查询片面的信息即可
     */
    List<String> getAllTypeToList();

    List<Goods> gSRbyGoodName(String goodsName);

    List<Goods> gSRbyGoodType(String goodsType);

    List<Goods> gSRbylaunchTime(String searchYear, String searchMonth);

    List<Goods> gSRbyExp(float MaxExp, float MiniExp);

    List<Goods> gSRbycondition(String condition);

    Goods getDetailGoodsInformation(int goodsID);

}
