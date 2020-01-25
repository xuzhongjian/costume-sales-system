package com.zjxu97.costume.service.commodity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.Stock;
import com.zjxu97.costume.param.GoodsParam;
import com.zjxu97.costume.param.StockInOutParam;

import java.util.List;

public interface CommodityStockService extends IService<Stock> {
    Stock getItemComByStore(Integer storeId, Integer itemId);

    void changeCommodityStock(GoodsParam goodsParam, Byte saleType);

    Boolean inOutStock(List<StockInOutParam> stockInOuts, Byte inOutType);

    List<Stock> getStoreStocks(List<Integer> itemIds, Integer storeId);
}
