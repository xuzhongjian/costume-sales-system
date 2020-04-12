package com.zjxu97.costume.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author zjxu97
 * @date 2020/1/27 11:07
 */
@Data
@TableName("item_detail")
public class ItemDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Byte isDeleted;

    @TableField(value = "create_at")
    private Date createAt;

    @TableField(value = "update_at")
    private Date updateAt;

    @TableField(value = "item_id")
    private Integer itemId;

    @TableField(value = "item_size_id")
    private Integer itemSizeId;

    @TableField(value = "price")
    private Integer price;
}
