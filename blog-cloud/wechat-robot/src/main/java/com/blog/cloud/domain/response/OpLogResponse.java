package com.blog.cloud.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "OpLogResponse", description = "OpLogResponse")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpLogResponse extends WechatHttpResponseBase {
}