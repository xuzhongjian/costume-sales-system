package com.zjxu97.costume.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("commodity_stocks")
@EqualsAndHashCode(callSuper = false)
public class CommodityStock extends Model<CommodityStock> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Byte isDeleted;


    @TableField(value = "create_at")
    private Date createAt;

    @TableField(value = "update_at")
    private Date updateAt;

    @TableField(value = "store_id")
    private Integer storeId;

    @TableField(value = "item_id")
    private Integer itemId;

    @TableField(value = "amount")
    private Integer amount;
}