package com.alan.microservices.oauth.server.service;

import com.alan.microservices.oauth.server.dao.UserMapper;
import com.alan.microservices.oauth.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        //对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user)>0;
    }
}
