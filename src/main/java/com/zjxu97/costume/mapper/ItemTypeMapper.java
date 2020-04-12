package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.entity.ItemType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Mapper
public interface ItemTypeMapper extends BaseMapper<ItemType> {
    /**
     * 根据商品的详细的id获取所属于的大类
     *
     * @param itemDetailId 商品的详细的id
     * @return 商品大类
     */
    ItemType getItemTypeByItemDetailId(@Param("item_detail_id") Integer itemDetailId);
}