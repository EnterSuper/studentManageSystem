/**
 * 功能：提供接口返回数据
 * 作者：Pjkang
 * 日期：2024/5/12 15:56
 */

package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springboot.common.AuthAccess;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import com.example.springboot.entity.Takes;
import com.example.springboot.service.TakesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (StrUtil.isBlank(user.getRole())) {
            return Result.error("请选择角色");
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

    @AuthAccess
    @PutMapping("/password")
    public Result password(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPhone())) {
            return Result.error("数据输入不合法");
        }
        userService.resetPassword(user);
        return Result.success();
    }

    @Autowired
    TakesService takesService;
    @GetMapping("/schedule")
    @ResponseBody
    public Result schedule(
            @RequestParam(name = "year") String year,
            @RequestParam(name = "semester") String semester,
            @RequestParam(name = "week") String week,
            @RequestParam(name = "id") Integer id) {
        List<Takes> schedulelist = takesService.list(new QueryWrapper<Takes>().select("time", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"));
        return Result.success(schedulelist);
    }

    @GetMapping("/grade")
    @ResponseBody
    public Result grade(
            @RequestParam(name = "year") String year,
            @RequestParam(name = "semester") String semester,
            @RequestParam(name = "id") Integer id) {
        List<Takes> gradelist = takesService.list(new QueryWrapper<Takes>().select("title", "credits", "grade", "type"));
        return Result.success(gradelist);
    }
}
