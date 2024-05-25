/**
 * 功能：
 * 作者：Pjkang
 * 日期：2024/5/21 21:47
 */

package com.example.springboot.exception;

import com.example.springboot.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServiceException.class)  // 通过该注解，可以将一个方法定义为异常处理方法。就是当Controller中抛出异常时，会被这个方法捕获。
    @ResponseBody  // 通过该注解，将方法的返回值定义为json格式。
    public Result serviceException(ServiceException e) {
        return Result.error("500", e.getMessage());
    }
}
