package com.blog.cloud.service.impl;

import com.blog.cloud.config.CacheConfiguration;
import com.blog.cloud.config.WechatApiServiceInternal;
import com.blog.cloud.domain.response.*;
import com.blog.cloud.domain.shared.ChatRoomDescription;
import com.blog.cloud.domain.shared.Contact;
import com.blog.cloud.enums.StatusNotifyCode;
import com.blog.cloud.service.WechatHttpService;
import com.blog.cloud.utils.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        Set<Contact> contacts = new HashSet<>();
        long seq = 0;
        do {
            GetContactResponse response = internal.getContact(cacheConfiguration.getHostUrl(), cacheConfiguration.getSKey(), seq, cacheConfiguration.getPassTicket());
            WechatUtils.checkBaseResponse(response);
            seq = response.getSeq();
            contacts.addAll(response.getMemberList());
        }
        while (seq > 0);
        return contacts;
    }

    @Override
    public void sendText(String userName, String content) {
        notifyNecessary(userName);
        SendMsgResponse response = internal.sendText(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), content, cacheConfiguration.getOwner().getUserName(), userName);
        WechatUtils.checkBaseResponse(response);
    }

    @Override
    public void setAlias(String userName, String newAlias) {
        OpLogResponse response = internal.setAlias(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), newAlias, userName, cacheConfiguration.getToken());
        WechatUtils.checkBaseResponse(response);
    }

    @Override
    public Set<Contact> batchGetContact(Set<String> list) {
        ChatRoomDescription[] descriptions =
                list.stream().map(x -> {
                    ChatRoomDescription description = new ChatRoomDescription();
                    description.setUserName(x);
                    return description;
                }).toArray(ChatRoomDescription[]::new);
        BatchGetContactResponse response = internal.batchGetContact(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), descriptions, cacheConfiguration.getToken());
        WechatUtils.checkBaseResponse(response);
        return response.getContactList();
    }

    @Override
    public void createChatRoom(List<String> userNames, String topic) {
        CreateChatRoomResponse response = internal.createChatRoom(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), userNames, topic, cacheConfiguration.getToken());
        WechatUtils.checkBaseResponse(response);
        //invoke BatchGetContact after CreateChatRoom
        ChatRoomDescription description = new ChatRoomDescription();
        description.setUserName(response.getChatRoomName());
        ChatRoomDescription[] descriptions = new ChatRoomDescription[]{description};
        BatchGetContactResponse batchGetContactResponse = internal.batchGetContact(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), descriptions, cacheConfiguration.getToken());
        WechatUtils.checkBaseResponse(batchGetContactResponse);
        cacheConfiguration.getChatRooms().addAll(batchGetContactResponse.getContactList());
    }

    @Override
    public void deleteChatRoomMember(String chatRoomUserName, String userName) {
        DeleteChatRoomMemberResponse response = internal.deleteChatRoomMember(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), chatRoomUserName, userName, cacheConfiguration.getToken());
        WechatUtils.checkBaseResponse(response);
    }

    @Override
    public void addChatRoomMember(String chatRoomUserName, String userName) {
        AddChatRoomMemberResponse response = internal.addChatRoomMember(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), chatRoomUserName, userName, cacheConfiguration.getToken());
        WechatUtils.checkBaseResponse(response);
        Contact chatRoom = cacheConfiguration.getChatRooms().stream().filter(x -> chatRoomUserName.equals(x.getUserName())).findFirst().orElse(null);
        if (chatRoom == null) {
            throw new RuntimeException("can't find " + chatRoomUserName);
        }
        chatRoom.getMemberList().addAll(response.getMemberList());
    }

    @Override
    public byte[] downloadImage(String url) {
        return internal.downloadImage(url);
    }

    @Override
    public void notifyNecessary(String userName) {
        if (userName == null) {
            throw new IllegalArgumentException("userName");
        }
        Set<String> unreadContacts = cacheConfiguration.getContactNamesWithUnreadMessage();
        if (unreadContacts.contains(userName)) {
            internal.statusNotify(cacheConfiguration.getHostUrl(), cacheConfiguration.getBaseRequest(), userName, StatusNotifyCode.READED.getCode(), cacheConfiguration.getPassTicket());
            unreadContacts.remove(userName);
        }
    }
}
