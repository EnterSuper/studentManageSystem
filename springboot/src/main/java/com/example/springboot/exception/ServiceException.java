/**
 * 功能：
 * 作者：Pjkang
 * 日期：2024/5/21 21:49
 */

package com.example.springboot.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final String code;

    public ServiceException(String msg) {
        super(msg);
        this.code = "500";
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }

}