package com.zjxu97.costume.model.param;

import com.zjxu97.costume.commons.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author zjxu97
 * @date 2020/1/30 13:08
 */
@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class ItemTypeDetailPageParam extends PageParam {
    @ApiModelProperty
    private Integer typeId;
}

