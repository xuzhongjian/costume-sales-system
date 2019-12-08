package com.zjxu97.costume.mapper;

import com.zjxu97.costume.entity.CommodityStock;

public interface CommodityStocksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommodityStock record);

    int insertSelective(CommodityStock record);

    CommodityStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityStock record);

    int updateByPrimaryKey(CommodityStock record);
}