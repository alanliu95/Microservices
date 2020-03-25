create table oauth_client_details (
                                      client_id VARCHAR(128) PRIMARY KEY,
                                      resource_ids VARCHAR(128),
                                      client_secret VARCHAR(128),
                                      scope VARCHAR(128),
                                      authorized_grant_types VARCHAR(128),
                                      web_server_redirect_uri VARCHAR(128),
                                      authorities VARCHAR(128),
                                      access_token_validity INTEGER,
                                      refresh_token_validity INTEGER,
                                      additional_information VARCHAR(4096),
                                      autoapprove VARCHAR(128)
);

create table oauth_client_token (
                                    token_id VARCHAR(128),
                                    token BLOB,
                                    authentication_id VARCHAR(128) PRIMARY KEY,
                                    user_name VARCHAR(128),
                                    client_id VARCHAR(128)
);

create table oauth_access_token (
                                    token_id VARCHAR(128),
                                    token BLOB,
                                    authentication_id VARCHAR(128) PRIMARY KEY,
                                    user_name VARCHAR(128),
                                    client_id VARCHAR(128),
                                    authentication BLOB,
                                    refresh_token VARCHAR(128)
);

create table oauth_refresh_token (
                                     token_id VARCHAR(128),
                                     token BLOB,
                                     authentication BLOB
);

create table oauth_code (
                            code VARCHAR(128), authentication BLOB
);

create table oauth_approvals (
                                 userId VARCHAR(128),
                                 clientId VARCHAR(128),
                                 scope VARCHAR(128),
                                 status VARCHAR(10),
                                 expiresAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO oauth_client_details(
    client_id ,resource_ids,client_secret,scope,authorized_grant_types,
    web_server_redirect_uri,authorities,access_token_validity,
    refresh_token_validity ,additional_information,
    autoapprove)
VALUES ('client1', 'resource1','$2a$10$YEpRG0cFXz5yfC/lKoCHJ.83r/K3vaXLas5zCeLc.EJsQ/gL5Jvum',
        'scope1,scope2','authorization_code,password,client_credentials,implicit,refresh_token',
        'http://www.baidu.com', 'OP_READ_RES1,OP_UPDATE_RES1,ROLE_ADMIN', '300', '1500', null, 'false');