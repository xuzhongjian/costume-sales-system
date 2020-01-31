package com.zjxu97.costume.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class StockVo {

    @ApiModelProperty(value = "库存的id")
    private Integer id;

    @ApiModelProperty(value = "商店信息")
    private StoreVo storeVo;

    @ApiModelProperty(value = "库存信息的详细")
    private ItemDetailVo itemDetailVo;

    @ApiModelProperty(value = "库存数量")
    private Integer amount;
}