/**
 * 功能：
 * 作者：Olivia
 * 日期：2024/5/30 20:31
 */

package com.example.springboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Takes;
import com.example.springboot.mapper.TakesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TakesService extends ServiceImpl<TakesMapper, Takes>{

    @Resource
    TakesMapper takesMapper;

    public Takes selectSchedule(String year, String semester, String week, Integer id) {
        QueryWrapper<Takes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("year", year)
                .eq("semester", semester)
                .ge("start_week", week)
                .le("end_week", week);
        return getOne(queryWrapper);
    }

    public Takes selectGrade(String year, String semester, String week, Integer id) {
        QueryWrapper<Takes> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("year", year)
                .eq("semester", semester);

        return getOne(queryWrapper);

    }
}