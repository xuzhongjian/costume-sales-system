package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.dto.StockDisplayDTO;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.sale.Stock;

import java.util.List;

public interface StockService extends IService<Stock> {
    void updateStockAmount(List<StockInOutDTO> stockInOutDTOList);

    List<StockDisplayDTO> getStockByItemList(List<Integer> itemList, Integer storeId, Integer pageNo, Integer pageSize);

    List<StockDisplayDTO> getStockByStore(Integer storeId, Integer pageNo, Integer pageSize);
}
