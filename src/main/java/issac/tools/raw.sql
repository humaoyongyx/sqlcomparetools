CREATE TABLE `t_menu_version` (
                                  `id` bigint NOT NULL COMMENT '主键',
                                  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '备份名称',
                                  `type` int NOT NULL DEFAULT '0' COMMENT '备份类型:  0、手动备份 1、自动备份的',
                                  `create_time` datetime NOT NULL COMMENT '创建时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单备份表';