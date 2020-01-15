package com.zjxu97.costume.model;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author zjxu97
 * @date 2020/1/16 00:33
 */
@Data
@TableName("users")
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_deleted")
    private Byte isDeleted;

    @TableField(value = "create_at")
    private Date createAt;

    @TableField(value = "update_at")
    private Date updateAt;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "nick_name")
    private String nickName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "store_id")
    private String storeId;
}
