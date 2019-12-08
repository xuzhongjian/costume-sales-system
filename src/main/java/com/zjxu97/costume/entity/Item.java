package com.zjxu97.costume.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("items")
@EqualsAndHashCode(callSuper = false)
public class Item extends Model<Item> {
    private Integer id;

    private Byte isDeleted;

    private Date createAt;

    private Date updateAt;

    private String itemName;

    private Integer costumeId;
}