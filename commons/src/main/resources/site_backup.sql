create table dev_type
(
    id          int auto_increment
        primary key,
    name        varchar(256) null,
    icon        varchar(256) null,
    data_struct varchar(256) null,
    info        varchar(256) null
)
    charset = utf8;

INSERT INTO  dev_type (id, name, icon, data_struct, info) VALUES (1, 'ubuntu服务器', null, null, '戴尔机架式服务器');
INSERT INTO  dev_type (id, name, icon, data_struct, info) VALUES (2, 'windows主机', null, null, '戴尔主机，运行windows10操作系统');
INSERT INTO  dev_type (id, name, icon, data_struct, info) VALUES (3, 'vmware', null, null, 'vmware虚拟机');
INSERT INTO  dev_type (id, name, icon, data_struct, info) VALUES (4, '树莓派', null, null, '卡片式计算机');
create table device
(
    id        int auto_increment
        primary key,
    site_id   int          not null,
    name      varchar(256) null,
    token     varchar(256) not null,
    dev_type  int          null,
    longitude float        null comment '经度',
    latitude  float        null comment '维度',
    info      varchar(256) null,
    constraint token
        unique (token)
)
    charset = utf8;

INSERT INTO  device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (1, 1, 'desktop-alan', 'desktop-alan', 2, 31.146, 121.421, 'windows10工作站');
INSERT INTO  device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (2, 1, 'ubuntu_14th', 'ubuntu_14th', 1, null, null, 'k8s集群worker节点');
INSERT INTO  device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (4, 1, 'vm-ubuntu16', 'vm-ubuntu16', 3, null, null, 'k8s集群master节点');
INSERT INTO  device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (5, 2, 'pi', 'pi', 4, null, null, '树莓派');
INSERT INTO  device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (6, 2, 'mk60', 'mk60', 4, null, null, null);
INSERT INTO  device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (7, 2, 'stm32', 'stm32', 4, null, null, null);
create table mqtt_acls
(
    topic      varchar(255) not null,
    permission varchar(10)  not null,
    id         int auto_increment
        primary key,
    token      varchar(255) not null
)
    comment 'mqtt设备权限控制';

INSERT INTO  mqtt_acls (topic, permission, id, token) VALUES ('/site/status', 'rw', 1, 'mk60');
INSERT INTO  mqtt_acls (topic, permission, id, token) VALUES ('/site/data', 'w', 2, 'mk60');
create table oauth_access_token
(
    token_id          varchar(128) null,
    token             blob         null,
    authentication_id varchar(128) not null
        primary key,
    user_name         varchar(128) null,
    client_id         varchar(128) null,
    authentication    blob         null,
    refresh_token     varchar(128) null
);


create table oauth_approvals
(
    userId         varchar(128)                        null,
    clientId       varchar(128)                        null,
    scope          varchar(128)                        null,
    status         varchar(10)                         null,
    expiresAt      timestamp default CURRENT_TIMESTAMP not null,
    lastModifiedAt timestamp default CURRENT_TIMESTAMP not null
);


create table oauth_client_details
(
    client_id               varchar(128)  not null
        primary key,
    resource_ids            varchar(128)  null,
    client_secret           varchar(128)  null,
    scope                   varchar(128)  null,
    authorized_grant_types  varchar(128)  null,
    web_server_redirect_uri varchar(128)  null,
    authorities             varchar(128)  null,
    access_token_validity   int           null,
    refresh_token_validity  int           null,
    additional_information  varchar(4096) null,
    autoapprove             varchar(128)  null
);

