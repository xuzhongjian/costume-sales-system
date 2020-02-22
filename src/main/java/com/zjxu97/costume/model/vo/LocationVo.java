package com.zjxu97.costume.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zjxu97
 * @date 2020/2/1 11:38
 */
@Data
@AllArgsConstructor
@ApiModel(value = "地区的id", description = "如果是area，那么id为0")
public class LocationVo {
    @ApiModelProperty(value = "上级地区的id")
    private Integer parentId;

    @ApiModelProperty(value = "本地区名称")
    private String locationName;

    @ApiModelProperty(value = "本地区id")
    private Integer locationId;
}
