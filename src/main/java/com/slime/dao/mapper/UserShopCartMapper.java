package com.slime.dao.mapper;

import com.slime.pojo.Goods;
import com.slime.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserShopCartMapper {

    List<ShoppingCart> getUserShopCart(int userID);

    int getUserID(String userName);

    Goods getDetailGoods(int GoodsID);

    int isHaveThisGoodsInCart(int cartID, int userID);

    int userCartCount(int userID);

    void deleteGoodsfromCart(int cartID, int userID);

    void deleteCart(int userID);

    void updateCart(int cartID, int userID, int count);

    void insertGoodsforCart(ShoppingCart shoppingCart);

}
