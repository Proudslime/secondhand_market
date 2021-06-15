package com.slime.controller;

import com.slime.dao.mapper.UserMappers.*;
import com.slime.pojo.*;
import com.slime.pojo.ResultClass.Result;
import com.slime.pojo.ResultClass.ResultResponse;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserMainController {

    final UserShopCartMapper cartMapper;
    final UserLoginMapper userMapper;
    final UserOrderSearchMapper dealingSearchMapper;
    final ShopSearchMapper shopSearchMapper;
    final UserAddressMapper userAddressMapper;
    final UserDataMapper userDataMapper;

    @Autowired
    public UserMainController(
            UserShopCartMapper cartMapper,
            UserLoginMapper userMapper,
            UserOrderSearchMapper dealingSearchMapper,
            ShopSearchMapper shopSearchMapper,
            UserAddressMapper userAddressMapper,
            UserDataMapper userDataMapper
    ) {
        this.cartMapper = cartMapper;
        this.userMapper = userMapper;
        this.dealingSearchMapper = dealingSearchMapper;
        this.shopSearchMapper = shopSearchMapper;
        this.userAddressMapper = userAddressMapper;
        this.userDataMapper = userDataMapper;
    }

    @PostMapping("/main/search")
    public Result<Goods> searchGoodsList(
            @RequestParam("searchContent") String searchContent
    ) {
        /*
        大致思路为分割前端传入的搜索条件
        我想的是创建一个类来实现搜索的分类，以便于查询
        要不要设计线程控制有待考虑
         */
        String[] search = searchContent.split(";");

        return null;
    }

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
            shoppingCart.setGoodsID(goods.getGoodsID());
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

    /*
    用户信息需要测试
     */
    @PostMapping("/main/userData")
    public Result<User> checkUserData(
        @RequestParam("userName") String userName
    ) {

        if (userName != null) {
            if (userMapper.isHavethisUser(userName) > 0) {
                return ResultResponse.makeOKRsp(userDataMapper.getUserData(userName));
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

    @PostMapping("/main/userData/edit")
    public Result<Integer> updateUserData(
           @RequestBody User user
    ) {
        if (user.getUserName() != null) {
            if (userMapper.isHavethisUser(user.getUserName()) > 0) {
                return ResultResponse.makeOKRsp(userDataMapper.updateUserData(user));
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

    @PostMapping("/main/userData/recharge")
    public Result<Integer> reCharge(
            @RequestBody User user,
            @RequestParam("money") int money
    ) {
        if (user.getUserName() != null) {
            if (userMapper.isHavethisUser(user.getUserName()) > 0) {
                if (money > 0) {
                    user.setWallet(user.getWallet() + money);
                } else {
                    return ResultResponse.makeErrRsp("充值数额有误");
                }
                return ResultResponse.makeOKRsp(userDataMapper.recharge(user));
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

    @PostMapping("/main/userAddress")
    public Result<List<AddressForUser>> checkUserAddress(
            @RequestParam("userName") String userName
    ) {

        if (userName != null) {
            if (userMapper.isHavethisUser(userName) > 0) {
                List<AddressForUser> addressList = userAddressMapper.getAllAddress(userDataMapper.getUserData(userName).getUserID());
                return ResultResponse.makeOKRsp(addressList);
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }

    }

    @PostMapping("/main/userAddress/add")
    public Result<Integer> addUserAddress(
            @RequestParam("userName") String userName,
            @RequestParam("address") String address,
            @RequestParam("addressList") List<AddressForUser> addressList
    ){
        if (userName != null) {
            if (userMapper.isHavethisUser(userName) > 0) {
                int userID = userDataMapper.getUserData(userName).getUserID();

                for (AddressForUser a1 : addressList) {
                    if (a1.getContent().equals(address)) {
                        return ResultResponse.makeErrRsp("已经有改地址，请不要重复添加");
                    }
                }

                AddressForUser addressForUser = new AddressForUser();
                addressForUser.setContent(address);
                addressForUser.setUserID(userID);
                addressForUser.setIsMainAddress("0");

                return ResultResponse.makeOKRsp(userAddressMapper.addAddress(addressForUser));
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

    @PostMapping("/main/userAddress/delete")
    public Result<Integer> deleteIndexAddress(
            @RequestParam("addressID") int addressID,
            @RequestParam("userName") String userName
    ) {
        if (userName != null) {
            if (userMapper.isHavethisUser(userName) > 0) {
                return ResultResponse.makeOKRsp(userAddressMapper.deleteIndexAddress(addressID,userDataMapper.getUserData(userName).getUserID()));
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

    @PostMapping("/main/userAddress/update")
    public Result<Integer> updateIndexAddress(
            @RequestParam("userName") String userName,
            @RequestParam("addressID") int addressID,
            @RequestParam("content") String content
    ) {
        if (userName != null) {
            if (userMapper.isHavethisUser(userName) > 0) {
                int userID = userDataMapper.getUserData(userName).getUserID();
                return ResultResponse.makeOKRsp(userAddressMapper.updateIndexAddressContent(addressID,userID,content));
            } else {
                return ResultResponse.makeErrRsp("请先登录");
            }
        } else {
            return ResultResponse.makeErrRsp("请先登录");
        }
    }

    @PostMapping("/main/userAddress/selectMainAddress")
    public Result<String> selectMainAddress(
          @RequestParam("userName") String userName,
          @RequestParam("addressID") int addressID,
          @RequestParam("AddressList") List<AddressForUser> AddressList
    ) {
        if (userMapper.isHavethisUser(userName) > 0) {
            int userID = dealingSearchMapper.getUserID(userName);
            int count = 0;
            String result = "";
            for (AddressForUser a : AddressList) {
                count += userAddressMapper.cancelMainAddress(a.getAddressID(),userID);
            }
            result += "main:" + userAddressMapper.selectMainAddress(addressID,userID);
            result += "--notMain:" + count;
            return ResultResponse.makeOKRsp(result);
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }
}
