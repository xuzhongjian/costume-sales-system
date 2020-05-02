package com.zjxu97.costume.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zjxu97
 * @date 2019/12/29 23:54
 */
@Data
@ApiModel(value = "类别")
public class ItemTypeVo {

    @ApiModelProperty(value = "类别的id")
    private Integer id;

    @ApiModelProperty(value = "类别的名称")
    private String typeName;
}