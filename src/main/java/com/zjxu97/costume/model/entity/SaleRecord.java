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
@TableName("sale_record")
@EqualsAndHashCode(callSuper = false)
public class SaleRecord extends Model<SaleRecord> {
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

    @TableField(value = "item_detail_id")
    private Integer itemDetailId;

    @TableField(value = "sale_type")
    private Byte saleType;

    @TableField(value = "age")
    private Integer age;
}