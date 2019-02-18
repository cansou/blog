DROP TABLE IF EXISTS `wechat_robot_user`;
CREATE TABLE `wechat_robot_user`(
  `id` VARCHAR(64) NOT NUll COMMENT '用户ID',
  `nickname` VARCHAR(255) NOT NULL COMMENT '用户昵称',
  `username` VARCHAR(255) NOT NULL COMMENT '用户名（电子邮箱）',
  `uni` BIGINT NOT NULL DEFAULT 0 COMMENT 'uni',
  `sex` TINYINT COMMENT '性别',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `user_status` TINYINT NOT NULL DEFAULT 1 COMMENT '用户状态（0 禁用 1 启用）',
  `signature` VARCHAR(64) NOT NULL COMMENT '签名',
  PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='微信机器人用户实体类';

DROP TABLE IF EXISTS `wechat_robot_friend_user`;
CREATE TABLE `wechat_robot_friend_user`(
  `id` VARCHAR(64) NOT NUll COMMENT '用户ID',
  `belong_uni` BIGINT NOT NULL DEFAULT 0 COMMENT 'belong_uni',
  `nickname` VARCHAR(255) NOT NULL COMMENT '用户昵称',
  `username` VARCHAR(255) NOT NULL COMMENT '用户名（电子邮箱）',
  `sex` TINYINT COMMENT '性别',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `signature` VARCHAR(64) NOT NULL COMMENT '签名',
  PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='微信机器人用户好友表';

