/**
 * 功能：提供接口返回数据
 * 作者：Pjkang
 * 日期：2024/5/12 15:56
 */

package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.AuthAccess;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController  // 通过该注解，将该类定义为一个控制器
public class WebController {
    @Autowired
    UserService userService;
    @AuthAccess
    @GetMapping (value = "/")   // "/"的意思是根目录，即http://localhost:9090/
    public Result hello(String name) {  // 接口的方法的实现。
        return Result.success("success");
    }

    // 登录
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user) {  // 通过@RequestBody注解，接收json格式的数据。
        if (StrUtil.isBlank(user.getUsername())) {
            return Result.error("用户名不能为空");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            return Result.error("密码不能为空");
        }
        user = userService.login(user);
        return Result.success(user);
    }

    @AuthAccess
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername())) {
            return Result.error("用户名不能为空");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            return Result.error("密码不能为空");
        }
        if (user.getUsername().length() > 10 || user.getUsername().length() < 2) {
            return Result.error("用户名长度必须在2-10之间");
        }
        if (user.getPassword().length() < 4) {
            return Result.error("密码长度必须大于4");
        }
        user = userService.register(user);
        return Result.success(user);
    }
}
