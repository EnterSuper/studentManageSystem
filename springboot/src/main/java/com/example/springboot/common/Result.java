package com.example.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  // 通过该注解，可以省略getter和setter方法
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Result {
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";
    public static final String CODE_SYS_ERROR = "500";
    /**
     * 返回码
     * 200：成功
     * 401：认证失败(无权限)
     * 500：系统异常
     */
    private String code;
    private String msg;
    private Object data;  // Object类型，可以接收任意类型的数据
    public static Result success() {  // 静态方法，返回一个Result对象
        return Result.builder().code(CODE_SUCCESS).msg("请求成功").build();
    }
    public static Result success(Object data) {  // json格式的数据，json格式可以key:对象
        return new Result(CODE_SUCCESS, "请求成功", data);
    }
    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR, msg, null);
    }
    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }
    public static Result error() {
        return new Result(CODE_SYS_ERROR, "系统异常", null);
    }
}