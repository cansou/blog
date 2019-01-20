package com.blog.cloud.domain.shared;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Member", description = "Member")
public class ChatRoomMember {

    @ApiModelProperty(name = "Uin", value = "Uin")
    private Long Uin;

    @ApiModelProperty(name = "UserName", value = "UserName")
    private String UserName;

    @ApiModelProperty(name = "NickName", value = "NickName")
    private String NickName;

    @ApiModelProperty(name = "AttrStatus", value = "AttrStatus")
    private Long AttrStatus;

    @ApiModelProperty(name = "PYInitial", value = "PYInitial")
    private String PYInitial;

    @ApiModelProperty(name = "PYQuanPin", value = "PYQuanPin")
    private String PYQuanPin;

    @ApiModelProperty(name = "RemarkPYInitial", value = "RemarkPYInitial")
    private String RemarkPYInitial;

    @ApiModelProperty(name = "RemarkPYQuanPin", value = "RemarkPYQuanPin")
    private String RemarkPYQuanPin;

    @ApiModelProperty(name = "MemberStatus", value = "MemberStatus")
    private Long MemberStatus;

    @ApiModelProperty(name = "DisplayName", value = "DisplayName")
    private String DisplayName;

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
