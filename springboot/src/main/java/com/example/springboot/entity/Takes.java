/**
 * 功能：
 * 作者：Olivia
 * 日期：2024/5/30 20:33
 */

package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data  // 通过该注解，可以省略getter和setter方法和构造方法和builder方法
@TableName("takes")  // 通过该注解，可以指定表名
public class Takes {
    Integer id;
    String year;
    String semester;
    Integer start_week;
    Integer end_week;
    Integer course_id;
    String title;
    Float credits;
    String type;
    Integer grade;
    String time;
    String Monday;
    String Tuesday;
    String Wednesday;
    String Thursday;
    String Friday;
    String Saturday;
    String Sunday;
    Integer time_id;
}
