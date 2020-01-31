package com.zjxu97.costume.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zjxu97
 * @date 2020/1/30 13:14
 */
@Data
@ApiModel
public class PageParam {
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "页容")
    private long size = 10;


    /**
     * 当前页
     */
    @ApiModelProperty(value = "页号")
    private long current = 1;
}
