package com.blog.cloud.service.impl;

import com.blog.cloud.domain.shared.*;
import com.blog.cloud.service.MessageService;
import com.blog.cloud.service.WechatHttpService;
import com.blog.cloud.utils.MessageUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private WechatHttpService wechatHttpService;

    @Override
    public void onReceivingChatRoomTextMessage(Message message) {
        log.info("onReceivingChatRoomTextMessage");
        log.info("from chatroom: " + message.getFromUserName());
        log.info("from person: " + MessageUtils.getSenderOfChatRoomTextMessage(message.getContent()));
        log.info("to: " + message.getToUserName());
        log.info("content:" + MessageUtils.getChatRoomTextMessageContent(message.getContent()));
    }

    @Override
    public void onReceivingChatRoomImageMessage(Message message, String thumbImageUrl, String fullImageUrl) {
        log.info("onReceivingChatRoomImageMessage");
        log.info("thumbImageUrl:" + thumbImageUrl);
        log.info("fullImageUrl:" + fullImageUrl);
    }

    @Override
    public void onReceivingPrivateTextMessage(Message message) {
        log.info("onReceivingPrivateTextMessage");
        log.info("from: " + message.getFromUserName());
        log.info("to: " + message.getToUserName());
        log.info("content:" + message.getContent());
//        将原文回复给对方
        replyMessage(message);
    }

    @Override
    public void onReceivingPrivateImageMessage(Message message, String thumbImageUrl, String fullImageUrl) {
        try {
            log.info("onReceivingPrivateImageMessage");
            log.info("thumbImageUrl:" + thumbImageUrl);
            log.info("fullImageUrl:" + fullImageUrl);
//        将图片保存在本地
            byte[] data = wechatHttpService.downloadImage(thumbImageUrl);
            FileOutputStream fos = new FileOutputStream("thumb.jpg");
            fos.write(data);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onReceivingFriendInvitation(RecommendInfo info) {
        log.info("onReceivingFriendInvitation");
        log.info("recommendinfo content:" + info.getContent());
//        默认接收所有的邀请
        return true;
    }

    @Override
    public void postAcceptFriendInvitation(Message message) {
        try {
            log.info("postAcceptFriendInvitation");
//        将该用户的微信号设置成他的昵称
            String content = StringEscapeUtils.unescapeXml(message.getContent());
            ObjectMapper xmlMapper = new XmlMapper();
            FriendInvitationContent friendInvitationContent = xmlMapper.readValue(content, FriendInvitationContent.class);
            wechatHttpService.setAlias(message.getRecommendInfo().getUserName(), friendInvitationContent.getFromusername());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChatRoomMembersChanged(Contact chatRoom, Set<ChatRoomMember> membersJoined, Set<ChatRoomMember> membersLeft) {
        log.info("onChatRoomMembersChanged");
        log.info("chatRoom:" + chatRoom.getUserName());
        if (membersJoined != null && membersJoined.size() > 0) {
            log.info("membersJoined:" + String.join(",", membersJoined.stream().map(ChatRoomMember::getNickName).collect(Collectors.toList())));
        }
        if (membersLeft != null && membersLeft.size() > 0) {
            log.info("membersLeft:" + String.join(",", membersLeft.stream().map(ChatRoomMember::getNickName).collect(Collectors.toList())));
        }
    }

    @Override
    public void onNewChatRoomsFound(Set<Contact> chatRooms) {
        log.info("onNewChatRoomsFound");
        chatRooms.forEach(x -> log.info(x.getUserName()));
    }

    @Override
    public void onChatRoomsDeleted(Set<Contact> chatRooms) {
        log.info("onChatRoomsDeleted");
        chatRooms.forEach(x -> log.info(x.getUserName()));
    }

    @Override
    public void onNewFriendsFound(Set<Contact> contacts) {
        log.info("onNewFriendsFound");
        contacts.forEach(x -> {
            log.info(x.getUserName());
            log.info(x.getNickName());
        });
    }

    @Override
    public void onFriendsDeleted(Set<Contact> contacts) {
        log.info("onFriendsDeleted");
        contacts.forEach(x -> {
            log.info(x.getUserName());
            log.info(x.getNickName());
        });
    }

    @Override
    public void onNewMediaPlatformsFound(Set<Contact> mps) {
        log.info("onNewMediaPlatformsFound");
    }

    @Override
    public void onMediaPlatformsDeleted(Set<Contact> mps) {
        log.info("onMediaPlatformsDeleted");
    }

    @Override
    public void onRedPacketReceived(Contact contact) {
        log.info("onRedPacketReceived");
        if (contact != null) {
            log.info("the red packet is from " + contact.getNickName());
        }
    }

    private void replyMessage(Message message) {
        wechatHttpService.sendText(message.getFromUserName(), message.getContent());
    }

}
