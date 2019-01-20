package com.blog.cloud.domain.request;

import com.blog.cloud.domain.shared.StatReport;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lxw
 * @date 2019/1/20
 * @description
 */
@Data
@ApiModel(value = "StatReportRequest", description = "StatReportRequest")
public class StatReportRequest {

    @ApiModelProperty(name = "BaseRequest", value = "BaseRequest")
    private BaseRequest BaseRequest;

    @ApiModelProperty(name = "Count", value = "Count")
    private Integer Count;

    @ApiModelProperty(name = "List", value = "List")
    private List<StatReport> List;
}
