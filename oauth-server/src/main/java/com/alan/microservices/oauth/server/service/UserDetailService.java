package com.alan.microservices.oauth.server.service;

import com.alan.microservices.commons.account.domain.SysUser;
import com.alan.microservices.oauth.server.dao.SysUserMapper;
import com.alan.microservices.oauth.server.model.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class UserDetailService implements UserDetailsService {
    @Autowired
    SysUserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user=userMapper.getByUsername(username);
        List<String> perms=userMapper.getPermsByUsername(username);
        return new MyUserDetail(user,perms);
    }
}
