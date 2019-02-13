package com.blog.cloud.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.blog.cloud.common.BaseReqDto;
import com.blog.cloud.dao.IWechatRobotUserMapper;
import com.blog.cloud.domain.robot.WechatRobotUserQueryDto;
import com.blog.cloud.domain.shared.Owner;
import com.blog.cloud.pojo.robot.WechatRobotUser;
import com.blog.cloud.service.IWechatRobotUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("wechatRobotUserService")
public class WechatRobotUserServiceImpl extends ServiceImpl<IWechatRobotUserMapper, WechatRobotUser> implements IWechatRobotUserService {

    @Override
    public List<WechatRobotUser> listAllWechatRobotUser() {
        return baseMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public List<WechatRobotUser> listWechatRobotUser(BaseReqDto<WechatRobotUserQueryDto> reqDto) {
        WechatRobotUserQueryDto dto = reqDto.getDto();
        Integer offset = reqDto.getOffset();
        Integer limit = reqDto.getLimit();

        RowBounds rowBounds = new RowBounds(offset, limit);
        EntityWrapper<WechatRobotUser> wrapper = new EntityWrapper<>();
        if (dto != null) {
            wrapper.like(StringUtils.isNotBlank(dto.getNickname()), "nickname", dto.getNickname());
        }
        return baseMapper.selectPage(rowBounds, wrapper);
    }

    @Override
    public Integer insertWechatRobotUser(Owner user) {
        WechatRobotUser userw = new WechatRobotUser();
        long currentTimeMillis = System.currentTimeMillis();
        userw.setUni(user.getUin());
        WechatRobotUser wechatRobotUser = baseMapper.selectOne(userw);
        WechatRobotUser entity = new WechatRobotUser();
        if (wechatRobotUser != null) {
            entity.setId(wechatRobotUser.getId());
            entity.setNickname(user.getNickName());
            entity.setUsername(user.getUserName());
            entity.setSex(user.getSex());
            entity.setSignature(user.getSignature());
            entity.setUpdateTime(currentTimeMillis);
            return baseMapper.updateById(entity);
        } else {
            entity.setNickname(user.getNickName());
            entity.setUsername(user.getUserName());
            entity.setSex(user.getSex());
            entity.setUni(user.getUin());
            entity.setSignature(user.getSignature());
            entity.setUpdateTime(currentTimeMillis);
            entity.setCreateTime(currentTimeMillis);
            entity.setUserStatus(1);
            return baseMapper.insert(entity);
        }
    }
}
