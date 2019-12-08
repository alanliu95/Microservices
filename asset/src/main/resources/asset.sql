# 表设计
use microservices;
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site`
(
    `id`        bigint(20) auto_increment COMMENT '场地id',
    `name`      varchar(256) NOT NULL COMMENT '场地名',
    `longitude` float DEFAULT NULL COMMENT '经度',
    `latitude`  float DEFAULT NULL COMMENT '维度',
    PRIMARY KEY (`id`),
    unique key idx_name (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

DROP TABLE IF EXISTS `device`;
CREATE TABLE `device`
(
    id        int          not null auto_increment,
    site_id   int          not null,
    name      varchar(256) NULL,
    token     varchar(256) not null unique,
    dev_type  int          null,
    longitude float DEFAULT NULL COMMENT '经度',
    latitude  float DEFAULT NULL COMMENT '维度',
    info      varchar(256) null,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

DROP TABLE IF EXISTS `dev_type`;
CREATE TABLE `dev_type`
(
    id          int          not null auto_increment,
    name        varchar(256) NULL,
    icon        varchar(256) null,
    data_struct varchar(256) null,
    info        varchar(256) null,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;

### 填充数据
insert into site(name, longitude, latitude)
values ('华理实验七楼', 31.146, 121.421);
insert into dev_type(name, icon, data_struct, info)
values ('戴尔工作站', null, null, '戴尔工作站')
insert into device(site_id, name, token, dev_type, longitude, latitude, info)
values (1, 'desktop-alan', '123456', 1, 31.146, 121.421, 'windows10工作站')