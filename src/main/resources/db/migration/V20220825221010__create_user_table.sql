CREATE TABLE user
(
    `id`             VARCHAR(32) NOT NULL PRIMARY KEY COMMENT '用户ID',
    `name`           VARCHAR(64) NOT NULL COMMENT '用户姓名',
    `email`          VARCHAR(64) NULL COMMENT '用户邮箱',
    `password`       VARCHAR(64) NOT NULL COMMENT '加密后的密码',
    `locked`         tinyint(1) DEFAULT 0 NOT NULL COMMENT '是否锁定, 1-是, 0-否',
    `enabled_symbol` tinyint(1) DEFAULT 1 NOT NULL COMMENT '是否可用, 1-是, 0-否',
    `created_time`   datetime(6) NOT NULL COMMENT '创建时间',
    `updated_time`   datetime(6) NOT NULL COMMENT '更新时间',
    CONSTRAINT uk_user_email
        UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户表';

