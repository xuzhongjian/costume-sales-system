package com.zjxu97.costume.commons;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/30 13:14
 */
@Data
@ApiModel
public class PageList<T> {

    /**
     * 查询的数据列表
     */
    @ApiModelProperty(value = "查询的数据列表")
    private List<T> records = Collections.emptyList();

    /**
     * 总数
     */
    @ApiModelProperty(value = "总页数")
    private long total;
    /**
     * 每页显示条数，默认 10
     */
    @ApiModelProperty(value = "每页显示条数，默认 10")
    private long size;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页号")
    private long current;
}
