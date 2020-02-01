package com.zjxu97.costume.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zjxu97
 * @date 2020/1/27 11:07
 */
@Data
@ApiModel(value = "商品详细")
public class ItemDetailVo {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品的模糊id 不包含型号和大小")
    private Integer itemId;

    @ApiModelProperty(value = "商品的模糊id 不包含型号和大小")
    private String itemTypeName;

    @ApiModelProperty(value = "商品的大小的id")
    private Integer itemSizeId;

    @ApiModelProperty(value = "商品的价格")
    private Integer price;

    @ApiModelProperty(value = "商品的大小")
    private String itemSize;

    @ApiModelProperty(value = "商品的名称")
    private String itemName;

    @ApiModelProperty(value = "性别")
    private String sexString;

}
