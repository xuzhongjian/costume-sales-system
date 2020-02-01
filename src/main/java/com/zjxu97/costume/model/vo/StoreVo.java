package com.zjxu97.costume.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "店铺信息")
public class StoreVo {

    @ApiModelProperty("店铺id")
    private Integer id;

    @ApiModelProperty("所属县区的id")
    private Integer districtId;

    @ApiModelProperty("店铺名称")
    private String storeName;

    @ApiModelProperty("店铺地址")
    private String storeAddress;
}