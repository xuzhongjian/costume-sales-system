package com.zjxu97.costume.mapper;

import com.zjxu97.costume.entity.CostumeType;

public interface CostumeTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CostumeType record);

    int insertSelective(CostumeType record);

    CostumeType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CostumeType record);

    int updateByPrimaryKey(CostumeType record);
}