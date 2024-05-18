/**
 * 功能：
 * 作者：Pjkang
 * 日期：2024/5/13 11:50
 */

package com.example.springboot.controller;

import com.example.springboot.common.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")  // 表示这个类是专门用来处理/user路径的请求（给user用的）
public class UserController {


    @Autowired  // 通过该注解，将UserService的对象注入到UserController中。就可以当成成员变量使用了。而不用通过构造函数new一个对象。
    UserService userService;

    /**
     * 添加用户
     */
    @PostMapping(value = "/add")
    public Result add(@RequestBody User user) {  // 通过@RequestBody注解，接收json格式的数据。
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("插入数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 修改用户信息
     */
    @PutMapping(value = "/update")
    public Result update(@RequestBody User user) {  // 通过@RequestBody注解，接收json格式的数据。
        try {
            userService.updataUser(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("更改数据失败");
            } else {
                return Result.error("系统错误");

            }
        }
        return Result.success();
    }

    /**
     * 删除用户信息
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result delete(@PathVariable Integer id) {  // 通过@PathVariable注解，接收路径中的参数。
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("删除数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 批量删除用户信息
     */
    @DeleteMapping(value = "/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {  // json格式的list: [1,2,3]
        try {
            userService.batchDeleteUser(ids);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("删除数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 查询用户信息
     */
    @GetMapping(value = "/selectAll")
    public Result selectAll() {  // 通过@RequestBody注解，接收json格式的数据。
        List<User> users;
        try {
            users = userService.selectAll();  // 查询到的消息要暂存起来
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("查询数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success(users);  // 返回查询到的数据
    }

    /**
     * 通过ID用户信息
     */
    @GetMapping(value = "/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {  // 接收url格式的数据。
        User user;
        try {
            user = userService.selectById(id);  // 查询到的消息要暂存起来
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("查询数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success(user);  // 返回查询到的数据
    }

    /**
     * 根据 name查询用户信息
     */
    @GetMapping(value = "/selectByName/{name}")
    public Result selectByName(@PathVariable String name) {
        List<User> users;
        try {
            users = userService.selectByName(name);  // 查询到的消息要暂存起来
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("查询数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success(users);  // 返回查询到的数据
    }

    /**
     * 根据多种条件查询用户信息
     */
    @GetMapping(value = "/selectByMore")  // @RequestParam注解，接收url格式的数据就是带问号 ?的那种：/selectByMore?username=xxx&name=xxx
    public Result selectByMore(@RequestParam String username, @RequestParam String name) {  // 通过@RequestParam注解，接收url格式的数据。与@PathVariable不同的是，@RequestParam接收的是key-value格式的数据。
        List<User> users;
        try {
            users = userService.selectByMore(username, name);  // 查询到的消息要暂存起来
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("查询数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success(users);  // 返回查询到的数据
    }

    /**
     * 根据多种条件模糊查询用户信息
     */
    @GetMapping(value = "/selectByMoreLike")
    public Result selectByMoreLike(@RequestParam String username, @RequestParam String name) {
        List<User> users;
        try {
            users = userService.selectByMoreLike(username, name);  // 查询到的消息要暂存起来
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("查询数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success(users);  // 返回查询到的数据
    }

    /**
     * 多条件分页查询
     */
    @GetMapping(value = "/selectByPage")
    public Result selectByPage(@RequestParam String username, @RequestParam String name,
                               @RequestParam Integer pageNum, @RequestParam Integer pageSize) {  // pageNum和pageSize分别是当前页和每页显示的数据条数。
        Page<User> page;  // User的分页
        try {
            page = userService.selectByPage(pageNum, pageSize, username, name);  // 查询到的消息要暂存起来
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {  // 如果是SQLException异常，就返回插入数据错误。instanceof是判断一个对象是否是另一个对象的实例。
                return Result.error("查询数据失败");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success(page);  // 返回查询到的数据
    }
    // 查询教这个学生的instructor
}









