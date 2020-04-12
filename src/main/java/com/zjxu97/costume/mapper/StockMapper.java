package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Mapper
public interface StockMapper extends BaseMapper<Stock> {
    /**
     * 更新库存数量
     *
     * @param stockInOutDTOList 更新的数据
     */
    void updateStockAmount(List<StockInOutDTO> stockInOutDTOList);
}