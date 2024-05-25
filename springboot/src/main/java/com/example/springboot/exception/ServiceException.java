/**
 * 功能：
 * 作者：Pjkang
 * 日期：2024/5/21 21:49
 */

package com.example.springboot.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
