package com.zjxu97.costume.entity;

import java.util.Date;

public class Item {
    private Integer id;

    private Byte isDeleted;

    private Date createAt;

    private Date updateAt;

    private String itemName;

    private Integer costumeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getCostumeId() {
        return costumeId;
    }

    public void setCostumeId(Integer costumeId) {
        this.costumeId = costumeId;
    }
}