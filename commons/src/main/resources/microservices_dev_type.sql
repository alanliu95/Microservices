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

INSERT INTO microservices.dev_type (id, name, icon, data_struct, info) VALUES (1, 'ubuntu服务器', null, null, '戴尔机架式服务器');
INSERT INTO microservices.dev_type (id, name, icon, data_struct, info) VALUES (2, 'windows主机', null, null, '戴尔主机，运行windows10操作系统');
INSERT INTO microservices.dev_type (id, name, icon, data_struct, info) VALUES (3, 'vmware', null, null, 'vmware虚拟机');
INSERT INTO microservices.dev_type (id, name, icon, data_struct, info) VALUES (4, '树莓派', null, null, '卡片式计算机');
create table device
(
    id        int auto_increment
        primary key,
    site_id   int          not null,
    name      varchar(255) null,
    token     varchar(255) not null,
    dev_type  varchar(100) null,
    longitude double       null comment '经度',
    latitude  double       null comment '维度',
    info      varchar(255) null,
    constraint token
        unique (token)
)
    charset = utf8;

INSERT INTO microservices.device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (1, 1, 'desktop-alan', 'desktop-alan', '2', 31.145999908447266, 121.4209976196289, 'windows10工作站');
INSERT INTO microservices.device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (2, 1, 'ubuntu_14th', 'ubuntu_14th', '1', null, null, 'k8s集群worker节点');
INSERT INTO microservices.device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (4, 1, 'vm-ubuntu16', 'vm-ubuntu16', '3', null, null, 'k8s集群master节点');
INSERT INTO microservices.device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (5, 2, 'pi', 'pi', '4', null, null, '树莓派');
INSERT INTO microservices.device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (6, 2, 'mk60', 'mk60', '4', null, null, null);
INSERT INTO microservices.device (id, site_id, name, token, dev_type, longitude, latitude, info) VALUES (7, 2, 'stm32', 'stm32', '4', null, null, null);
create table invest_assessment
(
    id          bigint auto_increment
        primary key,
    site_id     bigint      null,
    contaminant varchar(30) null,
    level1      int         null,
    level2      int         null,
    level3      int         null,
    level4      int         null,
    level5      int         null
);


create table invest_contaminant
(
    id            bigint auto_increment
        primary key,
    pointId       bigint      null,
    name          varchar(30) null comment '名称',
    cas           varchar(30) null comment 'cas编号',
    concentration float       null comment '浓度',
    control_val   float       null,
    screening_val float       null,
    unit          varchar(30) null,
    type          varchar(30) null
);

INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (1, 1, '砷', '7440-38-2', 35, 140, 60, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (2, 1, '镉', '7440-43-9', 55, 172, 65, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (3, 1, '铬', '18540-29-9', 6, 78, 5.7, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (4, 1, '铜', '7440-50-8', 7500, 36000, 18000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (5, 1, '甲苯', '108-88-3', 1300, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (6, 1, '间二甲苯', '108-38-3', 600, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (7, 1, '对二甲苯', '106-42-3', 475, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (8, 1, '乙苯', '100-41-4', 30, 280, 28, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (9, 1, '萘', '91-20-3', 75, 700, 70, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (10, 1, '䓛', '91-20-3', 1250, 12900, 1293, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (11, 1, '苯并[a]蒽', '56-55-3', 12, 151, 15, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (12, 1, '苯并[a]芘', '50-32-8', 2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (13, 2, '镉', '7440-43-9', 60, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (14, 2, '铬', '18540-29-9', 4, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (15, 2, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (16, 2, '甲苯', '108-88-3', 1321, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (17, 2, '间二甲苯', '108-38-3', 580, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (18, 2, '对二甲苯', '106-42-3', 500, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (19, 2, '乙苯', '100-41-4', 30.5, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (20, 2, '萘', '91-20-3', 75, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (21, 2, '䓛', '91-20-3', 1255, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (22, 2, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (23, 2, '苯并[a]芘', '50-32-8', 2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (24, 2, '砷', '7440-38-2', 42, 140, 60, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (25, 3, '镉', '7440-43-9', 55, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (26, 3, '铬', '18540-29-9', 6, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (27, 3, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (28, 3, '甲苯', '108-88-3', 1215, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (29, 3, '间二甲苯', '108-38-3', 610, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (30, 3, '对二甲苯', '106-42-3', 512, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (31, 3, '乙苯', '100-41-4', 31, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (32, 3, '萘', '91-20-3', 75, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (33, 3, '䓛', '91-20-3', 1257, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (34, 3, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (35, 3, '苯并[a]芘', '50-32-8', 3, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (36, 4, '镉', '7440-43-9', 40, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (37, 4, '铬', '18540-29-9', 5, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (38, 4, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (39, 4, '甲苯', '108-88-3', 1541, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (40, 4, '间二甲苯', '108-38-3', 485, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (41, 4, '对二甲苯', '106-42-3', 530, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (42, 4, '乙苯', '100-41-4', 32, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (43, 4, '萘', '91-20-3', 85, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (44, 4, '䓛', '91-20-3', 1259, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (45, 4, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (46, 4, '苯并[a]芘', '50-32-8', 3.2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (47, 5, '镉', '7440-43-9', 55, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (48, 5, '铬', '18540-29-9', 6, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (49, 5, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (50, 5, '甲苯', '108-88-3', 1021, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (51, 5, '间二甲苯', '108-38-3', 480, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (52, 5, '对二甲苯', '106-42-3', 500, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (53, 5, '乙苯', '100-41-4', 30.8, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (54, 5, '萘', '91-20-3', 75, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (55, 5, '䓛', '91-20-3', 1300, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (56, 5, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (57, 5, '苯并[a]芘', '50-32-8', 3.2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (58, 6, '镉', '7440-43-9', 35, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (59, 6, '铬', '18540-29-9', 7, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (60, 6, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (61, 6, '甲苯', '108-88-3', 1251, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (62, 6, '间二甲苯', '108-38-3', 485, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (63, 6, '对二甲苯', '106-42-3', 546, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (64, 6, '乙苯', '100-41-4', 31, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (65, 6, '萘', '91-20-3', 82, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (66, 6, '䓛', '91-20-3', 1280, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (67, 6, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (68, 6, '苯并[a]芘', '50-32-8', 3.2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (69, 3, '砷', '7440-38-2', 35, 60, 140, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (70, 7, '镉', '7440-43-9', 55, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (71, 7, '铬', '18540-29-9', 6, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (72, 7, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (73, 7, '甲苯', '108-88-3', 1321, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (74, 7, '间二甲苯', '108-38-3', 600, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (75, 7, '对二甲苯', '106-42-3', 578, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (76, 7, '乙苯', '100-41-4', 32, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (77, 7, '萘', '91-20-3', 75, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (78, 7, '䓛', '91-20-3', 1285, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (79, 7, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (80, 7, '苯并[a]芘', '50-32-8', 3.2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (81, 4, '砷', '7440-38-2', 70, 60, 140, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (82, 8, '镉', '7440-43-9', 55, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (83, 8, '铬', '18540-29-9', 8, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (84, 8, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (85, 8, '甲苯', '108-88-3', 1574, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (86, 8, '间二甲苯', '108-38-3', 485, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (87, 8, '对二甲苯', '106-42-3', 590, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (88, 8, '乙苯', '100-41-4', 36, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (89, 8, '萘', '91-20-3', 90, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (90, 8, '䓛', '91-20-3', 1290, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (91, 8, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (92, 8, '苯并[a]芘', '50-32-8', 3.2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (93, 5, '砷', '7440-38-2', 38, 60, 140, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (94, 9, '镉', '7440-43-9', 48, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (95, 9, '铬', '18540-29-9', 6, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (96, 9, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (97, 9, '甲苯', '108-88-3', 1524, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (98, 9, '间二甲苯', '108-38-3', 620, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (99, 9, '对二甲苯', '106-42-3', 600, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (100, 9, '乙苯', '100-41-4', 35, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (101, 9, '萘', '91-20-3', 75, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (102, 9, '䓛', '91-20-3', 1293, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (103, 9, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (104, 9, '苯并[a]芘', '50-32-8', 3.2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (105, 6, '砷', '7440-38-2', 35.4, 60, 140, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (106, 10, '镉', '7440-43-9', 55, 65, 172, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (107, 10, '铬', '18540-29-9', 6, 5.7, 78, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (108, 10, '铜', '7440-50-8', 7500, 18000, 36000, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (109, 10, '甲苯', '108-88-3', 1544, 1200, 1200, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (110, 10, '间二甲苯', '108-38-3', 612, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (111, 10, '对二甲苯', '106-42-3', 610, 570, 570, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (112, 10, '乙苯', '100-41-4', 30, 28, 280, 'mg/kg', '苯系物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (113, 10, '萘', '91-20-3', 75, 70, 700, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (114, 10, '䓛', '91-20-3', 1295, 1293, 12900, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (115, 10, '苯并[a]蒽', '56-55-3', 12, 15, 151, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (116, 10, '苯并[a]芘', '50-32-8', 3.2, 1.5, 5.5, 'mg/kg', '多环芳烃');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (117, 10, '砷', '7440-38-2', 45, 60, 140, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (118, 7, '砷', '7440-38-2', 35, 60, 140, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (119, 8, '砷', '7440-38-2', 44, 60, 140, 'mg/kg', '重金属和无机物');
INSERT INTO microservices.invest_contaminant (id, pointId, name, cas, concentration, control_val, screening_val, unit, type) VALUES (120, 9, '砷', '7440-38-2', 35, 60, 140, 'mg/kg', '重金属和无机物');
create table invest_point
(
    id            bigint auto_increment
        primary key,
    site_id       bigint       null,
    longitude     double       null,
    latitude      double       null,
    elevation     double       null,
    soil_type     varchar(255) null,
    sampling_time datetime     null,
    sn            varchar(255) null,
    region        varchar(255) null
)
    comment '采样点基本属性';

INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (1, 1, 32.02088928222656, 114.91158294677734, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S1', '甲醇脱硫');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (2, 1, 32.02329635620117, 114.90909576416016, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S2', '锅炉污水池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (3, 1, 32.023624420166016, 114.91119384765625, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S3', '造气沉灰池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (4, 1, 32.022708892822266, 114.91378021240234, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S4', '污水站');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (5, 1, 32.024505615234375, 119.9110107421875, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S5', '平流沉淀池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (6, 1, 32.023380279541016, 114.91082763671875, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S6', '造气厂房');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (7, 1, 32.0206413269043, 114.91136169433594, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S7', '甲醇合成');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (8, 1, 32.023162841796875, 114.9141616821289, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S8', '龙山灌渠下游');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (10, 1, 32.025787353515625, 32.025787353515625, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S10', '合成氨变换');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (11, 1, 32.02549362182617, 114.91020965576172, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S11', '合成氨脱硫');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (12, 1, 32.025352478027344, 114.91082763671875, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S12', '造气楼');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (13, 1, 32.023963928222656, 114.90950775146484, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S13', '型煤场');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (14, 1, 32.02580261230469, 114.9139175415039, 0.5, '黄棕壤', '2018-04-12 13:07:18', 'S14', '家属区');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (15, 1, 32.02090072631836, 114.91200256347656, 1.5, '黄棕壤', '2018-04-12 13:07:18', 'S1', '甲醇脱硫');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (16, 1, 32.02090072631836, 114.91200256347656, 1, '黄棕壤', '2018-04-12 13:07:18', 'S1', '甲醇脱硫');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (17, 1, 32.02090072631836, 114.91200256347656, 2, '黄棕壤', '2018-04-12 13:07:18', 'S1', '甲醇脱硫');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (18, 1, 32.02330017089844, 114.90899658203125, 1, '黄棕壤', '2018-04-12 13:07:18', 'S2', '锅炉污水池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (19, 1, 32.02330017089844, 114.90899658203125, 1.5, '黄棕壤', '2018-04-12 13:07:18', 'S2', '锅炉污水池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (20, 1, 32.02330017089844, 114.90899658203125, 2, '黄棕壤', '2018-04-12 13:07:18', 'S2', '锅炉污水池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (21, 1, 32.02360153198242, 114.91100311279297, 1, '黄棕壤', '2018-04-12 13:07:18', 'S3', '造气沉灰池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (22, 1, 32.02360153198242, 114.91100311279297, 1.5, '黄棕壤', '2018-04-12 13:07:18', 'S3', '造气沉灰池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (23, 1, 32.02360153198242, 114.91100311279297, 2, '黄棕壤', '2018-04-12 13:07:18', 'S3', '造气沉灰池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (24, 1, 32.022701263427734, 114.91400146484375, 1, '黄棕壤', '2018-04-12 13:07:18', 'S4', '污水站');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (25, 1, 32.022701263427734, 114.91400146484375, 1.5, '黄棕壤', '2018-04-12 13:07:18', 'S4', '污水站');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (26, 1, 32.022701263427734, 114.91400146484375, 2, '黄棕壤', '2018-04-12 13:07:18', 'S4', '污水站');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (27, 1, 32.02450180053711, 119.91100311279297, 1, '黄棕壤', '2018-04-12 13:07:18', 'S5', '平流沉淀池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (28, 1, 32.02450180053711, 119.91100311279297, 1.5, '黄棕壤', '2018-04-12 13:07:18', 'S5', '平流沉淀池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (29, 1, 32.02450180053711, 119.91100311279297, 2, '黄棕壤', '2018-04-12 13:07:18', 'S5', '平流沉淀池');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (30, 1, 32.023399353027344, 114.91100311279297, 1, '黄棕壤', '2018-04-12 13:07:18', 'S6', '造气厂房');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (31, 1, 32.023399353027344, 114.91100311279297, 1.5, '黄棕壤', '2018-04-12 13:07:18', 'S6', '造气厂房');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (32, 1, 32.023399353027344, 114.91100311279297, 2, '黄棕壤', '2018-04-12 13:07:18', 'S6', '造气厂房');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (36, 1, 32.020599365234375, 114.91100311279297, 1, '黄棕壤', '2018-04-12 13:07:18', 'S7', '甲醇合成');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (37, 1, 32.020599365234375, 114.91100311279297, 1.5, '黄棕壤', '2018-04-12 13:07:18', 'S7', '甲醇合成');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (38, 1, 32.020599365234375, 114.91100311279297, 2, '黄棕壤', '2018-04-12 13:07:18', 'S7', '甲醇合成');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (39, 1, 32.02320098876953, 114.91400146484375, 1, '黄棕壤', '2018-04-12 13:07:18', 'S8', '龙山灌渠下游');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (40, 1, 32.02320098876953, 114.91400146484375, 1.5, '黄棕壤', '2018-04-12 13:07:18', 'S8', '龙山灌渠下游');
INSERT INTO microservices.invest_point (id, site_id, longitude, latitude, elevation, soil_type, sampling_time, sn, region) VALUES (41, 1, 32.02320098876953, 114.91400146484375, 2, '黄棕壤', '2018-04-12 13:07:18', 'S8', '龙山灌渠下游');
create table invest_statistics
(
    id                  bigint auto_increment
        primary key,
    site_id             bigint      null,
    contaminant         varchar(30) null,
    max_val             double      null,
    min_val             double      null,
    detection_ratio     double      null,
    exceed_ratio        double      null,
    max_exceed_multiple double      null
);


create table mqtt_acls
(
    topic      varchar(255) not null,
    permission varchar(10)  not null,
    id         int auto_increment
        primary key,
    token      varchar(255) not null
)
    comment 'mqtt设备权限控制';

INSERT INTO microservices.mqtt_acls (topic, permission, id, token) VALUES ('/site/status', 'rw', 1, 'mk60');
INSERT INTO microservices.mqtt_acls (topic, permission, id, token) VALUES ('/site/data', 'w', 2, 'mk60');
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

INSERT INTO microservices.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('client1', 'resource1', '$2a$10$YEpRG0cFXz5yfC/lKoCHJ.83r/K3vaXLas5zCeLc.EJsQ/gL5Jvum', 'scope1,scope2,scope3', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', 'OP_READ_RES1,OP_UPDATE_RES1,ROLE_ADMIN', 3600, 1500, null, 'false');
INSERT INTO microservices.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('client2', 'resource1', '$2a$10$F.kzXriSk9wjyhOSYUSX6ujI2a9lkvjfzbhzhfMhdxq84HRAmn9PC', '', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', '', null, null, '{}', '');
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
    id               bigint auto_increment comment '场地id'
        primary key,
    name             varchar(256) not null comment '场地名',
    longitude        double       null comment '经度',
    latitude         double       null comment '维度',
    province         char(20)     not null,
    city             char(20)     not null,
    address          varchar(255) not null,
    stage            int          not null,
    char_contaminant varchar(255) null,
    polluter         varchar(255) null,
    constraint idx_name
        unique (name)
)
    comment '场地基本信息' charset = utf8;

INSERT INTO microservices.site (id, name, longitude, latitude, province, city, address, stage, char_contaminant, polluter) VALUES (1, '华理实验七楼', 121.4209976196289, 31.42099952697754, '上海市', '上海市', '徐汇区梅陇路130号', 0, null, null);
INSERT INTO microservices.site (id, name, longitude, latitude, province, city, address, stage, char_contaminant, polluter) VALUES (2, '华理实验十五楼', 110, 29.5, '上海市', '上海市', '徐汇区梅陇路130号', 1, null, null);
INSERT INTO microservices.site (id, name, longitude, latitude, province, city, address, stage, char_contaminant, polluter) VALUES (3, '合肥市', 117.25, 31.829999923706055, '安徽省', '合肥市', '新安江路', 0, null, null);
INSERT INTO microservices.site (id, name, longitude, latitude, province, city, address, stage, char_contaminant, polluter) VALUES (4, '北京', 116.27999877929688, 39.099998474121094, '北京市', '北京市', '王府井大街', 1, null, null);
create table site_doc
(
    id          bigint auto_increment
        primary key,
    site_id     bigint       null,
    name        varchar(100) null,
    type        varchar(30)  null,
    url         varchar(100) null,
    upload_time datetime     null
);


create table site_history
(
    id                  bigint auto_increment
        primary key,
    site_id             bigint       null,
    organization        varchar(100) null,
    purpose             varchar(255) null,
    pollution_condition varchar(255) null,
    starting_time       datetime     null,
    ending_time         datetime     null
)
    comment '经营历史';


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

INSERT INTO microservices.sys_permission (id, name, enname, description, created, updated) VALUES (37, '查看用户', 'OP_GET_USER', null, '2019-04-04 15:30:30', '2019-04-04 15:30:43');
INSERT INTO microservices.sys_permission (id, name, enname, description, created, updated) VALUES (38, '新增用户', 'OP_CREATE_USER', null, '2019-04-04 15:30:31', '2019-04-04 15:30:44');
INSERT INTO microservices.sys_permission (id, name, enname, description, created, updated) VALUES (39, '编辑用户', 'OP_UPDATE_USER', null, '2019-04-04 15:30:32', '2019-04-04 15:30:45');
INSERT INTO microservices.sys_permission (id, name, enname, description, created, updated) VALUES (40, '删除用户', 'OP_DELETE_USER', null, '2019-04-04 15:30:48', '2019-04-04 15:30:45');
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

INSERT INTO microservices.sys_role (id, name, enname, description, created, updated) VALUES (37, '超级管理员', 'ROLE_ADMIN', '超级管理员', '2019-04-04 23:22:03', '2019-04-04 23:22:05');
INSERT INTO microservices.sys_role (id, name, enname, description, created, updated) VALUES (38, '普通用户', 'ROLE_USER', '普通用户', '2020-04-05 18:05:14', '2020-04-05 18:05:18');
create table sys_role_permission
(
    id            bigint auto_increment
        primary key,
    role_id       bigint not null comment '角色 ID',
    permission_id bigint not null comment '权限 ID'
)
    comment '角色权限表' charset = utf8;

INSERT INTO microservices.sys_role_permission (id, role_id, permission_id) VALUES (37, 37, 37);
INSERT INTO microservices.sys_role_permission (id, role_id, permission_id) VALUES (38, 37, 38);
INSERT INTO microservices.sys_role_permission (id, role_id, permission_id) VALUES (39, 37, 39);
INSERT INTO microservices.sys_role_permission (id, role_id, permission_id) VALUES (40, 37, 40);
INSERT INTO microservices.sys_role_permission (id, role_id, permission_id) VALUES (43, 38, 37);
INSERT INTO microservices.sys_role_permission (id, role_id, permission_id) VALUES (44, 38, 39);
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

INSERT INTO microservices.sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (37, 'admin', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO microservices.sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (38, 'alan', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO microservices.sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (39, 'alan1', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO microservices.sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (40, 'alan2', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO microservices.sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (41, 'alan3', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO microservices.sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (42, 'alan4', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO microservices.sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (43, 'alan5', '$2a$10$YXZ5Lp0AFqznBzDaFYjJHu52TBeGkZ2YJwI5V2CmTnIkuz9lQ/AAO', '18301901303', '438162498@qq.com', '2020-03-27 12:15:36', '2020-03-27 12:15:36', null, '华东理工大学');
INSERT INTO microservices.sys_user (id, username, password, phone, email, created, updated, avatar, organization) VALUES (44, 'user', '$2a$10$V58b.Gh0RiikLCVVMMdnD.X37DL6K/ww0Sxlq8lhOw1vVNKKfzaji', null, null, '2020-04-06 17:23:25', '2020-04-06 17:23:25', null, null);
create table sys_user_role
(
    id      bigint auto_increment
        primary key,
    user_id bigint not null comment '用户 ID',
    role_id bigint not null comment '角色 ID'
)
    comment '用户角色表' charset = utf8;

INSERT INTO microservices.sys_user_role (id, user_id, role_id) VALUES (37, 37, 37);
INSERT INTO microservices.sys_user_role (id, user_id, role_id) VALUES (38, 38, 38);