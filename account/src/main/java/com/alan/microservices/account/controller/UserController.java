package com.alan.microservices.account.controller;

import com.alan.microservices.account.model.PageReq;
import com.alan.microservices.account.model.SysUser;
import com.alan.microservices.account.model.UserList;
import com.alan.microservices.account.service.UserService;
import com.alan.microservices.commons.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/all")
    @ApiOperation("按页查询用户")
    Result<UserList> queryAll(PageReq pageReq){
        List<SysUser> sysUsers = userService.getAll();
        int total= sysUsers.size();
        int start=pageReq.getPageSize()*(pageReq.getPageNum()-1);
        int stop=pageReq.getPageSize()*pageReq.getPageNum();
        List<SysUser> page=new ArrayList<>(pageReq.getPageSize());
        for (int i = start; i < total && i<stop; i++) {
            page.add(sysUsers.get(i));
        }
        UserList data=new UserList(pageReq);
        data.setTotalSize(total);
        data.setContent(page);
        Result<UserList> result=Result.success(data);
        return result;
    }
    @GetMapping("/{userId}")
    @ApiOperation("根据id查询")

    SysUser queryById(@PathVariable("userId") long userId){
        return userService.getById(userId);
    }

    @GetMapping("")
    @ApiOperation("根据用户名查询")
    SysUser queryByUsername(@RequestParam("username") String username){
        return userService.getByUsername(username);
    }

    @GetMapping("/perm")
    List<String> getPermByUsername(@RequestParam("username") String username){
        return userService.getPermsAndRolesByUserName(username);
    }
    /*
   'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  }
    */

    @GetMapping("/detail")
    @ApiOperation("用户详细")
    Result<Map> detail() {
        Map<String, Object> info = new HashMap();
        info.put("roles", new String[]{"admin"});
        info.put("introduction", "super administrator");
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "alan");
        return Result.success(info);

    }
}
