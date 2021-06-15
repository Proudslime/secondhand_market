package com.slime.controller;

import com.slime.dao.mapper.AdministratorsMapper;
import com.slime.pojo.*;
import com.slime.pojo.ResultClass.Result;
import com.slime.pojo.ResultClass.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 管理员监控网站，数据库内容
 * @author: Admin
 * @date: 2021年06月12日 15:56
 */
@RestController
public class AdminController {

    final AdministratorsMapper adminMapper;

    @Autowired
    public AdminController(AdministratorsMapper adminMapper) {
        this.adminMapper = adminMapper;
    }
/*
    管理员功能就是统领全局的sql方法，可以单写，我在其他的地方复用了很多
     */

    @PostMapping("/admin")
    public Result<Administrators> loginAdmin(
            @RequestParam("adminName") String adminName,
            @RequestParam("adminPass") String adminPass
    ) {
        if (adminMapper.isHaveThisAdmin(adminName) > 0) {
            if (adminMapper.checkPass(adminName,adminPass) != 0) {
                return ResultResponse.makeOKRsp(adminMapper.loginIn(adminName,adminPass));
            } else {
                return ResultResponse.makeErrRsp("");
            }
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/admin/UserList")
    public Result<List<User>> checkUserList(
            @RequestBody Administrators admin
    ) {
        if (adminMapper.checkPass(admin.getAdminName(),admin.getAdminPass()) != 0) {
            return ResultResponse.makeOKRsp(adminMapper.getAllUser());
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/admin/StoreList")
    public Result<List<Store>> getAllStore(
            @RequestBody Administrators admin
    ) {
        if (adminMapper.checkPass(admin.getAdminName(),admin.getAdminPass()) != 0) {
            return ResultResponse.makeOKRsp(adminMapper.getAllStore());
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/admin/Dealing")
    public Result<List<Dealing>> getAllDealing(
            @RequestBody Administrators admin
    ) {
        if (adminMapper.checkPass(admin.getAdminName(),admin.getAdminPass()) != 0) {
            return ResultResponse.makeOKRsp(adminMapper.getAllDealing());
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/admin/StoreWare")
    public Result<List<Warehouse_store>> getAllWare(
            @RequestBody Administrators admin
    ) {
        if (adminMapper.checkPass(admin.getAdminName(),admin.getAdminPass()) != 0) {
            return ResultResponse.makeOKRsp(adminMapper.getAllWare());
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/admin/UserAddress")
    public Result<List<AddressForUser>> getAllUserAddress(
            @RequestBody Administrators admin
    ) {
        if (adminMapper.checkPass(admin.getAdminName(),admin.getAdminPass()) != 0) {
            return ResultResponse.makeOKRsp(adminMapper.getAllUserAddress());
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/admin/goodList")
    public Result<List<Goods>> getAllGoods(
            @RequestBody Administrators admin
    ) {
        if (adminMapper.checkPass(admin.getAdminName(),admin.getAdminPass()) != 0) {
            return ResultResponse.makeOKRsp(adminMapper.getAllGoods());
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }

    @PostMapping("/admin/Memorial")
    public Result<List<Memorial>> getAllMemorial(
            @RequestBody Administrators admin
    ) {
        if (adminMapper.checkPass(admin.getAdminName(),admin.getAdminPass()) != 0) {
            return ResultResponse.makeOKRsp(adminMapper.getAllMemorial());
        } else {
            return ResultResponse.makeErrRsp("");
        }
    }
}
