package com.blog.cloud.domain.response;

import com.blog.cloud.domain.shared.Contact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "GetContactResponse", description = "GetContactResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetContactResponse extends WechatHttpResponseBase {

    @JsonProperty
    @ApiModelProperty(name = "MemberCount", value = "MemberCount")
    private Integer MemberCount;

    @JsonProperty
    @ApiModelProperty(name = "MemberList", value = "MemberList")
    private Set<Contact> MemberList;

    @JsonProperty
    @ApiModelProperty(name = "Seq", value = "Seq")
    private Long Seq;

}
