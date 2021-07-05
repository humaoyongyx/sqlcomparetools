CREATE TABLE `t_project_link` (
 `id` bigint NOT NULL COMMENT '项目链接id',
 `project_id` bigint DEFAULT NULL COMMENT '项目id',
 `share_link` bigint DEFAULT NULL COMMENT '分享连接地址',
 `enable` tinyint DEFAULT '0' COMMENT '是否有效,1-有效, 0-无效',
 `user_id` char(30) NOT NULL COMMENT '创建人id，值来源于认证中心系统',
 `create_time` datetime NOT NULL COMMENT '创建时间',
 PRIMARY KEY (`id`)
) COMMENT='项目链接';
CREATE TABLE `t_component_config` (
 `id` bigint NOT NULL COMMENT '主键id',
 `project_id` bigint DEFAULT NULL COMMENT '项目id',
 `scope` varchar(10) DEFAULT 'project' COMMENT '配置文件scope范围。global：全局配置,project:项目级别配置，默认级别。',
 `category_code` varchar(255) NOT NULL COMMENT '配置分类编码',
 `category_name` varchar(255) NOT NULL COMMENT '配置分类名称',
 `component_level` varchar(255) DEFAULT NULL COMMENT '配置标签枚举\r\nspeciality：专业\r\nscene：场景\r\ncomponentType：构建类型\r\nspecs:规格',
 `tag` varchar(255) DEFAULT NULL COMMENT '标签',
 `code` varchar(50) DEFAULT NULL COMMENT '编码',
 `name` varchar(100) DEFAULT NULL COMMENT '名称',
 `detail` json DEFAULT NULL COMMENT '详细内容',
 `ref_id` bigint DEFAULT NULL COMMENT '关联code',
 `business_type` varchar(100) DEFAULT NULL COMMENT '业务类型或子配置类型',
 `create_by` varchar(20) DEFAULT NULL COMMENT '创建人',
 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 `update_by` varchar(20) DEFAULT NULL COMMENT '更新人',
 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`)
) ;
CREATE TABLE `t_project_link_import` (
 `id` bigint NOT NULL COMMENT '项目链接导入id',
 `project_id` bigint DEFAULT NULL COMMENT '当前项目id',
 `link_project_id` bigint DEFAULT NULL COMMENT '链接项目id,复制的项目',
 `origin_project_id` bigint DEFAULT NULL COMMENT '源项目id,即从哪个项目分享出来的',
 `origin_project_modify_time` datetime NOT NULL COMMENT '源项目最后更新时间，值来源于t_project.last_modify_time',
 `visible` tinyint DEFAULT '0' COMMENT '可视化(0-不可视;1-可视)',
 `enable` tinyint DEFAULT '0' COMMENT '数据是否有效,1-有效, 0-无效',
 `type` tinyint DEFAULT NULL COMMENT '类型，1-同主体模型 2-自定义',
 `create_time` datetime NOT NULL COMMENT '创建时间',
 `modify_time` datetime NOT NULL COMMENT '更新时间',
 `remark` varchar(200) DEFAULT NULL COMMENT '包含：更新失效/删除失效',
 `update_status` tinyint DEFAULT NULL COMMENT '模型更新状态，0-未更新；1-更新中；2-更新完成',
 PRIMARY KEY (`id`)
) COMMENT='项目链接导入-链接模型导入';
CREATE TABLE `t_test` (
 `f` varchar(255) DEFAULT NULL
) ;
CREATE TABLE `t_dictionnary_copy1` (
 `type` varchar(100) NOT NULL COMMENT '类型或分组',
 `dict_key` varchar(100) NOT NULL COMMENT '键key',
 `dict_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '键值-数字、文本、json串',
 `data_type` varchar(255) DEFAULT '' COMMENT '数据类型,文本、json串，默认值为空表示文本',
 `dict_desc` varchar(255) DEFAULT NULL COMMENT '描述',
 `product_code` varchar(100) DEFAULT '' COMMENT '产品编号,默认值为空，表示公共字典数据',
 `deleted` tinyint DEFAULT '0' COMMENT '删除状态,1: 删除 0：未删除',
 `create_time` datetime NOT NULL COMMENT '创建时间',
 PRIMARY KEY (`type`,`dict_key`) 
) COMMENT='字典表\r\n1.用于存储整个系统的公共字典数据\r\n2.用于存储不同产品的特殊字典数据';
CREATE TABLE `t_component_type_bak` (
 `code` varchar(100) NOT NULL COMMENT '类型编号',
 `name` varchar(100) NOT NULL COMMENT '类型名称',
 `icon` varchar(100) DEFAULT NULL COMMENT 'iconfont-class的值',
 `command` varchar(200) DEFAULT NULL COMMENT '命令值',
 `sort` int DEFAULT NULL COMMENT '排序',
 `file_path` varchar(255) DEFAULT NULL COMMENT 'ics文件路径',
 `deleted` tinyint DEFAULT '0' COMMENT '删除状态,1: 删除 0：未删除',
 `create_time` datetime NOT NULL COMMENT '创建时间'
) ;
CREATE TABLE `t_menu_20210407` (
 `id` varchar(32) NOT NULL,
 `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
 `sort` int DEFAULT NULL COMMENT '排序',
 `parent_id` varchar(32) NOT NULL,
 `product_id` bigint DEFAULT NULL COMMENT '产品id',
 `icon` varchar(100) DEFAULT NULL COMMENT '功能启用icon',
 `dis_icon` varchar(100) DEFAULT NULL COMMENT '功能禁用icon',
 `command_type` int DEFAULT '0' COMMENT '消息类型,默认值为0',
 `command_id` int DEFAULT NULL COMMENT '命令id',
 `params` varchar(100) DEFAULT NULL COMMENT '命令id对应的参数值',
 `menu_layout` varchar(100) DEFAULT NULL COMMENT '左边菜单menus;头部菜单topBar;右下菜单bottomBarR;左下菜单bottomBarL',
 `hint` varchar(200) DEFAULT NULL COMMENT 'hover提示内容',
 `show_name` tinyint DEFAULT NULL COMMENT '名字是否显示',
 `show_icon` tinyint DEFAULT NULL COMMENT 'icon是否显示',
 `layout` tinyint DEFAULT NULL COMMENT '没有特殊布局，默认值为0 ;1代表下拉框',
 `deleted` tinyint DEFAULT '0' COMMENT '删除状态,1: 删除 0：未删除',
 `create_time` datetime NOT NULL COMMENT '创建时间',
 `hidden` tinyint DEFAULT NULL COMMENT '默认隐藏',
 `tag` varchar(255) DEFAULT NULL COMMENT '标签',
 `tool_key` varchar(255) DEFAULT NULL COMMENT 'toolkey',
 `weight` int DEFAULT NULL COMMENT '布局权重;同一组中权重最高的排在最前面',
 `phase` varchar(50) DEFAULT '' COMMENT '阶段',
 `tool_belong` text COMMENT '所属Tool',
 `default_visible` tinyint DEFAULT '1' COMMENT '显示默认',
 `closely_visible` text COMMENT '显示亲近',
 `reject_visible` text COMMENT '显示排斥',
 `select_closely_visible` text COMMENT '显示选择亲近',
 `select_reject_visible` text COMMENT '显示选择排斥',
 `default_disabled` tinyint DEFAULT '0' COMMENT '禁用默认',
 `closely_disabled` text COMMENT '禁用亲近',
 `reject_disabled` text COMMENT '禁用排斥',
 `select_reject_disabled` text COMMENT '禁用选择排斥',
 `select_closely_activated` text COMMENT '激活选择亲近'
) ;
CREATE TABLE `t_component_copy1` (
 `id` bigint NOT NULL COMMENT '构件id',
 `product_code` varchar(100) DEFAULT NULL COMMENT '产品编号,构件所属产品',
 `speciality_code` varchar(100) DEFAULT NULL COMMENT '专业编号',
 `scene_code` varchar(100) DEFAULT NULL COMMENT '场景编号',
 `component_type_code` varchar(100) DEFAULT NULL COMMENT '构件类型编号',
 `name` varchar(255) NOT NULL COMMENT '构件名称',
 `save_path` varchar(500) DEFAULT NULL COMMENT '文件保存路径',
 `user_id` char(30) NOT NULL COMMENT '创建人id，值来源于认证中心系统',
 `user_name` varchar(100) DEFAULT NULL COMMENT '登录的用户名，值来源于认证系统',
 `deleted` tinyint DEFAULT '0' COMMENT '删除状态,1: 删除 0：未删除',
 `create_time` datetime NOT NULL COMMENT '创建时间',
 `last_modify_by` char(30) DEFAULT NULL COMMENT '最后修改人',
 `last_modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
 `file_id` bigint DEFAULT NULL COMMENT '文件id',
 `convert_status` int DEFAULT NULL COMMENT '文件转换状态,1-待转换; 2-消息已发送; 3-开始转换；4-转换完成；5-转换失败',
 `data_source` varchar(10) DEFAULT NULL COMMENT '数据来源;create-创建; import-导入',
 PRIMARY KEY (`id`) ,
 KEY `user_id_index` (`user_id`) 
) COMMENT='构件表';
ALTER TABLE `t_white_list` MODIFY COLUMN `user_name` varchar(100) NOT NULL COMMENT '登录的用户名，值同认证系统一致';
ALTER TABLE `t_tag_tree` MODIFY COLUMN `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE `t_tag_tree` MODIFY COLUMN `name` varchar(255) NOT NULL COMMENT '标签树名称';
ALTER TABLE `t_shape_floor` MODIFY COLUMN `floor_name` varchar(100) DEFAULT NULL COMMENT '楼层名称';
ALTER TABLE `t_space_assembly_data` MODIFY COLUMN `component_style_id` varchar(32) NOT NULL COMMENT '构建样式ID';
ALTER TABLE `t_space_assembly_data` MODIFY COLUMN `do_serialno` varchar(100) DEFAULT NULL COMMENT '做法编号';
ALTER TABLE `t_space_assembly_data` MODIFY COLUMN `do_location_name` varchar(200) DEFAULT NULL COMMENT '做法位置名称';
ALTER TABLE `t_space_assembly_data` MODIFY COLUMN `component_style_name` varchar(200) DEFAULT NULL COMMENT '构建样式名称（做法名称+龙骨）';
ALTER TABLE `t_space_assembly_data` MODIFY COLUMN `project_type_name` varchar(200) DEFAULT NULL COMMENT '工程类别名称';
ALTER TABLE `t_space_assembly_data` MODIFY COLUMN `restraint_mode_name` varchar(200) DEFAULT NULL COMMENT '约束方式名称';
ALTER TABLE `t_space_assembly_data` MODIFY COLUMN `burning_capability` varchar(100) DEFAULT NULL COMMENT '燃烧性能';
ALTER TABLE `t_tag_object` MODIFY COLUMN `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE `t_tag_object` MODIFY COLUMN `shape_type` varchar(50) DEFAULT 'Unknown' COMMENT 'shape类型 未知-Unknown； 建筑-building；楼层-floor；区域房间-regionRoom；区域非房间-regionNoRoom';
ALTER TABLE `t_tag_category_color` MODIFY COLUMN `color_value` varchar(100) DEFAULT NULL COMMENT '颜色值hsb色值，逗号分隔开';
ALTER TABLE `t_tag_category_color` MODIFY COLUMN `color_type` varchar(255) DEFAULT NULL COMMENT '颜色类型 hsbrgb';
ALTER TABLE `t_tag_category_color` MODIFY COLUMN `color_hex_value` varchar(100) DEFAULT NULL COMMENT '颜色16进制值';
ALTER TABLE `t_duct_sys` MODIFY COLUMN `id` bigint NOT NULL COMMENT '主键';
ALTER TABLE `t_apply_probation` MODIFY COLUMN `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话';
ALTER TABLE `t_apply_probation` MODIFY COLUMN `user_name` varchar(100) NOT NULL COMMENT '登录的用户名，值同认证系统一致';
ALTER TABLE `t_apply_probation` MODIFY COLUMN `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户姓名';
ALTER TABLE `t_apply_probation` MODIFY COLUMN `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱';
ALTER TABLE `t_shape_building` MODIFY COLUMN `building_name` varchar(100) DEFAULT NULL COMMENT '建筑名字';
ALTER TABLE `t_tag_tree_config` MODIFY COLUMN `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE `t_space_assembly_room_details` MODIFY COLUMN `component_style_id` varchar(32) DEFAULT NULL COMMENT '构建样式';
ALTER TABLE `t_space_assembly_room_details` MODIFY COLUMN `do_location_name` varchar(200) DEFAULT NULL COMMENT '做法位置名称';
ALTER TABLE `t_space_assembly_room_details` MODIFY COLUMN `burning_capability` varchar(100) DEFAULT NULL COMMENT '燃烧性能';
ALTER TABLE `t_space_assembly_room_details` MODIFY COLUMN `do_serialno` varchar(100) DEFAULT NULL COMMENT '做法编号';
ALTER TABLE `t_space_assembly_room_details` MODIFY COLUMN `component_style_name` varchar(200) DEFAULT NULL COMMENT '构建样式名称（做法名称+龙骨）';
ALTER TABLE `t_component_relation` MODIFY COLUMN `value` varchar(100) NOT NULL COMMENT '专业/场景/构件类型的编号值';
ALTER TABLE `t_component_relation` MODIFY COLUMN `type` varchar(50) NOT NULL COMMENT '类型：专业-zy/ 场景-scene/ 构件类型-cType';
ALTER TABLE `t_tenant` MODIFY COLUMN `phone` varchar(50) DEFAULT NULL COMMENT '联系电话';
ALTER TABLE `t_tenant` MODIFY COLUMN `system_no` varchar(100) DEFAULT NULL COMMENT '系统编号';
ALTER TABLE `t_tenant` MODIFY COLUMN `contact` varchar(50) DEFAULT NULL COMMENT '联系人';
ALTER TABLE `t_tenant` MODIFY COLUMN `app_secret_salt` varchar(100) NOT NULL COMMENT 'Salt';
ALTER TABLE `t_tenant` MODIFY COLUMN `app_secret` varchar(100) NOT NULL COMMENT 'AppSecret';
ALTER TABLE `t_tenant` MODIFY COLUMN `id` varchar(50) NOT NULL COMMENT '租户ID';
ALTER TABLE `t_tenant` MODIFY COLUMN `name` varchar(100) NOT NULL COMMENT '租户名称';
ALTER TABLE `t_tenant` MODIFY COLUMN `server_url` varchar(100) DEFAULT NULL COMMENT '服务地址';
ALTER TABLE `t_tenant` MODIFY COLUMN `system_name` varchar(100) DEFAULT NULL COMMENT '系统名';
ALTER TABLE `t_tenant` MODIFY COLUMN `app_key` varchar(100) NOT NULL COMMENT 'AppKey';
ALTER TABLE `t_duct_category` MODIFY COLUMN `id` bigint NOT NULL COMMENT '主键';
ALTER TABLE `t_file` MODIFY COLUMN `thumb_path` varchar(255) DEFAULT NULL COMMENT '文件缩略图保存路径';
ALTER TABLE `t_file` MODIFY COLUMN `server_path` varchar(500) DEFAULT NULL COMMENT '服务器端文件保存路径';
ALTER TABLE `t_file` MODIFY COLUMN `ext` varchar(50) DEFAULT NULL COMMENT '文件扩展名';
ALTER TABLE `t_file` MODIFY COLUMN `name` varchar(150) DEFAULT NULL COMMENT '原文件名';
ALTER TABLE `t_file` MODIFY COLUMN `target_path` varchar(255) DEFAULT NULL COMMENT '目标文件路径，转换后的ics文件存放路径';
ALTER TABLE `t_file` MODIFY COLUMN `convert_log_path` varchar(500) DEFAULT NULL COMMENT '文件转换日志路径';
ALTER TABLE `t_file` MODIFY COLUMN `client_path` varchar(500) DEFAULT NULL COMMENT '客户端文件保存路径';
ALTER TABLE `t_file` MODIFY COLUMN `convert_fail_reason` varchar(500) DEFAULT NULL COMMENT '文件转换失败原因';
ALTER TABLE `t_project` MODIFY COLUMN `render_item_version_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渲染项目模型版本id';
ALTER TABLE `t_project` MODIFY COLUMN `render_project_id` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '渲染项目id';
ALTER TABLE `t_space_assembly_tmpl` MODIFY COLUMN `component_style_id` varchar(32) DEFAULT NULL COMMENT '构建样式';
ALTER TABLE `t_space_assembly_tmpl` MODIFY COLUMN `tag_name` varchar(100) DEFAULT NULL COMMENT '标签名称';
ALTER TABLE `t_space_assembly_tmpl` MODIFY COLUMN `do_location_name` varchar(200) DEFAULT NULL COMMENT '做法位置名称';
ALTER TABLE `t_space_assembly_tmpl` MODIFY COLUMN `color_value` varchar(32) DEFAULT NULL COMMENT '标签颜色';
ALTER TABLE `t_space_assembly_tmpl` MODIFY COLUMN `burning_capability` varchar(100) DEFAULT NULL COMMENT '燃烧性能';
ALTER TABLE `t_space_assembly_tmpl` MODIFY COLUMN `do_serialno` varchar(100) DEFAULT NULL COMMENT '做法编号';
ALTER TABLE `t_space_assembly_tmpl` MODIFY COLUMN `component_style_name` varchar(200) DEFAULT NULL COMMENT '构建样式名称（做法名称+龙骨）';
ALTER TABLE `t_tenant_token_apply` MODIFY COLUMN `token` varchar(100) NOT NULL COMMENT 'Token';
ALTER TABLE `t_tenant_token_apply` MODIFY COLUMN `nick_name` varchar(100) DEFAULT NULL COMMENT '用户名';
ALTER TABLE `t_tenant_token_apply` MODIFY COLUMN `user_name` varchar(100) NOT NULL COMMENT '用户账号';
ALTER TABLE `t_tenant_token_apply` MODIFY COLUMN `tenant_id` varchar(50) NOT NULL COMMENT '租户ID';
ALTER TABLE `t_tenant_token_apply` MODIFY COLUMN `user_id` varchar(100) NOT NULL COMMENT '用户ID';
ALTER TABLE `t_tenant_token_apply` MODIFY COLUMN `app_key` varchar(100) NOT NULL COMMENT 'AppKey';
ALTER TABLE `t_duct_sys_props` MODIFY COLUMN `recommend_colors_hex` text COMMENT '推荐颜色，十六进制。以逗号分割。';
ALTER TABLE `t_duct_sys_props` MODIFY COLUMN `id` bigint NOT NULL COMMENT '主键';
ALTER TABLE `t_duct_sys_props` MODIFY COLUMN `recommend_colors_rgb` text COMMENT '推荐颜色，rgb格式。以逗号分割。';
ALTER TABLE `t_tag_category` MODIFY COLUMN `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE `t_tag_category` MODIFY COLUMN `name` varchar(255) NOT NULL COMMENT '类别名称';
ALTER TABLE `t_tag_category` MODIFY COLUMN `color_value` varchar(100) DEFAULT NULL COMMENT '颜色值hsb色值，逗号分隔开';
ALTER TABLE `t_tag_category` MODIFY COLUMN `color_type` varchar(255) DEFAULT NULL COMMENT '颜色类型 hsbrgb';
ALTER TABLE `t_tag_category` MODIFY COLUMN `color_hex_value` varchar(100) DEFAULT NULL COMMENT '颜色16进制值';
ALTER TABLE `t_tag` MODIFY COLUMN `create_by` varchar(50) DEFAULT NULL COMMENT '创建人id';
ALTER TABLE `t_tag` MODIFY COLUMN `name` varchar(255) NOT NULL COMMENT '标签名称';
ALTER TABLE `t_tag` MODIFY COLUMN `color_value` varchar(100) DEFAULT NULL COMMENT '颜色值hsb色值，逗号分隔开';
ALTER TABLE `t_tag` MODIFY COLUMN `color_type` varchar(255) DEFAULT NULL COMMENT '颜色类型 hsbrgb';
ALTER TABLE `t_tag` MODIFY COLUMN `color_hex_value` varchar(100) DEFAULT NULL COMMENT '颜色16进制值';
ALTER TABLE `t_shape_region` MODIFY COLUMN `tag_name` varchar(100) DEFAULT NULL COMMENT '标签名称';
ALTER TABLE `t_shape_region` MODIFY COLUMN `color` varchar(50) DEFAULT NULL COMMENT '颜色';
ALTER TABLE `t_shape_region` MODIFY COLUMN `color_value` varchar(32) DEFAULT NULL COMMENT '标签颜色';
ALTER TABLE `t_shape_region` MODIFY COLUMN `pivot` varchar(50) DEFAULT NULL COMMENT '名字显示位置';
ALTER TABLE `t_shape_region` MODIFY COLUMN `name` varchar(100) DEFAULT NULL COMMENT '区域名字';
