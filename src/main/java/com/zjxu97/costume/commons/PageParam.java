package com.zjxu97.costume.commons;

import io.swagger.annotations.ApiModel;
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
    private long size = 10;


    /**
     * 当前页
     */
    private long current = 1;
}
