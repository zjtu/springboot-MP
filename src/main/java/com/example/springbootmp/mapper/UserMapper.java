package com.example.springbootmp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootmp.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

     void updateAgeByUsername(@Param("age") Integer age,@Param("username") String username);
}
