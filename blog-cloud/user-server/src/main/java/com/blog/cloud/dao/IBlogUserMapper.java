package com.blog.cloud.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.blog.cloud.pojo.user.BlogUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IBlogUserMapper extends BaseMapper<BlogUser> {
}
