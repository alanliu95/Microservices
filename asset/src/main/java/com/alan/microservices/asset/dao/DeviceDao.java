package com.alan.microservices.asset.dao;

import com.alan.microservices.commons.asset.domain.Device;
import com.alan.microservices.commons.asset.domain.DeviceDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceDao {
    @Select("select * from device where token=#{token} ")
    Device getByToken(String token);

    @Select("select * from device order by site_id ")
    List<Device> getAllDevices();

    @Select("select * from device where id=#{id} ")
    Device getById(Long id);

    @Select("select * from device where site_id=#{siteId} order by id ")
    List<Device> getDevicesBySiteId(Long siteId);

    @Select("select t1.*,t2.name as dev_type_name from device t1 join  dev_type t2\n" +
            " on t1.dev_type=t2.id and t1.site_id=#{siteId}")
    List<DeviceDetail> getDevicesDetailBySiteId(Long siteId);
}
