package com.example.springbootmp.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootmp.entity.User;
import com.example.springbootmp.mapper.UserMapper;
import com.example.springbootmp.service.IUserService;
import com.example.springbootmp.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@Api(value = "接口测试",tags = "用户相关接口")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/page")
    @ApiOperation(value = "分页查询",notes = "分页查询数据")
    public Result page(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize){
        LambdaQueryWrapper<User>  lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.gt(User::getAge,15);
        Page<User> page = new Page<>(pageNum,pageSize);
        IPage<User> userPage = userMapper.selectPage(page, lambdaQueryWrapper);
        System.out.println(userPage.getPages());
        System.out.println(userPage.getRecords());
        System.out.println(userPage.getTotal());
        return Result.success(page);
    }
}
