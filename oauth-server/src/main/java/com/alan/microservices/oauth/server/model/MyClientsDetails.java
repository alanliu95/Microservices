package com.alan.microservices.oauth.server.model;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.HashSet;

public class MyClientsDetails extends BaseClientDetails {
    String clientId;
    String clientSecret;
    HashSet<String> resourceIds;

    public MyClientsDetails(String clientId,String clientSecret, String resourceIds, String scopes, String grantTypes, String authorities, String redirectUris){
        super( clientId,  resourceIds,  scopes,  grantTypes,  authorities,  redirectUris);
        this.clientSecret=clientSecret;
    }
}
