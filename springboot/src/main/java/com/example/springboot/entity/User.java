/**
 * 功能：
 * 作者：Pjkang
 * 日期：2024/5/13 11:29
 */

package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data  // 通过该注解，可以省略getter和setter方法和构造方法和builder方法
@TableName("user")  // 通过该注解，可以指定表名
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String role;
    @TableField(exist = false)  // 通过该注解，可以指定该字段不是数据库表中的字段
    private String Token;
}
