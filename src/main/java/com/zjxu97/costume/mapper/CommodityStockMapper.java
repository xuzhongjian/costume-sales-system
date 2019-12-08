package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.entity.CommodityStock;

public interface CommodityStockMapper extends BaseMapper<CommodityStock> {
    int deleteByPrimaryKey(Integer id);

    int insert(CommodityStock record);

    int insertSelective(CommodityStock record);

    CommodityStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityStock record);

    int updateByPrimaryKey(CommodityStock record);
}