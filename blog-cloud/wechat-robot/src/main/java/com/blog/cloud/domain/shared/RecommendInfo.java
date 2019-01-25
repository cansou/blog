package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RecommendInfo", description = "RecommendInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendInfo {
    
    @JsonProperty
    @ApiModelProperty(name = "UserName", value = "UserName")
    private String UserName;
    
    @JsonProperty
    @ApiModelProperty(name = "NickName", value = "NickName")
    private String NickName;
    
    @JsonProperty
    @ApiModelProperty(name = "QQNum", value = "QQNum")
    private Long QQNum;
    
    @JsonProperty
    @ApiModelProperty(name = "Province", value = "Province")
    private String Province;
    
    @JsonProperty
    @ApiModelProperty(name = "City", value = "City")
    private String City;
    
    @JsonProperty
    @ApiModelProperty(name = "Content", value = "Content")
    private String Content;
    
    @JsonProperty
    @ApiModelProperty(name = "Signature", value = "Signature")
    private String Signature;
    
    @JsonProperty
    @ApiModelProperty(name = "Alias", value = "Alias")
    private String Alias;
    
    @JsonProperty
    @ApiModelProperty(name = "Scene", value = "Scene")
    private Integer Scene;
    
    @JsonProperty
    @ApiModelProperty(name = "VerifyFlag", value = "VerifyFlag")
    private Integer VerifyFlag;
    
    @JsonProperty
    @ApiModelProperty(name = "AttrStatus", value = "AttrStatus")
    private Long AttrStatus;
    
    @JsonProperty
    @ApiModelProperty(name = "Sex", value = "Sex")
    private Integer Sex;
    
    @JsonProperty
    @ApiModelProperty(name = "Ticket", value = "Ticket")
    private String Ticket;
    
    @JsonProperty
    @ApiModelProperty(name = "OpCode", value = "OpCode")
    private Integer OpCode;

}