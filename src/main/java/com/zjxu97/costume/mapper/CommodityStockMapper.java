package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.CommodityStock;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommodityStockMapper extends BaseMapper<CommodityStock> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CommodityStock record);

    CommodityStock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommodityStock record);

    int updateByPrimaryKey(CommodityStock record);
}