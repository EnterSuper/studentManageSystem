/**
 * 功能：
 * 作者：Pjkang
 * 日期：2024/5/13 11:49
 */

package com.example.springboot.service;

import com.example.springboot.common.Page;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service  // 通过该注解，可以将该类交给Spring容器管理。作用是：将该类交给Spring容器管理，这样在其他类中就可以直接注入该类的对象。
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public void updataUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Integer id) {
        userMapper.delete(id);
    }

    public void batchDeleteUser(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.delete(id);
        }
    }

    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    public List<User> selectByName(String name) {
        return userMapper.selectByName(name);
    }

    public List<User> selectByMore(String username, String name) {
        return userMapper.selectByMore(username, name);
    }

    public List<User> selectByMoreLike(String username, String name) {
        return userMapper.selectByMoreLike(username, name);
    }

    public Page<User> selectByPage(Integer pageNum, Integer pageSize, String username, String name) {
        Integer skipNum = (pageNum - 1) * pageSize;
        Page<User> page = new Page<>();
        List<User> user = userMapper.selectByPage(skipNum, pageSize, username, name);
        page.setTotal(user.size());
        page.setList(user);
        return page;
    }
    // 验证用户账号合法性
    public User login(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());  // username是唯一的，所以只会返回一个用户。
        if (dbUser == null) {
            throw new ServiceException("用户不存在");  // 抛出异常
        }
        if (!Objects.equals(user.getPassword(), dbUser.getPassword())) {  // Objects.equals是比较两个对象是否相等的方法。
            throw new ServiceException("密码错误");
        }
        // 生成token
        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public User register(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser != null) {
            throw new ServiceException("用户已存在");
        }
        user.setName(user.getUsername());
        userMapper.insertUser(user);
        return user;
    }

    public void resetPassword(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户不存在");
        }
        if (!user.getPhone().equals(dbUser.getPhone())) {
            throw new ServiceException("验证错误");
        }
        dbUser.setPassword("123");   // 重置密码
        userMapper.updateUser(dbUser);
    }
}
