package com.zjxu97.costume.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Data
@TableName("area")
@EqualsAndHashCode(callSuper = false)
public class Area extends Model<Area> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Byte isDeleted;

    @TableField(value = "create_at")
    private Date createAt;

    @TableField(value = "update_at")
    private Date updateAt;

    @TableField(value = "area_name")
    private String areaName;
}