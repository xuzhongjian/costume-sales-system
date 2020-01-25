package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.Stock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityStockMapper extends BaseMapper<Stock> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);
}