package com.zjxu97.costume.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("store")
@EqualsAndHashCode(callSuper = false)
public class Store extends Model<Store> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Byte isDeleted;


    @TableField(value = "create_at")
    private Date createAt;

    @TableField(value = "update_at")
    private Date updateAt;

    @TableField(value = "district_id")
    private Integer districtId;

    @TableField(value = "store_name")
    private String storeName;

    @TableField(value = "store_address")
    private String storeAddress;

}