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

    @ApiModelProperty(name = "Uin", value = "Uin")
    @JsonProperty
    private String Uin;

    @ApiModelProperty(name = "Sid", value = "Sid")
    @JsonProperty
    private String Sid;

    @ApiModelProperty(name = "Skey", value = "Skey")
    @JsonProperty
    private String Skey;

    @ApiModelProperty(name = "DeviceID", value = "DeviceID")
    @JsonProperty
    private String DeviceID;

}
