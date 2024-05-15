/**
 * 功能：分页类
 * 作者：Pjkang
 * 日期：2024/5/14 11:24
 */

package com.example.springboot.common;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {  // 泛型类，T表示任意类型，表示页面数据的类型
    private List<T> list;  // 每页的数据
    private Integer total;  // 每页的个数
}
