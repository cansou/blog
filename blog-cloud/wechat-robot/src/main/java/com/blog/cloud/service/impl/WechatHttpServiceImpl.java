package com.blog.cloud.service.impl;

import com.blog.cloud.config.CacheConfiguration;
import com.blog.cloud.config.WechatApiServiceInternal;
import com.blog.cloud.domain.response.SendMsgResponse;
import com.blog.cloud.domain.shared.Contact;
import com.blog.cloud.service.WechatHttpService;
import com.blog.cloud.utils.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class WechatHttpServiceImpl implements WechatHttpService {

    @Autowired
    private WechatApiServiceInternal internal;

    @Autowired
    private CacheConfiguration cacheConfiguration;

    @Override
    public void logout() {
        internal.logout(cacheConfiguration.getHostUrl(), cacheConfiguration.getSKey());
    }

    @Override
    public Set<Contact> getContact() {
        return null;
    }

    @Override
    public void sendText(String userName, String content) {
        notifyNecessary(userName);
        SendMsgResponse response = internal.sendText(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), content, cacheConfiguration.getOwner().getUserName(), userName);
        WechatUtils.checkBaseResponse(response);
    }

    @Override
    public void setAlias(String userName, String newAlias) {

    }

    @Override
    public Set<Contact> batchGetContact(Set<String> list) {
        return null;
    }

    @Override
    public void createChatRoom(List<String> userNames, String topic) {

    }

    @Override
    public void deleteChatRoomMember(String chatRoomUserName, String userName) {

    }

    @Override
    public void addChatRoomMember(String chatRoomUserName, String userName) {

    }

    @Override
    public byte[] downloadImage(String url) {
        return new byte[0];
    }

    @Override
    public void notifyNecessary(String userName) {

    }
}
