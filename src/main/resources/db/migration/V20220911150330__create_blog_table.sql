CREATE TABLE blog
(
    `id`           VARCHAR(32)                     NOT NULL PRIMARY KEY COMMENT '博客ID',
    `title`        VARCHAR(64)                     NOT NULL COMMENT '博客标题',
    `content`      TEXT                            NOT NULL COMMENT '博客内容',
    `status`       VARCHAR(32) DEFAULT 'PUBLISHED' NOT NULL COMMENT '博客状态, DRAFT-草稿;PUBLISHED-已上架;CLOSED-已下架',
    `created_time` datetime(6) NOT NULL COMMENT '创建时间',
    `updated_time` datetime(6) NOT NULL COMMENT '更新时间',
    CONSTRAINT bk_blog_title
        UNIQUE (title)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '博客表'