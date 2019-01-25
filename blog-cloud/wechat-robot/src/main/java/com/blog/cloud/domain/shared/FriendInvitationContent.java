package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "msg")
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "FriendInvitationContent", description = "FriendInvitationContent")
public class FriendInvitationContent {
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "fromusername", value = "fromusername")
    private String fromusername;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "encryptusername", value = "encryptusername")
    private String encryptusername;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "fromnickname", value = "fromnickname")
    private String fromnickname;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "content", value = "content")
    private String content;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "shortpy", value = "shortpy")
    private String shortpy;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "imagestatus", value = "imagestatus")
    private Long imagestatus;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "scene", value = "scene")
    private Integer scene;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "country", value = "country")
    private String country;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "province", value = "province")
    private String province;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "city", value = "city")
    private String city;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "percard", value = "percard")
    private Integer percard;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "sex", value = "sex")
    private Integer sex;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "alias", value = "alias")
    private String alias;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "albumflag", value = "albumflag")
    private Long albumflag;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "albumstyle", value = "albumstyle")
    private Integer albumstyle;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "albumbgimgid", value = "albumbgimgid")
    private String albumbgimgid;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "snsflag", value = "snsflag")
    private Long snsflag;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "snsbgimgid", value = "snsbgimgid")
    private String snsbgimgid;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "snsbgobjectid", value = "snsbgobjectid")
    private String snsbgobjectid;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "mhash", value = "mhash")
    private String mhash;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "mfullhash", value = "mfullhash")
    private String mfullhash;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "bigheadimgurl", value = "bigheadimgurl")
    private String bigheadimgurl;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "smallheadimgurl", value = "smallheadimgurl")
    private String smallheadimgurl;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "ticket", value = "ticket")
    private String ticket;
    
    @JacksonXmlProperty
    @ApiModelProperty(name = "opcode", value = "opcode")
    private Integer opcode;

}