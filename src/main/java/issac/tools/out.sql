ALTER TABLE `t_project` ADD COLUMN `new_save_path` varchar(500) DEFAULT NULL COMMENT '新的文件保存路径';
ALTER TABLE `t_filter` MODIFY COLUMN `filter_type` int DEFAULT '1' COMMENT '模板类型';
ALTER TABLE `t_project_link` MODIFY COLUMN `main_project_id` bigint NOT NULL COMMENT '主项目id版本管理中的最开始的项目id';
ALTER TABLE `t_project_link_history` MODIFY COLUMN `copy_from` bigint DEFAULT NULL COMMENT '复制项目id来源';
