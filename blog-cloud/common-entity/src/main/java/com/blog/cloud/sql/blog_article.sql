CREATE TABLE blog_article(
  `id` INT NOT NUll AUTO_INCREMENT COMMENT '文章ID',
  `user_id` INT NOT NULL COMMENT '用户ID',
  `title` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '文章标题',
  `text` TEXT COMMENT '正文',
  `create_time` BIGINT NOT NULL COMMENT '创建时间',
  `update_time` BIGINT NOT NULL COMMENT '更新时间',
  `article_status` TINYINT NOT NULL COMMENT '文章状态（0 未发布 1 发布）',
  `del_status` TINYINT NOT NULL COMMENT '删除状态（0 未删除 1 删除）',
  PRIMARY KEY (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='博客文章表';
