package com.zjxu97.costume.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@TableName("district")
@EqualsAndHashCode(callSuper = false)
public class District extends Model<District> {
    private Integer id;

    private Byte isDeleted;

    private Date createAt;

    private Date updateAt;

    private String districtName;

    private Integer cityId;
}