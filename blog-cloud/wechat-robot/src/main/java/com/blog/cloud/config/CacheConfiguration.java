package com.blog.cloud.config;

import com.blog.cloud.domain.request.BaseRequest;
import com.blog.cloud.domain.shared.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Data
@Configuration
public class CacheConfiguration {

    @ApiModelProperty(name = "Uin", value = "Uin")
    private Boolean alive;

    @ApiModelProperty(name = "uuid", value = "uuid")
    private String uuid;

    @ApiModelProperty(name = "hostUrl", value = "hostUrl")
    private String hostUrl;

    @ApiModelProperty(name = "syncUrl", value = "syncUrl")
    private String syncUrl;

    @ApiModelProperty(name = "fileUrl", value = "fileUrl")
    private String fileUrl;

    @ApiModelProperty(name = "passTicket", value = "passTicket")
    private String passTicket;

    @ApiModelProperty(name = "baseRequest", value = "baseRequest")
    private BaseRequest baseRequest;

    @ApiModelProperty(name = "owner", value = "owner")
    private Owner owner;

    @ApiModelProperty(name = "syncKey", value = "syncKey")
    private SyncKey syncKey;

    @ApiModelProperty(name = "syncCheckKey", value = "syncCheckKey")
    private SyncCheckKey syncCheckKey;

    @ApiModelProperty(name = "sKey", value = "sKey")
    private String sKey;

    @ApiModelProperty(name = "uin", value = "uin")
    private String uin;

    @ApiModelProperty(name = "sid", value = "sid")
    private String sid;

    @ApiModelProperty(name = "individuals", value = "individuals")
    private Set<Contact> individuals;

    @ApiModelProperty(name = "mediaPlatforms", value = "mediaPlatforms")
    private Set<Contact> mediaPlatforms;

    @ApiModelProperty(name = "chatRooms", value = "chatRooms")
    private Set<Contact> chatRooms;

    @ApiModelProperty(name = "contactNamesWithUnreadMessage", value = "contactNamesWithUnreadMessage")
    private Set<String> contactNamesWithUnreadMessage;

    @ApiModelProperty(name = "token", value = "token")
    private Token token;


    public CacheConfiguration() {
        this.alive = false;
        this.individuals = new HashSet<>();
        this.mediaPlatforms = new HashSet<>();
        this.chatRooms = new HashSet<>();
        this.contactNamesWithUnreadMessage = new HashSet<>();
    }

    public void reset() {
        this.hostUrl = null;
        this.syncUrl = null;
        this.fileUrl = null;
        this.passTicket = null;
        this.baseRequest = null;
        this.owner = null;
        this.syncCheckKey = null;
        this.syncKey = null;
        this.sKey = null;
        this.uuid = null;
        this.uin = null;
        this.sid = null;
        this.individuals.clear();
        this.mediaPlatforms.clear();
        this.chatRooms.clear();
    }


}