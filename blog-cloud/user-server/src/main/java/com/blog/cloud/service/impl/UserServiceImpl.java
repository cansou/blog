package com.blog.cloud.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.blog.cloud.dao.IBlogUserMapper;
import com.blog.cloud.domain.user.BlogUserAddDto;
import com.blog.cloud.pojo.user.BlogUser;
import com.blog.cloud.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<IBlogUserMapper, BlogUser> implements IUserService {

    @Override
    public Integer registerUser(BlogUserAddDto userAddDto) {
        Long currentTime = System.currentTimeMillis();
        BlogUser blogUser = new BlogUser();
        BeanUtils.copyProperties(userAddDto, blogUser);
        blogUser.setSalt("afdasd");
        blogUser.setCreateTime(currentTime);
        blogUser.setUpdateTime(currentTime);
        blogUser.setUserStatus(1);
        return baseMapper.insert(blogUser);
    }

}
