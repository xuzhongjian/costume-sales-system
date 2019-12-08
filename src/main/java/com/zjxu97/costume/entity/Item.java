package com.zjxu97.costume.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("items")
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

    @TableField(value = "costume_id")
    private Integer costumeId;

    @TableField(value = "sex")
    private Byte Sex;

    @TableField(value = "item_size")
    private Byte itemSize;

}