package com.zjxu97.costume.service.commodity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.commons.SaleTypeEnum;
import com.zjxu97.costume.model.Stock;
import com.zjxu97.costume.mapper.CommodityStockMapper;
import com.zjxu97.costume.param.GoodsParam;
import com.zjxu97.costume.param.StockInOutParam;
import com.zjxu97.costume.service.commodity.CommodityStockService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommodityStockServiceImpl extends ServiceImpl<CommodityStockMapper, Stock> implements CommodityStockService {

    @Override
    public Stock getItemComByStore(Integer storeId, Integer itemId) {
        return this.getOne(qw().eq("store_id", storeId).eq("item_id", itemId));
    }

    @Override
    public void changeCommodityStock(GoodsParam goodsParam, Byte saleType) {
        List<Integer> itemIds = goodsParam.getItemIds();
        Integer storeId = goodsParam.getStoreId();
        List<Stock> stocks = new ArrayList<>();
        for (Integer itemId : itemIds) {
            Stock stock =
                    this.getOne(qw().eq("store_id", storeId).eq("item_id", itemId));
            stocks.add(stock);
        }
        if (saleType.equals(SaleTypeEnum.SALE.getValue())) {
            stocks = stocks.stream().peek(commodityStock -> commodityStock.setAmount(commodityStock.getAmount() - 1)).collect(Collectors.toList());
        } else if (saleType.equals(SaleTypeEnum.RETURN.getValue())) {
            stocks = stocks.stream().peek(commodityStock -> commodityStock.setAmount(commodityStock.getAmount() + 1)).collect(Collectors.toList());
        }
        this.saveOrUpdateBatch(stocks);
    }

    @Override
    public Boolean inOutStock(List<StockInOutParam> stockInOuts, Byte inOutType) {
        List<Stock> stocks = new ArrayList<>();
        for (StockInOutParam stockInOut : stockInOuts) {
            Integer itemId = stockInOut.getItemId();
            Integer storeId = stockInOut.getStoreId();
            Integer amount = stockInOut.getAmount();
            Stock stock =
                    this.getOne(qw().eq("store_id", storeId).eq("item_id", itemId));
            if (inOutType.equals(InOutEnum.In.getValue())) {
                stock.setAmount(stock.getAmount() + amount);
            } else {
                stock.setAmount(stock.getAmount() - amount);
            }
            stocks.add(stock);
        }

        return this.saveOrUpdateBatch(stocks);
    }

    public List<Stock> getStoreStocks(List<Integer> itemIds, Integer storeId) {

        return this.list(qw().in(Common.isUsefulList(itemIds), "item_id", itemIds)
                .eq(Common.isUsefulNum(storeId), "store_id", storeId));
    }

    private QueryWrapper<Stock> qw() {
        return new QueryWrapper<>();
    }
}
