package com.zjxu97.costume.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zjxu97
 * @date 2019/12/29 23:54
 */
@Data
@ApiModel(value = "大小")
public class ItemSizeVo {
    @ApiModelProperty(value = "大小的id")
    private Integer id;

    @ApiModelProperty(value = "大小，数字为鞋码")
    private String displayName;
}