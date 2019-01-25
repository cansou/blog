package com.blog.cloud.service.impl;

import com.blog.cloud.config.CacheConfiguration;
import com.blog.cloud.config.WechatApiProperties;
import com.blog.cloud.config.WechatApiServiceInternal;
import com.blog.cloud.domain.response.SyncCheckResponse;
import com.blog.cloud.domain.response.SyncResponse;
import com.blog.cloud.domain.response.VerifyUserResponse;
import com.blog.cloud.domain.shared.*;
import com.blog.cloud.enums.MessageType;
import com.blog.cloud.enums.RetCode;
import com.blog.cloud.enums.Selector;
import com.blog.cloud.service.MessageService;
import com.blog.cloud.service.SyncServie;
import com.blog.cloud.utils.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author lxw
 * @date 2019/1/22
 * @description
 */
@Slf4j
@Service
public class SyncServieImpl implements SyncServie {

    @Autowired
    private WechatApiServiceInternal internal;

    @Autowired
    private CacheConfiguration cacheConfiguration;

    @Autowired
    private MessageService messageService;

    @Autowired
    private WechatApiProperties properties;

    private final static String RED_PACKET_CONTENT = "收到红包，请在手机上查看";

    public void listen() {

        try {

            SyncCheckResponse syncCheckResponse = internal.syncCheck(cacheConfiguration.getSyncUrl(), cacheConfiguration.getToken(), cacheConfiguration.getSyncKey());
            Integer retCode = syncCheckResponse.getRetcode();
            Integer selector = syncCheckResponse.getSelector();

            log.info(String.format("[SYNCCHECK] retcode = %s, selector = %s", retCode, selector));

            if (retCode == RetCode.NORMAL.getCode()) {
                log.info("你有一条新的消息");

                if (selector == Selector.NEW_MESSAGE.getCode()) {
                    onNewMessage();
                } else if (selector == Selector.ENTER_LEAVE_CHAT.getCode()) {
                    sync();
                } else if (selector == Selector.CONTACT_UPDATED.getCode()) {
                    sync();
                } else if (selector == Selector.UNKNOWN1.getCode()) {
                    sync();
                } else if (selector == Selector.UNKNOWN6.getCode()) {
                    sync();
                } else if (selector != Selector.NORMAL.getCode()) {
                    throw new RuntimeException("syncCheckResponse ret = " + retCode);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    private void onNewMessage() throws IOException, URISyntaxException {
        SyncResponse syncResponse = sync();
        if (messageService == null) {
            return;
        }
        for (Message message : syncResponse.getAddMsgList()) {
            //文本消息
            if (message.getMsgType() == MessageType.TEXT.getCode()) {
                cacheConfiguration.getContactNamesWithUnreadMessage().add(message.getFromUserName());
                //个人
                if (isMessageFromIndividual(message)) {
                    messageService.onReceivingPrivateTextMessage(message);
                }
                //群
                else if (isMessageFromChatRoom(message)) {
                    messageService.onReceivingChatRoomTextMessage(message);
                }
                //图片
            } else if (message.getMsgType() == MessageType.IMAGE.getCode()) {
                cacheConfiguration.getContactNamesWithUnreadMessage().add(message.getFromUserName());

                String fullImageUrl = cacheConfiguration.getHostUrl() + String.format(properties.getUrl().getGetMsgImg(), message.getMsgId(), cacheConfiguration.getSKey(), cacheConfiguration.getPassTicket());
                String thumbImageUrl = fullImageUrl + "&type=slave";
                //个人
                if (isMessageFromIndividual(message)) {
                    messageService.onReceivingPrivateImageMessage(message, thumbImageUrl, fullImageUrl);
                }
                //群
                else if (isMessageFromChatRoom(message)) {
                    messageService.onReceivingChatRoomImageMessage(message, thumbImageUrl, fullImageUrl);
                }
            }
            //系统消息
            else if (message.getMsgType() == MessageType.SYS.getCode()) {
                //红包
                if (RED_PACKET_CONTENT.equals(message.getContent())) {
                    log.info("[*] you've received a red packet");
                    String from = message.getFromUserName();
                    Set<Contact> contacts = null;
                    //个人
                    if (isMessageFromIndividual(message)) {
                        contacts = cacheConfiguration.getIndividuals();
                    }
                    //群
                    else if (isMessageFromChatRoom(message)) {
                        contacts = cacheConfiguration.getChatRooms();
                    }
                    if (contacts != null) {
                        Contact contact = contacts.stream().filter(x -> Objects.equals(x.getUserName(), from)).findAny().orElse(null);
                        messageService.onRedPacketReceived(contact);
                    }
                }
            }
            //好友邀请
            else if (message.getMsgType() == MessageType.VERIFYMSG.getCode() && cacheConfiguration.getOwner().getUserName().equals(message.getToUserName())) {
                if (messageService.onReceivingFriendInvitation(message.getRecommendInfo())) {
                    acceptFriendInvitation(message.getRecommendInfo());
                    log.info("[*] you've accepted the invitation");
                    messageService.postAcceptFriendInvitation(message);
                } else {
                    log.info("[*] you've declined the invitation");
                    //TODO decline invitation
                }
            }

        }
    }

    private boolean isMessageFromIndividual(Message message) {
        return message.getFromUserName() != null
                && message.getFromUserName().startsWith("@")
                && !message.getFromUserName().startsWith("@@");
    }

    private boolean isMessageFromChatRoom(Message message) {
        return message.getFromUserName() != null && message.getFromUserName().startsWith("@@");
    }

    private void acceptFriendInvitation(RecommendInfo info) throws IOException, URISyntaxException {
        VerifyUser user = new VerifyUser();
        user.setValue(info.getUserName());
        user.setVerifyUserTicket(info.getTicket());
        VerifyUserResponse verifyUserResponse = internal.acceptFriend(
                cacheConfiguration.getHostUrl(),
                cacheConfiguration.getBaseRequest(),
                cacheConfiguration.getPassTicket(),
                Arrays.asList(user)
        );
        WechatUtils.checkBaseResponse(verifyUserResponse);
    }

    private SyncResponse sync() throws IOException {
        SyncResponse syncResponse = internal.sync(cacheConfiguration.getHostUrl(), cacheConfiguration.getSyncKey(), cacheConfiguration.getBaseRequest());
        WechatUtils.checkBaseResponse(syncResponse);
        cacheConfiguration.setSyncKey(syncResponse.getSyncKey());
        cacheConfiguration.setSyncCheckKey(syncResponse.getSyncCheckKey());
        //mod包含新增和修改
        if (syncResponse.getModContactCount() > 0) {
            onContactsModified(syncResponse.getModContactList());
        }
        //del->联系人移除
        if (syncResponse.getDelContactCount() > 0) {
            onContactsDeleted(syncResponse.getDelContactList());
        }
        return syncResponse;
    }

    private void onContactsModified(Set<Contact> contacts) {
        Set<Contact> individuals = new HashSet<>();
        Set<Contact> chatRooms = new HashSet<>();
        Set<Contact> mediaPlatforms = new HashSet<>();

        for (Contact contact : contacts) {
            if (WechatUtils.isIndividual(contact)) {
                individuals.add(contact);
            } else if (WechatUtils.isMediaPlatform(contact)) {
                mediaPlatforms.add(contact);
            } else if (WechatUtils.isChatRoom(contact)) {
                chatRooms.add(contact);
            }
        }

        //individual
        if (individuals.size() > 0) {
            Set<Contact> existingIndividuals = cacheConfiguration.getIndividuals();
            Set<Contact> newIndividuals = individuals.stream().filter(x -> !existingIndividuals.contains(x)).collect(Collectors.toSet());
            individuals.forEach(x -> {
                existingIndividuals.remove(x);
                existingIndividuals.add(x);
            });
            if (messageService != null && newIndividuals.size() > 0) {
                messageService.onNewFriendsFound(newIndividuals);
            }
        }
        //chatroom
        if (chatRooms.size() > 0) {
            Set<Contact> existingChatRooms = cacheConfiguration.getChatRooms();
            Set<Contact> newChatRooms = new HashSet<>();
            Set<Contact> modifiedChatRooms = new HashSet<>();
            for (Contact chatRoom : chatRooms) {
                if (existingChatRooms.contains(chatRoom)) {
                    modifiedChatRooms.add(chatRoom);
                } else {
                    newChatRooms.add(chatRoom);
                }
            }
            existingChatRooms.addAll(newChatRooms);
            if (messageService != null && newChatRooms.size() > 0) {
                messageService.onNewChatRoomsFound(newChatRooms);
            }
            for (Contact chatRoom : modifiedChatRooms) {
                Contact existingChatRoom = existingChatRooms.stream().filter(x -> x.getUserName().equals(chatRoom.getUserName())).findFirst().orElse(null);
                if (existingChatRoom == null) {
                    continue;
                }
                existingChatRooms.remove(existingChatRoom);
                existingChatRooms.add(chatRoom);
                if (messageService != null) {
                    Set<ChatRoomMember> oldMembers = existingChatRoom.getMemberList();
                    Set<ChatRoomMember> newMembers = chatRoom.getMemberList();
                    Set<ChatRoomMember> joined = newMembers.stream().filter(x -> !oldMembers.contains(x)).collect(Collectors.toSet());
                    Set<ChatRoomMember> left = oldMembers.stream().filter(x -> !newMembers.contains(x)).collect(Collectors.toSet());
                    if (joined.size() > 0 || left.size() > 0) {
                        messageService.onChatRoomMembersChanged(chatRoom, joined, left);
                    }
                }
            }
        }
        if (mediaPlatforms.size() > 0) {
            //media platform
            Set<Contact> existingPlatforms = cacheConfiguration.getMediaPlatforms();
            Set<Contact> newMediaPlatforms = existingPlatforms.stream().filter(x -> !existingPlatforms.contains(x)).collect(Collectors.toSet());
            mediaPlatforms.forEach(x -> {
                existingPlatforms.remove(x);
                existingPlatforms.add(x);
            });
            if (messageService != null && newMediaPlatforms.size() > 0) {
                messageService.onNewMediaPlatformsFound(newMediaPlatforms);
            }
        }
    }

    private void onContactsDeleted(Set<Contact> contacts) {
        Set<Contact> individuals = new HashSet<>();
        Set<Contact> chatRooms = new HashSet<>();
        Set<Contact> mediaPlatforms = new HashSet<>();
        for (Contact contact : contacts) {
            if (WechatUtils.isIndividual(contact)) {
                individuals.add(contact);
                cacheConfiguration.getIndividuals().remove(contact);
            } else if (WechatUtils.isChatRoom(contact)) {
                chatRooms.add(contact);
                cacheConfiguration.getChatRooms().remove(contact);
            } else if (WechatUtils.isMediaPlatform(contact)) {
                mediaPlatforms.add(contact);
                cacheConfiguration.getMediaPlatforms().remove(contact);
            }
        }
        if (messageService != null) {
            if (individuals.size() > 0) {
                messageService.onFriendsDeleted(individuals);
            }
            if (chatRooms.size() > 0) {
                messageService.onChatRoomsDeleted(chatRooms);
            }
            if (mediaPlatforms.size() > 0) {
                messageService.onMediaPlatformsDeleted(mediaPlatforms);
            }
        }
    }


}
