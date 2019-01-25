package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Message", description = "Message")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    @JsonProperty
    @ApiModelProperty(name = "MsgId", value = "MsgId")
    private String MsgId;

    @JsonProperty
    @ApiModelProperty(name = "FromUserName", value = "FromUserName")
    private String FromUserName;

    @JsonProperty
    @ApiModelProperty(name = "ToUserName", value = "ToUserName")
    private String ToUserName;

    @JsonProperty
    @ApiModelProperty(name = "MsgType", value = "MsgType")
    private Integer MsgType;

    @JsonProperty
    @ApiModelProperty(name = "Content", value = "Content")
    private String Content;

    @JsonProperty
    @ApiModelProperty(name = "Status", value = "Status")
    private Long Status;

    @JsonProperty
    @ApiModelProperty(name = "ImgStatus", value = "ImgStatus")
    private Long ImgStatus;

    @JsonProperty
    @ApiModelProperty(name = "CreateTime", value = "CreateTime")
    private Long CreateTime;

    @JsonProperty
    @ApiModelProperty(name = "VoiceLength", value = "VoiceLength")
    private Long VoiceLength;

    @JsonProperty
    @ApiModelProperty(name = "PlayLength", value = "PlayLength")
    private Long PlayLength;

    @JsonProperty
    @ApiModelProperty(name = "FileName", value = "FileName")
    private String FileName;

    @JsonProperty
    @ApiModelProperty(name = "FileSize", value = "FileSize")
    private String FileSize;

    @JsonProperty
    @ApiModelProperty(name = "MediaId", value = "MediaId")
    private String MediaId;

    @JsonProperty
    @ApiModelProperty(name = "Url", value = "Url")
    private String Url;

    @JsonProperty
    @ApiModelProperty(name = "AppMsgType", value = "AppMsgType")
    private Integer AppMsgType;

    @JsonProperty
    @ApiModelProperty(name = "StatusNotifyCode", value = "StatusNotifyCode")
    private Integer StatusNotifyCode;

    @JsonProperty
    @ApiModelProperty(name = "StatusNotifyUserName", value = "StatusNotifyUserName")
    private String StatusNotifyUserName;

    @JsonProperty
    @ApiModelProperty(name = "RecommendInfo", value = "RecommendInfo")
    private RecommendInfo RecommendInfo;

    @JsonProperty
    @ApiModelProperty(name = "ForwardFlag", value = "ForwardFlag")
    private Integer ForwardFlag;

    @JsonProperty
    @ApiModelProperty(name = "AppInfo", value = "AppInfo")
    private AppInfo AppInfo;

    @JsonProperty
    @ApiModelProperty(name = "HasProductId", value = "HasProductId")
    private Integer HasProductId;

    @JsonProperty
    @ApiModelProperty(name = "Ticket", value = "Ticket")
    private String Ticket;

    @JsonProperty
    @ApiModelProperty(name = "ImgHeight", value = "ImgHeight")
    private Integer ImgHeight;

    @JsonProperty
    @ApiModelProperty(name = "ImgWidth", value = "ImgWidth")
    private Integer ImgWidth;

    @JsonProperty
    @ApiModelProperty(name = "SubMsgType", value = "SubMsgType")
    private Integer SubMsgType;

    @JsonProperty
    @ApiModelProperty(name = "NewMsgId", value = "NewMsgId")
    private Long NewMsgId;

    @JsonProperty
    @ApiModelProperty(name = "OriContent", value = "OriContent")
    private String OriContent;

}