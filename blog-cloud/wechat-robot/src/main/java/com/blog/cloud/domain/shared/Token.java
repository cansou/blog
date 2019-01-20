package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Token", description = "Token")
@JacksonXmlRootElement(localName = "error")
public class Token {

    @JacksonXmlProperty(localName = "ret")
    @ApiModelProperty(name = "ret", value = "ret")
    private Integer ret;

    @JacksonXmlProperty(localName = "message")
    @ApiModelProperty(name = "message", value = "message")
    private String message;

    @JacksonXmlProperty(localName = "skey")
    @ApiModelProperty(name = "skey", value = "skey")
    private String skey;

    @JacksonXmlProperty(localName = "wxsid")
    @ApiModelProperty(name = "wxsid", value = "wxsid")
    private String wxsid;

    @JacksonXmlProperty(localName = "wxuin")
    @ApiModelProperty(name = "wxuin", value = "wxuin")
    private String wxuin;

    @JacksonXmlProperty(localName = "pass_ticket")
    @ApiModelProperty(name = "pass_ticket", value = "pass_ticket")
    private String pass_ticket;

    @JacksonXmlProperty(localName = "isgrayscale")
    @ApiModelProperty(name = "isgrayscale", value = "isgrayscale")
    private int isgrayscale;

}