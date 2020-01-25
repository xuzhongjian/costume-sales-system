package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.ItemType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CostumeTypeMapper extends BaseMapper<ItemType> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ItemType record);

    ItemType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemType record);

    int updateByPrimaryKey(ItemType record);
}