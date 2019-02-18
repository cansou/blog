package com.blog.cloud.service.impl;

import com.blog.cloud.config.WechatApiServiceInternal;
import com.blog.cloud.domain.response.*;
import com.blog.cloud.domain.shared.ChatRoomDescription;
import com.blog.cloud.domain.shared.Contact;
import com.blog.cloud.domain.shared.WechatRobotCache;
import com.blog.cloud.enums.StatusNotifyCode;
import com.blog.cloud.service.IWechatHttpService;
import com.blog.cloud.utils.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service("wechatHttpServic")
public class WechatHttpServiceImpl implements IWechatHttpService {

    @Autowired
    private WechatApiServiceInternal internal;

    @Override
    public void logout(WechatRobotCache cache) {
        internal.logout(cache.getHostUrl(), cache.getSKey());
    }

    @Override
    public Set<Contact> getContact(WechatRobotCache cache) {
        Set<Contact> contacts = new HashSet<>();
        long seq = 0;
        do {
            GetContactResponse response = internal.getContact(cache.getHostUrl(), cache.getSKey(), seq, cache.getPassTicket());
            WechatUtils.checkBaseResponse(response);
            seq = response.getSeq();
            contacts.addAll(response.getMemberList());
        }
        while (seq > 0);
        return contacts;
    }

    @Override
    public void sendText(String userName, String content, WechatRobotCache cache) {
        notifyNecessary(userName, cache);
        SendMsgResponse response = internal.sendText(cache.getHostUrl(), cache.getBaseRequest(), content, cache.getOwner().getUserName(), userName);
        WechatUtils.checkBaseResponse(response);
    }

    @Override
    public void setAlias(String userName, String newAlias, WechatRobotCache cache) {
        OpLogResponse response = internal.setAlias(cache.getHostUrl(), cache.getBaseRequest(), newAlias, userName, cache.getToken());
        WechatUtils.checkBaseResponse(response);
    }

    @Override
    public Set<Contact> batchGetContact(Set<String> list, WechatRobotCache cache) {
        ChatRoomDescription[] descriptions =
                list.stream().map(x -> {
                    ChatRoomDescription description = new ChatRoomDescription();
                    description.setUserName(x);
                    return description;
                }).toArray(ChatRoomDescription[]::new);
        BatchGetContactResponse response = internal.batchGetContact(cache.getHostUrl(), cache.getBaseRequest(), descriptions, cache.getToken());
        WechatUtils.checkBaseResponse(response);
        return response.getContactList();
    }

    @Override
    public void createChatRoom(List<String> userNames, String topic, WechatRobotCache cache) {
        CreateChatRoomResponse response = internal.createChatRoom(cache.getHostUrl(), cache.getBaseRequest(), userNames, topic, cache.getToken());
        WechatUtils.checkBaseResponse(response);
        //invoke BatchGetContact after CreateChatRoom
        ChatRoomDescription description = new ChatRoomDescription();
        description.setUserName(response.getChatRoomName());
        ChatRoomDescription[] descriptions = new ChatRoomDescription[]{description};
        BatchGetContactResponse batchGetContactResponse = internal.batchGetContact(cache.getHostUrl(), cache.getBaseRequest(), descriptions, cache.getToken());
        WechatUtils.checkBaseResponse(batchGetContactResponse);
        cache.getChatRooms().addAll(batchGetContactResponse.getContactList());
    }

    @Override
    public void deleteChatRoomMember(String chatRoomUserName, String userName, WechatRobotCache cache) {
        DeleteChatRoomMemberResponse response = internal.deleteChatRoomMember(cache.getHostUrl(), cache.getBaseRequest(), chatRoomUserName, userName, cache.getToken());
        WechatUtils.checkBaseResponse(response);
    }

    @Override
    public void addChatRoomMember(String chatRoomUserName, String userName, WechatRobotCache cache) {
        AddChatRoomMemberResponse response = internal.addChatRoomMember(cache.getHostUrl(), cache.getBaseRequest(), chatRoomUserName, userName, cache.getToken());
        WechatUtils.checkBaseResponse(response);
        Contact chatRoom = cache.getChatRooms().stream().filter(x -> chatRoomUserName.equals(x.getUserName())).findFirst().orElse(null);
        if (chatRoom == null) {
            throw new RuntimeException("can't find " + chatRoomUserName);
        }
        chatRoom.getMemberList().addAll(response.getMemberList());
    }

    @Override
    public byte[] downloadImage(String url, WechatRobotCache cache) {
        return internal.downloadImage(url);
    }

    @Override
    public void notifyNecessary(String userName, WechatRobotCache cache) {
        if (userName == null) {
            throw new IllegalArgumentException("userName");
        }
        Set<String> unreadContacts = cache.getContactNamesWithUnreadMessage();
        if (unreadContacts.contains(userName)) {
            internal.statusNotify(cache.getHostUrl(), cache.getBaseRequest(), userName, StatusNotifyCode.READED.getCode(), cache.getPassTicket());
            unreadContacts.remove(userName);
        }
    }
}
