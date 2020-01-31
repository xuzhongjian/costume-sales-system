package com.zjxu97.costume.model.param;

import com.zjxu97.costume.commons.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zjxu97
 * @date 2020/1/20 10:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryStockPageParam extends PageParam {

    @ApiModelProperty(value = "商店id")
    private Integer storeId;

    @ApiModelProperty(value = "关键字")
    private String itemKeyWords;

    @ApiModelProperty(value = "类别id")
    private Integer itemTypeId;

    @ApiModelProperty(value = "大小id")
    private Integer itemSizeId;
}
