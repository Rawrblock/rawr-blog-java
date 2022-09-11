ALTER TABLE user
    ADD COLUMN `avatar_id` VARCHAR(32) NULL COMMENT '头像文件' AFTER `password`,
        ADD CONSTRAINT `c_user_avatar_id`
        FOREIGN KEY (`avatar_id`) REFERENCES `file`(`id`);