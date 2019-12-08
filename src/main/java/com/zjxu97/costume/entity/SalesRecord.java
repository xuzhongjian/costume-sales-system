package com.zjxu97.costume.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
@Data
@TableName("sales_record")
@EqualsAndHashCode(callSuper = false)
public class SalesRecord extends Model<SalesRecord> {
    private Integer id;

    private Byte isDeleted;

    private Date createAt;

    private Date updateAt;

    private Integer storeId;

    private Integer itemId;
}