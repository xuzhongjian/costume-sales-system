package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.commons.PageParam;
import com.zjxu97.costume.model.dto.StockDisplayDTO;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.model.param.StoreStockPageParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.model.vo.StockVo;

import java.util.List;

public interface StockService extends IService<Stock> {
    void updateStockAmount(List<StockInOutDTO> stockInOutDTOList);

    IPage<Stock> getStockByItemList(List<Integer> itemIdList, Integer storeId, PageParam pageParam);

    IPage<Stock> getStockByStore(StoreStockPageParam storeStockPageParam);

    List<StockVo> getItemDetailVoFromEntityList(List<Stock> stockList);
}
