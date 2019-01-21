package com.blog.cloud.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lxw
 * @date 2019/1/20
 * @description
 */
@Data
@ApiModel(value = "BaseRequest", description = "BaseRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequest {

    @JsonProperty
    @ApiModelProperty(name = "Uin", value = "Uin")
    private String Uin;

    @JsonProperty
    @ApiModelProperty(name = "Sid", value = "Sid")
    private String Sid;

    @JsonProperty
    @ApiModelProperty(name = "Skey", value = "Skey")
    private String Skey;

    @JsonProperty
    @ApiModelProperty(name = "DeviceID", value = "DeviceID")
    private String DeviceID;

}
