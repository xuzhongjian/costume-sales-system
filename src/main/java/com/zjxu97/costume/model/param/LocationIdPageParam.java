package com.zjxu97.costume.model.param;

import com.zjxu97.costume.commons.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zjxu97
 * @date 2020/1/31 20:38
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class LocationIdPageParam extends PageParam {

    @ApiModelProperty("位置的id")
    private Integer locationId;
}
