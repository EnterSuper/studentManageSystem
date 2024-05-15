package com.example.springboot.mapper;

import com.example.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper  // 通过该注解，可以将该接口交给Spring容器管理。比如在其他类中直接注入该接口的对象。
public interface UserMapper {

    @Insert("insert into `user` (username, password, name, phone, email, address, avatar)" +
            "values (#{username}, #{password}, #{name}, #{phone}, #{email}, #{address}, #{avatar})")
        // 通过该注解，可以将该方法映射到对应的sql语句。就是在调用这个方法的时候，会执行对应的sql语句。
    void insertUser(User user);

    @Update("update `user` set username = #{username}, password = #{password}, name = #{name}, " +
            "phone = #{phone}, email = #{email}, address = #{address}, avatar = #{avatar} where id = #{id}")
    void updateUser(User user);

    @Delete("delete from `user` where id = #{id}")
    void delete(Integer id);

    @Select("select * from `user` order by id desc")
    List<User> selectAll();

    @Select("select * from `user` where id = #{id} order by id desc")
    User selectById(Integer id);

    @Select("select * from `user` where name = #{name} order by id desc")
    List<User> selectByName(String name);

    @Select("select * from `user` where username = #{username} and name = #{name} order by id desc")
    List<User> selectByMore(@Param("username") String username, @Param("name") String name);  // @Param注解是给参数取别名，这样在sql语句中就可以使用这个别名。
    // 为什么有时候不写@Param注解也可以呢？因为MyBatis会自动将参数封装成一个Map，key是参数名，value是参数值。所以在sql语句中可以直接使用参数名。
    // 但是如果有多个参数，且参数名相同，就会出现问题。所以最好还是写上@Param注解。
    @Select("select * from `user` where username like concat('%', #{username}, '%') and name like " +
            "concat('%', #{name}, '%') order by id desc")  // concat是连接字符串的函数。'%'+字符串+'%'表示模糊查询。
    List<User> selectByMoreLike(@Param("username") String username, @Param("name") String name);

    @Select("select * from `user` where username like concat('%', #{username}, '%') and name like " +
            "concat('%', #{name}, '%') order by id desc limit #{skipNum}, #{pageSize}")  // limit是分页查询的函数。limit 起始位置, 查询条数，limit后面不能用括号。
    List<User> selectByPage(@Param("skipNum") Integer skipNum, @Param("pageSize") Integer pageSize,
                            @Param("username") String username, @Param("name") String name);


}
