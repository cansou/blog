DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`(
  `id` VARCHAR(64) NOT NUll COMMENT '文章ID',
  `user_id` VARCHAR(64) NOT NULL COMMENT '用户ID',
  `title` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '文章标题',
  `text` TEXT COMMENT '正文',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `article_status` TINYINT NOT NULL COMMENT '文章状态（0 未发布 1 发布）',
  `del_status` TINYINT NOT NULL COMMENT '删除状态（0 未删除 1 删除）',
  PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='博客文章表';

DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user`(
  `id` VARCHAR(64) NOT NUll COMMENT '文章ID',
  `nickname` VARCHAR(64) NOT NULL COMMENT '用户昵称',
  `username` VARCHAR(64) NOT NULL COMMENT '用户名（电子邮箱）',
  `password` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '盐值',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `activated` TINYINT NOT NULL DEFAULT 0 COMMENT '激活状态（0 未激活 1 激活）',
  `user_status` TINYINT NOT NULL DEFAULT 1 COMMENT '用户状态（0 禁用 1 启用）',
  PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='博客用户表';

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
	`token_id` VARCHAR (256) NOT NULL DEFAULT '',
	`token` TEXT,
	`authentication_id` VARCHAR (250) NOT NULL,
	`user_name` VARCHAR (256) NOT NULL DEFAULT '',
	`client_id` VARCHAR (256) NOT NULL DEFAULT '',
	`authentication` TEXT,
	`refresh_token` VARCHAR (256) NOT NULL DEFAULT '',
	PRIMARY KEY (`authentication_id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = 'oauth_access_token';

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
	`client_id` VARCHAR (250) NOT NULL,
	`resource_ids` VARCHAR (256) NOT NULL DEFAULT '',
	`client_secret` VARCHAR (256) NOT NULL DEFAULT '',
	`scope` VARCHAR (256) NOT NULL DEFAULT '',
	`authorized_grant_types` VARCHAR (256) NOT NULL DEFAULT '',
	`web_server_redirect_uri` VARCHAR (256) NOT NULL DEFAULT '',
	`authorities` VARCHAR (256) NOT NULL DEFAULT '',
	`access_token_validity` INT (11) NULL DEFAULT NULL,
	`refresh_token_validity` INT (11) NULL DEFAULT NULL,
	`additional_information` VARCHAR (4096) NOT NULL DEFAULT '',
	`autoapprove` VARCHAR (256) NOT NULL DEFAULT '',
	PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = 'oauth_client_details';

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
	`token_id` VARCHAR (256) NOT NULL DEFAULT '',
	`token` TEXT,
	`authentication` TEXT
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = 'oauth_refresh_token';