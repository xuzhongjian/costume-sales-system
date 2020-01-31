package com.zjxu97.costume.model.param;

import com.zjxu97.costume.commons.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zjxu97
 * @date 2020/1/31 11:31
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class ItemDetailPageParam extends PageParam {
    @ApiModelProperty(value = "商品的模糊id 不带有size")
    private Integer itemId;
}
