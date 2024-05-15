/**
 * 功能：提供接口返回数据
 * 作者：Pjkang
 * 日期：2024/5/12 15:56
 */

package com.example.springboot.controller;

import com.example.springboot.common.Result;
import org.springframework.web.bind.annotation.*;


@RestController  // 通过该注解，将该类定义为一个控制器
@RequestMapping(value = "/web")  // 通过该注解，定义类的请求路径
public class WebController {


    @RequestMapping (value = "/hello", method = RequestMethod.GET) // 提供接口，路径为"/hello"，"/"路径的HTTP请求将会被映射到hello方法进行处理，就是接口的路径，默认是GET请求和“/”路径
    public Result hello(String name) {  // 接口的方法的实现。
        return Result.success(name);
    }

    // post新增数据，put更新数据，delete删除数据

    @PostMapping(value = "/post") // 提供接口，路径为"/hello"，"/"路径的HTTP请求将会被映射到hello方法进行处理，就是接口的路径，默认是GET请求和“/”路径
    public Result post(@RequestBody Obj obj) {  // post接口   一般用来提交数据，所以这里接收一个对象。web/post
        return Result.success(obj);  // Obj必须要有getter和setter方法
    }  // 如果不加@RequestBody注解，那么接口的参数就是url参数，就是?name=xxx，如果加了@RequestBody注解，那么接口的参数就是json格式的数据，就是{"name":"xxx"}

    @PutMapping(value = "/put")
    public Result put(@RequestBody Obj obj) {  // put接口一般用来更新数据，所以这里接收一个对象。web/put
        return Result.success(obj);
    }
    // put也可以删除数据。
    @DeleteMapping(value = "/delete/{id}")  // 请求路径中的id参数，通过@PathVariable注解绑定到方法的参数中。路径参数，就是url中的参数，比如：/delete/1
    public Result delete(@PathVariable Integer id) {  // 当然也可以用@RequestBody注解，接收json格式的数据，比如批量删除时，接收一个数组。
        return Result.success(id);
    }
}
