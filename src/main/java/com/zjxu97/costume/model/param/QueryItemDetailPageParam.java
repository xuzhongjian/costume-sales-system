package com.zjxu97.costume.model.param;

import com.zjxu97.costume.commons.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zjxu97
 * @date 2020/1/27 12:09
 */
@Data
@ApiModel(value = "关键词、类型、大小")
@EqualsAndHashCode(callSuper = true)
public class QueryItemDetailPageParam extends PageParam {
    @ApiModelProperty(value = "关键字")
    private String itemKeyWords;

    @ApiModelProperty(value = "类别的id")
    private Integer itemTypeId;

    @ApiModelProperty(value = "大小")
    private Integer itemSizeId;
}