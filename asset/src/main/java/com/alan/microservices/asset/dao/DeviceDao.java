package com.alan.microservices.asset.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.alan.microservices.commons.asset.domain.Device;

import java.util.List;

@Mapper
public interface DeviceDao {
    @Select("select * from device where token=#{token} ")
    Device getByToken(String token);

    @Select("select * from device order by site_id ")
    List<Device> getAllDevices();

    @Select("select * from device where id=#{id} ")
    Device getById(Long id);
}
