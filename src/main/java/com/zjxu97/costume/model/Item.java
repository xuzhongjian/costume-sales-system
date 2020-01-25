package com.zjxu97.costume.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("item")
@EqualsAndHashCode(callSuper = false)
public class Item extends Model<Item> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Byte isDeleted;

    @TableField(value = "create_at")
    private Date createAt;

    @TableField(value = "update_at")
    private Date updateAt;

    @TableField(value = "item_name")
    private String itemName;

    @TableField(value = "item_type_id")
    private Integer itemTypeId;

    @TableField(value = "item_size_id")
    private Integer itemSizeId;

    @TableField(value = "price")
    private Integer price;
}