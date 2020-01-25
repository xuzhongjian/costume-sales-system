package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.dto.StockDTO;
import com.zjxu97.costume.model.entity.sale.Stock;

import java.util.List;

public interface StockService extends IService<Stock> {
    void updateStockAmount(List<StockDTO> stockDTOList);
}
