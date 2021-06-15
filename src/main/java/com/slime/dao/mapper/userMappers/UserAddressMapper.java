package com.slime.dao.mapper.UserMappers;

import com.slime.pojo.AddressForUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserAddressMapper {

    List<AddressForUser> getAllAddress(int userID);

    int cancelMainAddress(int addressID, int userID);

    int selectMainAddress(int addressID, int userID);

    int addAddress(AddressForUser address);

    String getMainAddress(int userID);

    int deleteIndexAddress(int addressID, int userID);

    int updateIndexAddressContent(int addressID, int userID, String content);

}
