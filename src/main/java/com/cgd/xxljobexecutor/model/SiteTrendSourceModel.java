package com.cgd.xxljobexecutor.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author 晓果冻
 * @version 1.0
 * @date 2021/7/3 20:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteTrendSourceModel {

    @ApiModelProperty(notes = "应用ID")
    private String siteId;

    @ApiModelProperty(notes = "来源")
    private String sourceType;

    @ApiModelProperty(notes = "浏览量(PV)")
    private Integer pvCount;

    @ApiModelProperty(notes = "访问次数")
    private Integer visitCount;

    @ApiModelProperty(notes = "访客数(UV)")
    private Integer visitorCount;

    @ApiModelProperty(notes = "新访客数")
    private Integer newVisitorCount;

    @ApiModelProperty(notes = "IP 数")
    private Integer ipCount;
}
