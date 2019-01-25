package com.blog.cloud.service;

import com.blog.cloud.domain.shared.Contact;

import java.util.List;
import java.util.Set;

public interface WechatHttpService {

    void logout();

    Set<Contact> getContact();

    void sendText(String userName, String content);

    void setAlias(String userName, String newAlias);

    Set<Contact> batchGetContact(Set<String> list);

    void createChatRoom(List<String> userNames, String topic);

    void deleteChatRoomMember(String chatRoomUserName, String userName);

    void addChatRoomMember(String chatRoomUserName, String userName);

    byte[] downloadImage(String url);

    void notifyNecessary(String userName);

}
