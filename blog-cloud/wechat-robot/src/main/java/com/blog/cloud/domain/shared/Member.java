package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Member", description = "Member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {
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
    @ApiModelProperty(name = "HeadImgUrl", value = "HeadImgUrl")
    private String HeadImgUrl;

    @JsonProperty
    @ApiModelProperty(name = "ContactFlag", value = "ContactFlag")
    private Integer ContactFlag;

    @JsonProperty
    @ApiModelProperty(name = "RemarkName", value = "RemarkName")
    private String RemarkName;

    @JsonProperty
    @ApiModelProperty(name = "HideInputBarFlag", value = "HideInputBarFlag")
    private Integer HideInputBarFlag;

    @JsonProperty
    @ApiModelProperty(name = "Sex", value = "Sex")
    private Integer Sex;

    @JsonProperty
    @ApiModelProperty(name = "Signature", value = "Signature")
    private String Signature;

    @JsonProperty
    @ApiModelProperty(name = "VerifyFlag", value = "VerifyFlag")
    private Integer VerifyFlag;

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
    @ApiModelProperty(name = "StarFriend", value = "StarFriend")
    private Integer StarFriend;

    @JsonProperty
    @ApiModelProperty(name = "AppAccountFlag", value = "AppAccountFlag")
    private Long AppAccountFlag;

    @JsonProperty
    @ApiModelProperty(name = "SnsFlag", value = "SnsFlag")
    private Long SnsFlag;

}
