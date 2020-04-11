package com.alan.microservices.oauth.server.model;

import com.alan.microservices.commons.account.domain.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyUserDetail implements UserDetails {

    private SysUser user;
    private List<String> perms;


    public MyUserDetail(SysUser user, List<String> perms) {
        this.user=user;
        this.perms=perms;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    ////////////////////////////////////////////////

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perms.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
