package com.blog.cloud.service;

import com.blog.cloud.domain.shared.Contact;
import com.blog.cloud.domain.shared.WechatRobotCache;

import java.util.List;
import java.util.Set;

public interface IWechatHttpService {

    void logout(WechatRobotCache cache);

    Set<Contact> getContact(WechatRobotCache cache);

    void sendText(String userName, String content, WechatRobotCache cache);

    void setAlias(String userName, String newAlias, WechatRobotCache cache);

    Set<Contact> batchGetContact(Set<String> list, WechatRobotCache cache);

    void createChatRoom(List<String> userNames, String topic, WechatRobotCache cache);

    void deleteChatRoomMember(String chatRoomUserName, String userName, WechatRobotCache cache);

    void addChatRoomMember(String chatRoomUserName, String userName, WechatRobotCache cache);

    byte[] downloadImage(String url, WechatRobotCache cache);

    void notifyNecessary(String userName, WechatRobotCache cache);

}
