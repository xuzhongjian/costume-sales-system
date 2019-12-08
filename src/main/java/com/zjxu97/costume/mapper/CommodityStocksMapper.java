package com.zjxu97.costume.mapper;

import com.zjxu97.costume.entity.CommodityStocks;

public interface CommodityStocksMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommodityStocks record);

    int insertSelective(CommodityStocks record);

    CommodityStocks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityStocks record);

    int updateByPrimaryKey(CommodityStocks record);
}