INSERT INTO  oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('client1', 'resource1', '$2a$10$YEpRG0cFXz5yfC/lKoCHJ.83r/K3vaXLas5zCeLc.EJsQ/gL5Jvum', 'scope1,scope2,scope3', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', 'OP_READ_RES1,OP_UPDATE_RES1,ROLE_ADMIN', 3600, 1500, null, 'false');
INSERT INTO  oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('client2', 'resource1', '$2a$10$F.kzXriSk9wjyhOSYUSX6ujI2a9lkvjfzbhzhfMhdxq84HRAmn9PC', '', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', '', null, null, '{}', '');
create table oauth_client_token
(
    token_id          varchar(128) null,
    token             blob         null,
    authentication_id varchar(128) not null
        primary key,
    user_name         varchar(128) null,
    client_id         varchar(128) null
);


create table oauth_code
(
    code           varchar(128) null,
    authentication blob         null
);


create table oauth_refresh_token
(
    token_id       varchar(128) null,
    token          blob         null,
    authentication blob         null
);


create table site
(
    id        bigint auto_increment comment '场地id'
        primary key,
    name      varchar(256) not null comment '场地名',
    longitude float        null comment '经度',
    latitude  float        null comment '维度',
    province  char(20)     not null,
    city      char(20)     not null,
    address   varchar(255) not null,
    stage     int          not null,
    constraint idx_name
        unique (name)
)
    comment '场地基本信息' charset = utf8;

INSERT INTO  site (id, name, longitude, latitude, province, city, address, stage) VALUES (1, '华理实验七楼', 121.421, 31.421, '上海市', '上海市', '徐汇区梅陇路130号', 0);
INSERT INTO  site (id, name, longitude, latitude, province, city, address, stage) VALUES (2, '华理实验十五楼', 110, 29.5, '上海市', '上海市', '徐汇区梅陇路130号', 1);
INSERT INTO  site (id, name, longitude, latitude, province, city, address, stage) VALUES (3, '合肥市', 117.25, 31.83, '安徽省', '合肥市', '新安江路', 2);
INSERT INTO  site (id, name, longitude, latitude, province, city, address, stage) VALUES (4, '北京', 116.28, 39.1, '北京市', '北京市', '王府井大街', 3);
create table sys_permission
(
    id          bigint auto_increment
        primary key,
    name        varchar(64)  not null comment '权限名称',
    enname      varchar(64)  not null comment '权限英文名称',
    description varchar(200) null comment '备注',
    created     datetime     not null,
    updated     datetime     not null
)
    comment '权限表' charset = utf8;

INSERT INTO  sys_permission (id, name, enname, description, created, updated) VALUES (37, '查看用户', 'OP_GET_USER', null, '2019-04-04 15:30:30', '2019-04-04 15:30:43');
INSERT INTO  sys_permission (id, name, enname, description, created, updated) VALUES (38, '新增用户', 'OP_CREATE_USER', null, '2019-04-04 15:30:31', '2019-04-04 15:30:44');
INSERT INTO  sys_permission (id, name, enname, description, created, updated) VALUES (39, '编辑用户', 'OP_UPDATE_USER', null, '2019-04-04 15:30:32', '2019-04-04 15:30:45');
INSERT INTO  sys_permission (id, name, enname, description, created, updated) VALUES (40, '删除用户', 'OP_DELETE_USER', null, '2019-04-04 15:30:48', '2019-04-04 15:30:45');
create table sys_role
(
    id          bigint auto_increment
        primary key,
    name        varchar(64)  not null comment '角色名称',
    enname      varchar(64)  not null comment '角色英文名称',
    description varchar(200) null comment '备注',
    created     datetime     not null,
    updated     datetime     not null
)
    comment '角色表' charset = utf8;

INSERT INTO  sys_role (id, name, enname, description, created, updated) VALUES (37, '超级管理员', 'ROLE_ADMIN', '超级管理员', '2019-04-04 23:22:03', '2019-04-04 23:22:05');
INSERT INTO  sys_role (id, name, enname, description, created, updated) VALUES (38, '普通用户', 'ROLE_USER', '普通用户', '2020-04-05 18:05:14', '2020-04-05 18:05:18');
create table sys_role_permission
(
    id            bigint auto_increment
        primary key,
    role_id       bigint not null comment '角色 ID',
    permission_id bigint not null comment '权限 ID'
)
    comment '角色权限表' charset = utf8;

INSERT INTO  sys_role_permission (id, role_id, permission_id) VALUES (37, 37, 37);
INSERT INTO  sys_role_permission (id, role_id, permission_id) VALUES (38, 37, 38);
INSERT INTO  sys_role_permission (id, role_id, permission_id) VALUES (39, 37, 39);
INSERT INTO  sys_role_permission (id, role_id, permission_id) VALUES (40, 37, 40);
INSERT INTO  sys_role_permission (id, role_id, permission_id) VALUES (43, 38, 37);
INSERT INTO  sys_role_permission (id, role_id, permission_id) VALUES (44, 38, 39);
create table sys_user
(
    id           bigint auto_increment
        primary key,
    username     varchar(50)  not null comment '用户名',
    password     varchar(128) not null comment '密码，加密存储',
    phone        varchar(20)  null comment '注册手机号',
    email        varchar(50)  null comment '注册邮箱',
    created      datetime     not null,
    updated      datetime     not null,
    avatar       varchar(255) null,
    organization varchar(255) null,
    constraint username
        unique (username)
)
    comment '用户表' charset = utf8;

INSERT INTO  sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (37, 'admin', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO  sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (38, 'alan', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO  sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (39, 'alan1', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO  sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (40, 'alan2', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO  sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (41, 'alan3', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO  sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (42, 'alan4', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO  sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (43, 'alan5', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO  sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (44, 'user', '$2a$10$V58b.Gh0RiikLCVVMMdnD.X37DL6K/ww0Sxlq8lhOw1vVNKKfzaji', null, null, '2020-04-06 17:23:25', '2020-04-06 17:23:25', null, null);
create table sys_user_role
(
    id      bigint auto_increment
        primary key,
    user_id bigint not null comment '用户 ID',
    role_id bigint not null comment '角色 ID'
)
    comment '用户角色表' charset = utf8;

INSERT INTO  sys_user_role (id, user_id, role_id) VALUES (37, 37, 37);
INSERT INTO  sys_user_role (id, user_id, role_id) VALUES (38, 38, 38);