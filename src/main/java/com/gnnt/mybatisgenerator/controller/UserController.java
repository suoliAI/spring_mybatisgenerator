package com.gnnt.mybatisgenerator.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.gnnt.mybatisgenerator.model.User;
import com.gnnt.mybatisgenerator.response.BaseResponse;
import com.gnnt.mybatisgenerator.response.DataResponse;
import com.gnnt.mybatisgenerator.service.IUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户管理", description = "用户管理")
@Slf4j
@RestController
@RequestMapping("userManager")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "根据id获取用户",notes = "id必须是数字")
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户ID",required = true, paramType = "path")})
    @ApiResponses({@ApiResponse(code=400,message="id为空")})
    @GetMapping("/user/{id}")
    public DataResponse get(@PathVariable("id")Integer id){
        return DataResponse.buildSuccess(userService.selectUserById(id));
    }
    @ApiOperation(value = "新增用户信息",httpMethod = "POST")
    @ApiImplicitParam(name="user", value="用户实体", required = true, dataType = "User")
    @ApiResponse(code=500,message = "新增用户失败")
    @PostMapping("/user/put")
    public BaseResponse put(User user){
        log.info("新增用户信息 {}",user.toString());
        userService.add(user);
        return BaseResponse.buildSuccess();
    }
    @ApiOperation(value = "删除用户信息")
    @ApiImplicitParam(name="id", value="用户ID", required = true, dataType = "String")
    @DeleteMapping("/user/{id}")
    public BaseResponse delete(@PathVariable("id") String id){
        return BaseResponse.buildSuccess();
    }
    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParam(name="user", value="用户实体", required = true, dataType = "User")
    @PutMapping("/user/put")
    public BaseResponse update(User user){
        log.info("更新用户信息 {}",user.toString());
        int update = userService.update(user);
        return BaseResponse.buildSuccess();
    }
}
