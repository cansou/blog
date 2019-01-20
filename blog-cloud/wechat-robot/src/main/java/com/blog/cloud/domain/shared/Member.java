package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Member", description = "Member")
public class Member {
    @ApiModelProperty(name = "Uin", value = "Uin")
    private Long Uin;

    @ApiModelProperty(name = "UserName", value = "UserName")
    private String UserName;

    @ApiModelProperty(name = "NickName", value = "NickName")
    private String NickName;

    @ApiModelProperty(name = "HeadImgUrl", value = "HeadImgUrl")
    private String HeadImgUrl;

    @ApiModelProperty(name = "ContactFlag", value = "ContactFlag")
    private Integer ContactFlag;

    @ApiModelProperty(name = "RemarkName", value = "RemarkName")
    private String RemarkName;

    @ApiModelProperty(name = "HideInputBarFlag", value = "HideInputBarFlag")
    private Integer HideInputBarFlag;

    @ApiModelProperty(name = "Sex", value = "Sex")
    private Integer Sex;

    @ApiModelProperty(name = "Signature", value = "Signature")
    private String Signature;

    @ApiModelProperty(name = "VerifyFlag", value = "VerifyFlag")
    private Integer VerifyFlag;

    @ApiModelProperty(name = "PYInitial", value = "PYInitial")
    private String PYInitial;

    @ApiModelProperty(name = "PYQuanPin", value = "PYQuanPin")
    private String PYQuanPin;

    @ApiModelProperty(name = "RemarkPYInitial", value = "RemarkPYInitial")
    private String RemarkPYInitial;

    @ApiModelProperty(name = "RemarkPYQuanPin", value = "RemarkPYQuanPin")
    private String RemarkPYQuanPin;

    @ApiModelProperty(name = "StarFriend", value = "StarFriend")
    private Integer StarFriend;

    @ApiModelProperty(name = "AppAccountFlag", value = "AppAccountFlag")
    private Long AppAccountFlag;

    @ApiModelProperty(name = "SnsFlag", value = "SnsFlag")
    private Long SnsFlag;

}
