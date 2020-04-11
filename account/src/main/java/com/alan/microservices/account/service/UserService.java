package com.alan.microservices.account.service;

import com.alan.microservices.account.dao.SysRolePermissionMapper;
import com.alan.microservices.account.dao.SysUserMapper;
import com.alan.microservices.account.dao.SysUserRoleMapper;
import com.alan.microservices.account.model.SysRole;
import com.alan.microservices.account.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    SysUserMapper  userMapper;
    @Autowired
    SysUserRoleMapper userRoleMapper;
    @Autowired
    SysRolePermissionMapper rolePermissionMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

//    @PreAuthorize("hasAnyAuthority('OP_GET_USER')")
    public SysUser getById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

//    @PreAuthorize("hasAnyAuthority('OP_GET_USER')")
    public List<SysUser> getAll() {
        return userMapper.selectAll();
    }
    public List<String> getPermsAndRolesByUserName(String username){
        List<SysRole> roles=userMapper.getRolesByUsername(username);
        List<String> perms=userMapper.getPermsByUsername(username);
        if (perms==null) return null;
        for(SysRole role:roles){
                perms.add(role.getEnname());
        }
        return perms;
    }

    public SysUser getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

//    @PreAuthorize("hasAnyAuthority('OP_CREATE_USER')")
    public int createUser(SysUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.insert(user);
    }
}
