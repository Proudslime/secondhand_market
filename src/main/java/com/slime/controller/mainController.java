package com.slime.controller;

import com.slime.dao.mapper.UserShopCartMapper;
import com.slime.dao.mapper.userLoginMapper;
import com.slime.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class mainController {

    final UserShopCartMapper cartMapper;
    final userLoginMapper userMapper;

    @Autowired
    public mainController(
            UserShopCartMapper cartMapper,
            userLoginMapper userMapper
    ) {
        this.cartMapper = cartMapper;
        this.userMapper = userMapper;
    }
    /*
    全部没有测试，等待集中化测试
     */
    @PostMapping("/main/search/addGood")
    public Result<ShoppingCart> addGoodtoCart(
            Goods goods,
            @RequestParam("userName") String userName,
            @RequestParam("amout") String amout
    ) {
        if (userMapper.isHavethisUser(userName) != 0) {
            int userID = cartMapper.getUserID(userName);
            int index = cartMapper.userCartCount(userID);
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUserID(cartMapper.getUserID(userName));
            shoppingCart.setAmout(Integer.parseInt(amout));
            shoppingCart.setGoodsID(goods.getGoodsId());
            cartMapper.insertGoodsforCart(shoppingCart);
            if (cartMapper.userCartCount(userID) - index == 1) {
                return ResultResponse.makeOKRsp(shoppingCart);
            } else {
                return ResultResponse.makeErrRsp("没有加入购物车，请等待一段时间再试");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

    @PostMapping("/main/shopCart")
    public Result<List<Goods>> checkCart(
            @RequestParam("userName") String userName
    ) {

        if (!userName.equals("")) {
            if (userMapper.isHavethisUser(userName) != 0) {
                List<ShoppingCart> cartslist = cartMapper.getUserShopCart(cartMapper.getUserID(userName));
                List<Goods> goodsList = new ArrayList<>();
                for (ShoppingCart cart: cartslist) {
                    goodsList.add(cartMapper.getDetailGoods(cart.getGoodsID()));
                }
                return ResultResponse.makeOKRsp(goodsList);
            } else {
                return ResultResponse.makeErrRsp("还没登录，请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("还没登录，请先登录");
        }
    }

    @PostMapping("/main/shopCart/update")
    public Result<String> updateCart(
            @RequestParam("CartID") String CartID,
            @RequestParam("amout") String amout,
            @RequestParam("userName") String userName
            ) {

        if (userMapper.isHavethisUser(userName) != 0) {
            int userID = cartMapper.getUserID(userName);
            int index = cartMapper.isHaveThisGoodsInCart(Integer.parseInt(CartID),userID);
            cartMapper.updateCart(
                    Integer.parseInt(CartID), userID,
                    Integer.parseInt(amout));
            if (cartMapper.isHaveThisGoodsInCart(Integer.parseInt(CartID),userID) - index == 0) {
                return ResultResponse.makeOKRsp("修改成功");
            } else {
                return ResultResponse.makeErrRsp("修改失败，请等待一段时间再修改");
            }
        } else {
            return ResultResponse.makeErrRsp("没有该用户,请先登录");
        }
    }

    @PostMapping("/main/shopCart/delete")
    public Result<String> deleteIndexCart(
            @RequestParam("CartID") String CartID,
            @RequestParam("userName") String userName
    ) {

        if (userMapper.isHavethisUser(userName) != 0) {
            int userID = cartMapper.getUserID(userName);
            int ID = Integer.parseInt(CartID);
            int index = cartMapper.isHaveThisGoodsInCart(ID, userID);
            cartMapper.deleteGoodsfromCart(
                    Integer.parseInt(CartID), userID
            );
            if (index - cartMapper.isHaveThisGoodsInCart(ID, userID) == 1) {
                return ResultResponse.makeOKRsp("删除当前商品成功");
            } else {
                return ResultResponse.makeErrRsp("删除当前商品失败，请等待一段时间再删除");
            }
        } else {
            return ResultResponse.makeErrRsp("没有该用户，请先登录");
        }

    }

    @PostMapping("/main/shopCart/deleteAll")
    public Result<String> deleteAllGoodsfromCart(
            @RequestParam("userName") String userName
    ) {
        if (userMapper.isHavethisUser(userName) != 0) {
            int userID = cartMapper.getUserID(userName);
            int index = cartMapper.userCartCount(userID);
            cartMapper.deleteCart(cartMapper.getUserID(userName));
            if (index == 0) {
                return ResultResponse.makeOKRsp("全部删除成功");
            } else {
                return ResultResponse.makeErrRsp( "删除当前全部商品失败，请等待一段时间再实现");
            }
        } else {
            return ResultResponse.makeErrRsp("没有该用户，请先登录");
        }
    }

}
