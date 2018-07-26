DROP TABLE `blog_article`;
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
