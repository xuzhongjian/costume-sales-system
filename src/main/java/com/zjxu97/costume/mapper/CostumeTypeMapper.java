package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.entity.CostumeType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CostumeTypeMapper extends BaseMapper<CostumeType> {
    int deleteByPrimaryKey(Integer id);

    int insert(CostumeType record);

    int insertSelective(CostumeType record);

    CostumeType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CostumeType record);

    int updateByPrimaryKey(CostumeType record);
}