package com.blog.cloud.service.impl;

import com.blog.cloud.config.WechatApiProperties;
import com.blog.cloud.config.WechatApiServiceInternal;
import com.blog.cloud.domain.response.SyncCheckResponse;
import com.blog.cloud.domain.response.SyncResponse;
import com.blog.cloud.domain.response.VerifyUserResponse;
import com.blog.cloud.domain.shared.*;
import com.blog.cloud.enums.MessageType;
import com.blog.cloud.enums.RetCode;
import com.blog.cloud.enums.Selector;
import com.blog.cloud.service.IMessageService;
import com.blog.cloud.service.ISyncServie;
import com.blog.cloud.utils.RedisUtil;
import com.blog.cloud.utils.WechatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("syncServie")
public class SyncServieImpl implements ISyncServie {

    @Autowired
    private WechatApiServiceInternal internal;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private WechatApiProperties properties;
    
    @Autowired
    private RedisUtil redisUtil;

    private final static String RED_PACKET_CONTENT = "收到红包，请在手机上查看";

    public boolean listen(String uuid) {

        try {

            WechatRobotCache cache = redisUtil.get(uuid, WechatRobotCache.class);
            if (cache.get_() == null) {
                cache.set_(System.currentTimeMillis());
            }
            if (cache.getR() == null) {
                cache.setR(cache.get_());
            }
            cache.setR(cache.getR() + 25000);

            SyncCheckResponse syncCheckResponse = internal.syncCheck(cache);
            Integer retCode = syncCheckResponse.getRetcode();
            Integer selector = syncCheckResponse.getSelector();

            log.info(String.format("[SYNCCHECK] retcode = %s, selector = %s", retCode, selector));

            if (retCode == RetCode.NORMAL.getCode()) {
                log.info("你有一条新的消息");

                if (selector == Selector.NEW_MESSAGE.getCode()) {
                    onNewMessage(cache);
                } else if (selector == Selector.ENTER_LEAVE_CHAT.getCode()) {
                    sync(cache);
                } else if (selector == Selector.CONTACT_UPDATED.getCode()) {
                    sync(cache);
                } else if (selector == Selector.UNKNOWN1.getCode()) {
                    sync(cache);
                } else if (selector == Selector.UNKNOWN6.getCode()) {
                    sync(cache);
                } else if (selector != Selector.NORMAL.getCode()) {
                    throw new RuntimeException("syncCheckResponse ret = " + retCode);
                }
                redisUtil.set(uuid, cache);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {

        }

    }

    private void onNewMessage(WechatRobotCache cache) {
        SyncResponse syncResponse = sync(cache);
        if (messageService == null) {
            return;
        }
        for (Message message : syncResponse.getAddMsgList()) {
            //文本消息
            if (message.getMsgType() == MessageType.TEXT.getCode()) {
                cache.getContactNamesWithUnreadMessage().add(message.getFromUserName());
                //个人
                if (isMessageFromIndividual(message)) {
                    messageService.onReceivingPrivateTextMessage(message, cache);
                }
                //群
                else if (isMessageFromChatRoom(message)) {
                    messageService.onReceivingChatRoomTextMessage(message, cache);
                }
                //图片
            } else if (message.getMsgType() == MessageType.IMAGE.getCode()) {
                cache.getContactNamesWithUnreadMessage().add(message.getFromUserName());

                String fullImageUrl = cache.getHostUrl() + String.format(properties.getUrl().getGetMsgImg(), message.getMsgId(), cache.getSKey(), cache.getPassTicket());
                String thumbImageUrl = fullImageUrl + "&type=slave";
                //个人
                if (isMessageFromIndividual(message)) {
                    messageService.onReceivingPrivateImageMessage(message, thumbImageUrl, fullImageUrl, cache);
                }
                //群
                else if (isMessageFromChatRoom(message)) {
                    messageService.onReceivingChatRoomImageMessage(message, thumbImageUrl, fullImageUrl, cache);
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
                        contacts = cache.getIndividuals();
                    }
                    //群
                    else if (isMessageFromChatRoom(message)) {
                        contacts = cache.getChatRooms();
                    }
                    if (contacts != null) {
                        Contact contact = contacts.stream().filter(x -> Objects.equals(x.getUserName(), from)).findAny().orElse(null);
                        messageService.onRedPacketReceived(contact, cache);
                    }
                }
            }
            //好友邀请
            else if (message.getMsgType() == MessageType.VERIFYMSG.getCode() && cache.getOwner().getUserName().equals(message.getToUserName())) {
                if (messageService.onReceivingFriendInvitation(message.getRecommendInfo(), cache)) {
                    acceptFriendInvitation(message.getRecommendInfo(), cache);
                    log.info("[*] you've accepted the invitation");
                    messageService.postAcceptFriendInvitation(message, cache);
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

    private void acceptFriendInvitation(RecommendInfo info, WechatRobotCache cache) {
        VerifyUser user = new VerifyUser();
        user.setValue(info.getUserName());
        user.setVerifyUserTicket(info.getTicket());
        VerifyUserResponse verifyUserResponse = internal.acceptFriend(
                cache.getHostUrl(),
                cache.getBaseRequest(),
                cache.getPassTicket(),
                Arrays.asList(user),
                cache
        );
        WechatUtils.checkBaseResponse(verifyUserResponse);
    }

    private SyncResponse sync(WechatRobotCache cache) {
        SyncResponse syncResponse = internal.sync(cache.getHostUrl(), cache.getSyncKey(), cache.getBaseRequest(), cache);
        WechatUtils.checkBaseResponse(syncResponse);
        cache.setSyncKey(syncResponse.getSyncKey());
        cache.setSyncCheckKey(syncResponse.getSyncCheckKey());
        //mod包含新增和修改
        if (syncResponse.getModContactCount() > 0) {
            onContactsModified(syncResponse.getModContactList(), cache);
        }
        //del->联系人移除
        if (syncResponse.getDelContactCount() > 0) {
            onContactsDeleted(syncResponse.getDelContactList(), cache);
        }
        return syncResponse;
    }

    private void onContactsModified(Set<Contact> contacts, WechatRobotCache cache) {
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
            Set<Contact> existingIndividuals = cache.getIndividuals();
            Set<Contact> newIndividuals = individuals.stream().filter(x -> !existingIndividuals.contains(x)).collect(Collectors.toSet());
            individuals.forEach(x -> {
                existingIndividuals.remove(x);
                existingIndividuals.add(x);
            });
            if (messageService != null && newIndividuals.size() > 0) {
                messageService.onNewFriendsFound(newIndividuals, cache);
            }
        }
        //chatroom
        if (chatRooms.size() > 0) {
            Set<Contact> existingChatRooms = cache.getChatRooms();
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
                messageService.onNewChatRoomsFound(newChatRooms, cache);
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
                        messageService.onChatRoomMembersChanged(chatRoom, joined, left, cache);
                    }
                }
            }
        }
        if (mediaPlatforms.size() > 0) {
            //media platform
            Set<Contact> existingPlatforms = cache.getMediaPlatforms();
            Set<Contact> newMediaPlatforms = existingPlatforms.stream().filter(x -> !existingPlatforms.contains(x)).collect(Collectors.toSet());
            mediaPlatforms.forEach(x -> {
                existingPlatforms.remove(x);
                existingPlatforms.add(x);
            });
            if (messageService != null && newMediaPlatforms.size() > 0) {
                messageService.onNewMediaPlatformsFound(newMediaPlatforms, cache);
            }
        }
    }

    private void onContactsDeleted(Set<Contact> contacts, WechatRobotCache cache) {
        Set<Contact> individuals = new HashSet<>();
        Set<Contact> chatRooms = new HashSet<>();
        Set<Contact> mediaPlatforms = new HashSet<>();
        for (Contact contact : contacts) {
            if (WechatUtils.isIndividual(contact)) {
                individuals.add(contact);
                cache.getIndividuals().remove(contact);
            } else if (WechatUtils.isChatRoom(contact)) {
                chatRooms.add(contact);
                cache.getChatRooms().remove(contact);
            } else if (WechatUtils.isMediaPlatform(contact)) {
                mediaPlatforms.add(contact);
                cache.getMediaPlatforms().remove(contact);
            }
        }
        if (messageService != null) {
            if (individuals.size() > 0) {
                messageService.onFriendsDeleted(individuals, cache);
            }
            if (chatRooms.size() > 0) {
                messageService.onChatRoomsDeleted(chatRooms, cache);
            }
            if (mediaPlatforms.size() > 0) {
                messageService.onMediaPlatformsDeleted(mediaPlatforms, cache);
            }
        }
    }


}
