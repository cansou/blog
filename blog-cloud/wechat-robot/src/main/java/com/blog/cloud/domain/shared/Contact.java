package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "Contact", description = "Contact")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact extends Member {
    
    @JsonProperty
    @ApiModelProperty(name = "MemberCount", value = "MemberCount")
    private Integer MemberCount;
    
    @JsonProperty
    @ApiModelProperty(name = "MemberList", value = "MemberList")
    private Set<ChatRoomMember> MemberList;
    
    @JsonProperty
    @ApiModelProperty(name = "OwnerUin", value = "OwnerUin")
    private Integer OwnerUin;
    
    @JsonProperty
    @ApiModelProperty(name = "Statues", value = "Statues")
    private Long Statues;
    
    @JsonProperty
    @ApiModelProperty(name = "AttrStatus", value = "AttrStatus")
    private Long AttrStatus;
    
    @JsonProperty
    @ApiModelProperty(name = "Province", value = "Province")
    private String Province;
    
    @JsonProperty
    @ApiModelProperty(name = "City", value = "City")
    private String City;
    
    @JsonProperty
    @ApiModelProperty(name = "Alias", value = "Alias")
    private String Alias;
    
    @JsonProperty
    @ApiModelProperty(name = "UniFriend", value = "")
    private Integer UniFriend;
    
    @JsonProperty
    @ApiModelProperty(name = "DisplayName", value = "DisplayName")
    private String DisplayName;
    
    @JsonProperty
    @ApiModelProperty(name = "ChatRoomId", value = "ChatRoomId")
    private Long ChatRoomId;
    
    @JsonProperty
    @ApiModelProperty(name = "KeyWord", value = "KeyWord")
    private String KeyWord;
    
    @JsonProperty
    @ApiModelProperty(name = "EncryChatRoomId", value = "EncryChatRoomId")
    private String EncryChatRoomId;
    
    @JsonProperty
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
