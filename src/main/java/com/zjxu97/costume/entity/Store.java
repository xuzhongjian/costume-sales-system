package com.zjxu97.costume.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("stores")
@EqualsAndHashCode(callSuper = false)
public class Store extends Model<Store> {
    private Integer id;

    private Byte isDeleted;

    private Date createAt;

    private Date updateAt;

    private Integer districtId;

    private String storeName;

    private String storeAddress;

}