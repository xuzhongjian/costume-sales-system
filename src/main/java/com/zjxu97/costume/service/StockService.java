package com.zjxu97.costume.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.Stock;
import com.zjxu97.costume.model.vo.StockVo;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface StockService extends IService<Stock> {

    IPage<Stock> getStockByStore(int current, int size, int storeId);

    List<StockVo> getStockVoFromModelList(List<Stock> stockList);
}
