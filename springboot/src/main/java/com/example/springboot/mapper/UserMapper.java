package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.User;

public interface UserMapper extends BaseMapper<User> {
    User selectByUsername(String username);  // 通过继承BaseMapper接口，就可以直接使用BaseMapper中的方法了。

}