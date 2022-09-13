ALTER TABLE blog
    ADD COLUMN `cover_id` VARCHAR(32) NULL COMMENT '博客封面文件' AFTER `title`,
        ADD CONSTRAINT `c_blog_cover_id`
        FOREIGN KEY (`cover_id`) REFERENCES `file`(`id`);