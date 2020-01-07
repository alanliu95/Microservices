package com.alan.microservices.asset.service;

import com.alan.microservices.asset.dao.DeviceDao;
import com.alan.microservices.commons.asset.domain.DeviceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alan.microservices.commons.asset.domain.Device;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    DeviceDao deviceDao;

    public Device getByToken(String token) {
        return deviceDao.getByToken(token);
    }

    public List<Device> getAllDevices() {
        return deviceDao.getAllDevices();
    }

    public Device getById(Long id) {
        return deviceDao.getById(id);
    }

    public List<Device> getDevicesBySiteId(long siteId) {
        return deviceDao.getDevicesBySiteId(siteId);
    }

    public List<DeviceDetail> getDevicesDetailBySiteId(Long siteId) {
        return deviceDao.getDevicesDetailBySiteId(siteId);
    }
}
