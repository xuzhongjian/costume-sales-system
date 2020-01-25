package com.zjxu97.costume.model.entity.item;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@Data
@TableName("costume_type")
@EqualsAndHashCode(callSuper = false)
public class ItemType extends Model<ItemType> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Byte isDeleted;


    @TableField(value = "create_at")
    private Date createAt;

    @TableField(value = "update_at")
    private Date updateAt;

    /**
     *
     */
    @TableField(value = "sex")
    private Byte sex;

    @TableField(value = "type_name")
    private String typeName;
}