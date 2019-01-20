package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "Contact", description = "Contact")
public class Contact extends Member {
    
    @ApiModelProperty(name = "MemberCount", value = "MemberCount")
    private Integer MemberCount;
    
    @ApiModelProperty(name = "MemberList", value = "MemberList")
    private Set<ChatRoomMember> MemberList;
    
    @ApiModelProperty(name = "OwnerUin", value = "OwnerUin")
    private Integer OwnerUin;
    
    @ApiModelProperty(name = "Statues", value = "Statues")
    private Long Statues;
    
    @ApiModelProperty(name = "AttrStatus", value = "AttrStatus")
    private Long AttrStatus;
    
    @ApiModelProperty(name = "Province", value = "Province")
    private String Province;
    
    @ApiModelProperty(name = "City", value = "City")
    private String City;
    
    @ApiModelProperty(name = "Alias", value = "Alias")
    private String Alias;
    
    @ApiModelProperty(name = "UniFriend", value = "")
    private Integer UniFriend;
    
    @ApiModelProperty(name = "DisplayName", value = "DisplayName")
    private String DisplayName;
    
    @ApiModelProperty(name = "ChatRoomId", value = "ChatRoomId")
    private Long ChatRoomId;
    
    @ApiModelProperty(name = "KeyWord", value = "KeyWord")
    private String KeyWord;
    
    @ApiModelProperty(name = "EncryChatRoomId", value = "EncryChatRoomId")
    private String EncryChatRoomId;
    
    @ApiModelProperty(name = "IsOwner", value = "IsOwner")
    private Integer IsOwner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return this.getUserName().equals(contact.getUserName());
    }

    @Override
    public int hashCode() {
        return this.getUserName().hashCode();
    }
}
