INSERT INTO `user` (id, name, email, password, created_time, updated_time)
VALUES ('1', 'admin', '799389926@qq.com', 'admin', NOW(), NOW());

INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('1', 'ROLE_USER', '普通用户', '2022-07-24 14:41:12.260000', '2022-07-24 14:41:12.260000');

INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('2', 'ROLE_ADMIN', '管理员', '2022-07-24 14:41:12.260000', '2022-07-24 14:41:12.260000');

INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '1');

INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '2');