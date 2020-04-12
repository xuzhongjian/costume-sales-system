package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.mapper.StockMapper;
import com.zjxu97.costume.model.entity.ItemDetail;
import com.zjxu97.costume.model.entity.Stock;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.model.vo.StockVo;
import com.zjxu97.costume.model.vo.StoreVo;
import com.zjxu97.costume.service.ItemDetailService;
import com.zjxu97.costume.service.StockService;
import com.zjxu97.costume.service.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Resource
    ItemDetailService itemDetailService;

    @Resource
    StoreService storeService;


    @Override
    public IPage<Stock> getStockByStore(int current, int size, int storeId) {
        QueryWrapper<Stock> qw = qw().eq(Common.isUsefulNum(storeId), "store_id", storeId);
        Page<Stock> page = new Page<>();
        page.setSize(size);
        page.setCurrent(current);
        return this.page(page, qw);
    }

    @Override
    public List<StockVo> getStockVoFromModelList(List<Stock> stockList) {
        if (Common.isUselessList(stockList)) {
            return Lists.newArrayList();
        }

        // 具体商品的id
        List<Integer> itemDetailIdList = stockList.stream().map(Stock::getItemDetailId).collect(Collectors.toList());
        // 具体商品的list
        List<ItemDetail> itemDetailList = new ArrayList<>(itemDetailService.listByIds(itemDetailIdList));
        // 转换成具体商品的vo-list
        List<ItemDetailVo> itemDetailVoList = itemDetailService.getItemDetailVoFromModelList(itemDetailList);
        // 转换成id的map
        Map<Integer, ItemDetailVo> itemDetailVoMap = new HashMap<>();
        itemDetailVoList.forEach(itemDetailVo -> itemDetailVoMap.put(itemDetailVo.getId(), itemDetailVo));

        //获取商店的map
        List<Integer> storeIdList = stockList.stream().map(Stock::getStoreId).collect(Collectors.toList());
        List<Store> storeList = new ArrayList<>(storeService.listByIds(storeIdList));
        List<StoreVo> storeVoList = storeService.getStoreVoFromModelList(storeList);
        Map<Integer, StoreVo> storeVoHashMap = new HashMap<>();
        storeVoList.forEach(storeVo -> storeVoHashMap.put(storeVo.getId(), storeVo));

        return stockList.stream().map(stock -> {
            StockVo stockVo = new StockVo();
            BeanUtils.copyProperties(stock, stockVo);
            stockVo.setItemDetailVo(itemDetailVoMap.get(stock.getItemDetailId()));
            stockVo.setStoreVo(storeVoHashMap.get(stock.getStoreId()));
            return stockVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<Stock> qw() {
        return new QueryWrapper<>();
    }
}
