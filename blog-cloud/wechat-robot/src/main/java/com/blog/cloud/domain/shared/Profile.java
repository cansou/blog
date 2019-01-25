package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Profile", description = "Profile")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile {
    
    @JsonProperty
    @ApiModelProperty(name = "BitFlag", value = "BitFlag")
    private Integer BitFlag;
    
    @JsonProperty
    @ApiModelProperty(name = "UserName", value = "UserName")
    private Object UserName;
    
    @JsonProperty
    @ApiModelProperty(name = "NickName", value = "NickName")
    private Object NickName;
    
    @JsonProperty
    @ApiModelProperty(name = "BindEmail", value = "BindEmail")
    private Object BindEmail;
    
    @JsonProperty
    @ApiModelProperty(name = "BindMobile", value = "BindMobile")
    private Object BindMobile;
    
    @JsonProperty
    @ApiModelProperty(name = "BindUin", value = "BindUin")
    private Long BindUin;
    
    @JsonProperty
    @ApiModelProperty(name = "Status", value = "Status")
    private Long Status;
    
    @JsonProperty
    @ApiModelProperty(name = "Sex", value = "Sex")
    private Integer Sex;
    
    @JsonProperty
    @ApiModelProperty(name = "PersonalCard", value = "PersonalCard")
    private Integer PersonalCard;
    
    @JsonProperty
    @ApiModelProperty(name = "Alias", value = "Alias")
    private String Alias;
    
    @JsonProperty
    @ApiModelProperty(name = "HeadImgUpdateFlag", value = "HeadImgUpdateFlag")
    private Integer HeadImgUpdateFlag;
    
    @JsonProperty
    @ApiModelProperty(name = "HeadImgUrl", value = "HeadImgUrl")
    private String HeadImgUrl;
    
    @JsonProperty
    @ApiModelProperty(name = "Signature", value = "Signature")
    private String Signature;
    
}