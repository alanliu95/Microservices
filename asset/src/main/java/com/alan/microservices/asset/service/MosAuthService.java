package com.alan.microservices.asset.service;

import com.alan.microservices.asset.dao.DeviceDao;
import com.alan.microservices.asset.dao.MosAuthDao;
import com.alan.microservices.asset.model.MqttAcl;
import com.alan.microservices.commons.asset.domain.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MosAuthService {
    public static final Logger logger = LoggerFactory.getLogger(MosAuthService.class);
    private MosAuthDao mosAuthDao;
    private DeviceDao deviceDao;

    @Autowired
    public MosAuthService(DeviceDao deviceDao, MosAuthDao mosAuthDao) {
        this.deviceDao = deviceDao;
        this.mosAuthDao = mosAuthDao;
    }

    public boolean authorize(String token, String topic, int acc) {
        MqttAcl mqttAcl = mosAuthDao.authorize(token, topic);
        String permission = null;
        switch (acc) {
            case 1:
            case 4: {
                permission = "r";
                break;
            }
            case 2: {
                permission = "w";
                break;
            }
        }
        logger.debug("acl check: token:{},topic:{},permission:{}", token, topic, permission);
        if (mqttAcl != null && mqttAcl.getPermission().indexOf(permission) >= 0)
            return true;
        return false;
    }

    public boolean authenticate(String username) {
        Device device = deviceDao.getByToken(username);
        if (device != null)
            return true;
        return false;
    }
}
