DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`              bigint(20)   auto_increment COMMENT '用户id',
    `nickname`        varchar(255) NOT NULL COMMENT '用户昵称',
    `password`        varchar(32)  DEFAULT NULL COMMENT 'MD5(MD5(password+salt)+salt)',
    `salt`            varchar(10)  DEFAULT NULL COMMENT '盐',
    `head`            varchar(128) DEFAULT NULL COMMENT '头像, 云存储id',
    `register_date`   datetime     DEFAULT NULL COMMENT '注册时间',
    `last_login_date` datetime     DEFAULT NULL COMMENT '上次登录时间',
    `login_count`     int(11)      DEFAULT '0' COMMENT '总登录次数',
    PRIMARY KEY (`id`),
    unique key idx_nickname(`nickname`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_general_ci;