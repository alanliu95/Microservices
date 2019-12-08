package com.alan.microservices.event.handler.service;

import com.alan.microservices.event.handler.dao.DeviceDao;
import com.alan.microservices.event.handler.domain.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    @Autowired
    DeviceDao deviceDao;

    public Device getByToken(String token) {
        return deviceDao.getByToken(token);
    }
}
