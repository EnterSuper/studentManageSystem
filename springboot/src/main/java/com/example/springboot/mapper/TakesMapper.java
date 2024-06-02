/**
 * 功能：
 * 作者：Olivia
 * 日期：2024/5/30 20:20
 */

package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.Takes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TakesMapper extends BaseMapper<Takes>{

    // 只select这些属性time,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
    @Select("select * from takes where year = #{year} and semester = #{semester} and #{week} >= start_week and #{week} <= end_week order by time_id")
    List<Takes> selectSchedule(@Param("year") String year, @Param("semester") String semester, @Param("week") String week);
}
