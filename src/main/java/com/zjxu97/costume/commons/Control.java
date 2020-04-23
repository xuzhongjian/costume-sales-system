package com.zjxu97.costume.commons;

import com.zjxu97.costume.model.entity.Item;
import com.zjxu97.costume.model.entity.ItemSize;
import com.zjxu97.costume.model.entity.ItemType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zjxu97
 * @date 2020-04-22 20:42
 */
@Data
@ApiModel("控制体")
public class Control {

    /**
     * @see LocationClassConstants
     */
    @ApiModelProperty(value = "控制-地点")
    private String controlLocation;

    /**
     * @see ItemType
     */
    @ApiModelProperty(value = "控制-商品类别")
    private String controlItemType;

    /**
     * @see Item
     */
    @ApiModelProperty(value = "控制-商品")
    private String controlItem;

    /**
     * @see ItemSize
     */
    @ApiModelProperty(value = "控制-大小")
    private String controlSize;


    /**
     * @see ItemType#getSex()
     */
    @ApiModelProperty(value = "控制-性别")
    private String controlSex;
}

