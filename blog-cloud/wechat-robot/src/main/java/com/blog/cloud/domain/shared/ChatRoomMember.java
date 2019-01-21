package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Member", description = "Member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatRoomMember {

    @JsonProperty
    @ApiModelProperty(name = "Uin", value = "Uin")
    private Long Uin;

    @JsonProperty
    @ApiModelProperty(name = "UserName", value = "UserName")
    private String UserName;

    @JsonProperty
    @ApiModelProperty(name = "NickName", value = "NickName")
    private String NickName;

    @JsonProperty
    @ApiModelProperty(name = "AttrStatus", value = "AttrStatus")
    private Long AttrStatus;

    @JsonProperty
    @ApiModelProperty(name = "PYInitial", value = "PYInitial")
    private String PYInitial;

    @JsonProperty
    @ApiModelProperty(name = "PYQuanPin", value = "PYQuanPin")
    private String PYQuanPin;

    @JsonProperty
    @ApiModelProperty(name = "RemarkPYInitial", value = "RemarkPYInitial")
    private String RemarkPYInitial;

    @JsonProperty
    @ApiModelProperty(name = "RemarkPYQuanPin", value = "RemarkPYQuanPin")
    private String RemarkPYQuanPin;

    @JsonProperty
    @ApiModelProperty(name = "MemberStatus", value = "MemberStatus")
    private Long MemberStatus;

    @JsonProperty
    @ApiModelProperty(name = "DisplayName", value = "DisplayName")
    private String DisplayName;

    @JsonProperty
    @ApiModelProperty(name = "Keyword", value = "Keyword")
    private String Keyword;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatRoomMember that = (ChatRoomMember) o;
        return UserName.equals(that.UserName);
    }

    @Override
    public int hashCode() {
        return UserName.hashCode();
    }
}
