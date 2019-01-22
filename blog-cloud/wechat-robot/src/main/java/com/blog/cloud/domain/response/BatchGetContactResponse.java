package com.blog.cloud.domain.response;

import com.blog.cloud.domain.shared.Contact;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "BatchGetContactResponse", description = "BatchGetContactResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchGetContactResponse extends WechatHttpResponseBase {

    @JsonProperty
    @ApiModelProperty(name = "ContactList", value = "ContactList")
    private Set<Contact> ContactList;

    @JsonProperty
    @ApiModelProperty(name = "Count", value = "Count")
    private Integer Count;

}