package com.blog.cloud.service;

import com.blog.cloud.domain.shared.*;

import java.io.IOException;
import java.util.Set;

public interface IMessageService {
    /**
     * 事件：收到群聊天文本消息
     *
     * @param message 消息体
     */
    void onReceivingChatRoomTextMessage(Message message, WechatRobotCache cache);

    /**
     * 事件：收到群聊天图片消息
     *
     * @param message       消息体
     * @param thumbImageUrl 图片缩略图链接
     * @param fullImageUrl  图片完整图链接
     */
    void onReceivingChatRoomImageMessage(Message message, String thumbImageUrl, String fullImageUrl, WechatRobotCache cache);

    /**
     * 事件：收到个人聊天文本消息
     *
     * @param message 消息体
     */
    void onReceivingPrivateTextMessage(Message message, WechatRobotCache cache);

    /**
     * 事件：收到个人聊天图片消息
     *
     * @param message       消息体
     * @param thumbImageUrl 图片缩略图链接
     * @param fullImageUrl  图片完整图链接
     */
    void onReceivingPrivateImageMessage(Message message, String thumbImageUrl, String fullImageUrl, WechatRobotCache cache);

    /**
     * 事件：收到加好友邀请
     *
     * @param info 邀请信息
     * @return {@code true} 如果接受请求, 否则 {@code false}
     */
    boolean onReceivingFriendInvitation(RecommendInfo info, WechatRobotCache cache);

    /**
     * 事件：接受好友邀请成功
     *
     * @param message 消息体
     */
    void postAcceptFriendInvitation(Message message, WechatRobotCache cache);

    /**
     * 事件：群成员发生变化
     *
     * @param chatRoom      群
     * @param membersJoined 新加入的群成员
     * @param membersLeft   离开的群成员
     */
    void onChatRoomMembersChanged(Contact chatRoom, Set<ChatRoomMember> membersJoined, Set<ChatRoomMember> membersLeft, WechatRobotCache cache);

    /**
     * 事件：发现新增群（例如加入了新群）
     *
     * @param chatRooms 新增的群
     */
    void onNewChatRoomsFound(Set<Contact> chatRooms, WechatRobotCache cache);

    /**
     * 事件：发现群减少（例如被踢出了群）
     *
     * @param chatRooms 减少的群
     */
    void onChatRoomsDeleted(Set<Contact> chatRooms, WechatRobotCache cache);

    /**
     * 事件：发现新的好友
     *
     * @param contacts 新的好友
     */
    void onNewFriendsFound(Set<Contact> contacts, WechatRobotCache cache);

    /**
     * 事件：发现好友减少
     *
     * @param contacts 减少的好友
     */
    void onFriendsDeleted(Set<Contact> contacts, WechatRobotCache cache);

    /**
     * 事件：发现新的公众号
     *
     * @param mps 新的公众号
     */
    void onNewMediaPlatformsFound(Set<Contact> mps, WechatRobotCache cache);

    /**
     * 事件：删除公众号
     *
     * @param mps 被删除的公众号
     */
    void onMediaPlatformsDeleted(Set<Contact> mps, WechatRobotCache cache);

    /**
     * 事件：收到红包（个人的或者群里的）
     *
     * @param contact 发红包的个人或者群
     */
    void onRedPacketReceived(Contact contact, WechatRobotCache cache);
}