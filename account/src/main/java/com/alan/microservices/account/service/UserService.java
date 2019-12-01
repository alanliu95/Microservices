package com.alan.microservices.account.service;

import com.alan.microservices.account.dao.UserDao;
import com.alan.microservices.account.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public User getById(Long id){
        return userDao.getById(id);
    }
}
