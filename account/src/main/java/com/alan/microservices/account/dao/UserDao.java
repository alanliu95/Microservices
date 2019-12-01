package com.alan.microservices.account.dao;

import com.alan.microservices.account.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("select * from users where id = #{id}")
    User getById(@Param("id")Long id);

//    @Insert("insert into users(id, name) values(#{id}, #{name})")
//    public int insert(User user);
}
