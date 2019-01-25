package com.blog.cloud.domain.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "VerifyUser", description = "VerifyUser")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyUser {

    @JsonProperty
    @ApiModelProperty(name = "Value", value = "Value")
    private String Value;

    @JsonProperty
    @ApiModelProperty(name = "VerifyUserTicket", value = "VerifyUserTicket")
    private String VerifyUserTicket;

}