package com.blog.cloud.domain.request;

import com.blog.cloud.domain.shared.VerifyUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "VerifyUserRequest", description = "VerifyUserRequest")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyUserRequest {
    @JsonProperty
    @ApiModelProperty(name = "BaseRequest", value = "BaseRequest")
    private BaseRequest BaseRequest;
    
    @JsonProperty
    @ApiModelProperty(name = "Opcode", value = "Opcode")
    private Integer Opcode;
    
    @JsonProperty
    @ApiModelProperty(name = "SceneList", value = "SceneList")
    private Integer[] SceneList;

    @JsonProperty
    @ApiModelProperty(name = "SceneListCount", value = "SceneListCount")
    private Integer SceneListCount;

    @JsonProperty
    @ApiModelProperty(name = "skey", value = "skey")
    private String skey;

    @JsonProperty
    @ApiModelProperty(name = "VerifyContent", value = "VerifyContent")
    private String VerifyContent;

    @JsonProperty
    @ApiModelProperty(name = "VerifyUserList", value = "VerifyUserList")
    private List<VerifyUser> VerifyUserList;

    @JsonProperty
    @ApiModelProperty(name = "VerifyUserListSize", value = "VerifyUserListSize")
    private Integer VerifyUserListSize;

}