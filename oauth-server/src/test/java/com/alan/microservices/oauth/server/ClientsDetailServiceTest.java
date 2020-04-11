package com.alan.microservices.oauth.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientsDetailServiceTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;

    @Test
    public void addClient(){
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(passwordEncoder);
//        JdbcClientDetailsService service=(JdbcClientDetailsService)clientDetailsService;
        //(String clientId, String resourceIds, String scopes, String grantTypes, String authorities, String redirectUris)
        BaseClientDetails clientDetails=new BaseClientDetails("client2","resource1","scope1",
                "authorization_code,password,client_credentials,implicit,refresh_token",
                "OP_READ_RES1,OP_UPDATE_RES1,ROLE_ADMIN","www.baidu.com");
        clientDetails.setClientSecret("client2");
        clientDetailsService.addClientDetails(clientDetails);
    }

}
