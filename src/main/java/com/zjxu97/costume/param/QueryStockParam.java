package com.zjxu97.costume.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

/**
 * @author zjxu97
 * @date 2020/1/20 10:25
 */
@ApiModel(value = "查询参数")
public class QueryStockParam {
    @ApiParam(value = "店铺id")
    private Integer storeId;
    @ApiParam(value = "性别")
    private Byte sex;
    @ApiParam(value = "种类id")
    private Integer costumeId;
    @ApiParam(value = "商品搜索关键字")
    private String itemKeyWords;
}
