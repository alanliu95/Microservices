package com.alan.microservices.oauth.client.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyClientsDetails extends BaseClientDetails {
    String clientId;
    String clientSecret;
    HashSet<String> resourceIds;

    public MyClientsDetails(String clientId,String clientSecret, String resourceIds, String scopes, String grantTypes, String authorities, String redirectUris){
        super( clientId,  resourceIds,  scopes,  grantTypes,  authorities,  redirectUris);
        this.clientSecret=clientSecret;
    }
}
