/**
 * 功能：
 * 作者：Pjkang
 * 日期：2024/5/13 11:29
 */

package com.example.springboot.entity;

import lombok.*;

@Data  // 通过该注解，可以省略getter和setter方法和构造方法和builder方法

public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String Token;
    private String role;
}
