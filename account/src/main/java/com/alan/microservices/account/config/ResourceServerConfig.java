package com.alan.microservices.account.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration

//开启oauth2 resource server模式
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                //资源服务 id
                .resourceId("resource1")
                .tokenStore(tokenStore)
                //这个貌似是配置要不要把token信息记录在session中
                .stateless(true);
    }

//    @Override
//    //配置受保护资源的访问规则
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests()
//
//                .antMatchers("/ /**").access("#oauth2.hasScope('scope1')")
//                .antMatchers("/swagger-ui.html").permitAll()
//                .and()
//                //这个貌似是配置要不要把token信息记录在session中
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
@Override
//配置受保护资源的访问规则
public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests()

            .anyRequest().permitAll()
            .and()
            //这个貌似是配置要不要把token信息记录在session中
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
}
}
