package com.zjxu97.costume.mapper.sale;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.dto.StockDTO;
import com.zjxu97.costume.model.entity.sale.Stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper extends BaseMapper<Stock> {
    void updateStockAmount(List<StockDTO> stockDTOList);
}