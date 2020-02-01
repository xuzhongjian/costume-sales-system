package com.zjxu97.costume.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zjxu97
 * @date 2020/2/1 11:43
 */
@Data
@ApiModel(value = "地区参数")
public class LocationParam {

    @ApiModelProperty("地区id的等级分别为1234")
    Byte locationClass;

    @ApiModelProperty("地区的id")
    Integer locationId;

}
