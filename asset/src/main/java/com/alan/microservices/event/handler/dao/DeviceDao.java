package com.alan.microservices.event.handler.dao;

import com.alan.microservices.event.handler.domain.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeviceDao {
    @Select("select * from device where token=#{token} ")
    Device getByToken(String token);
}
