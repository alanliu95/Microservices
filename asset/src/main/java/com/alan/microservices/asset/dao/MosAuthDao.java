package com.alan.microservices.asset.dao;

import com.alan.microservices.asset.model.MqttAcl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MosAuthDao {
    @Select("select * from mqtt_acls where token= #{token} and topic=#{topic}")
    MqttAcl authorize(@Param("token") String token, @Param("topic") String topic);
}
