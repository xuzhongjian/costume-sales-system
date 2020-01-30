package com.zjxu97.costume.mapper.item;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.entity.item.ItemType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemTypeMapper extends BaseMapper<ItemType> {
    ItemType getItemTypeByItemDetailId(@Param("item_detail_id") Integer itemDetailId);
}