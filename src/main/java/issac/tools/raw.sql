CREATE TABLE `t_about_version` (
                                   `id` bigint NOT NULL COMMENT '主键id',
                                   `cpp_version_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'c++版本号',
                                   `web_version_no` int NOT NULL COMMENT 'web端版本号',
                                   `java_version_no` int NOT NULL COMMENT '后端版本号',
                                   `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `last_modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='关于版本表';

CREATE TABLE `t_about_version_record` (
                                          `id` bigint NOT NULL COMMENT '主键id',
                                          `about_version_id` bigint DEFAULT NULL COMMENT 't_about_version表主键',
                                          `item` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '关于内容条目',
                                          `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `last_modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                                          PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='关于版本表';