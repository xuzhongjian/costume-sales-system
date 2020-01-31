package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.commons.PageParam;
import com.zjxu97.costume.mapper.sale.StockMapper;
import com.zjxu97.costume.model.dto.StockDisplayDTO;
import com.zjxu97.costume.model.dto.StockIdentifyDTO;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.model.param.StoreStockPageParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.model.vo.StockVo;
import com.zjxu97.costume.model.vo.StoreVo;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.sale.StockService;
import com.zjxu97.costume.service.store.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Resource
    ItemDetailService itemDetailService;

    @Resource
    StoreService storeService;

    @Override
    public IPage<Stock> getStockByStore(StoreStockPageParam param) {
        Integer storeId = param.getStoreId();
        QueryWrapper<Stock> qw = qw().eq(Common.isUsefulNum(storeId), "store_id", storeId);
        Page<Stock> page = new Page<>();
        BeanUtils.copyProperties(param, page);
        return this.page(page, qw);
    }

    @Override
    public IPage<Stock> getStockByItemList(List<Integer> itemIdList, Integer storeId, PageParam pageParam) {
        Page<Stock> page = new Page<>();
        BeanUtils.copyProperties(pageParam, page);
        QueryWrapper<Stock> qw = qw().eq(Common.isUsefulNum(storeId), "store_id", storeId)
                .in(Common.isUsefulList(itemIdList), "item_id", itemIdList);
        return this.page(page, qw);
    }

    @Override
    public void updateStockAmount(List<StockInOutDTO> stockInOutDTOList) {
        List<StockIdentifyDTO> stockIdentifyDTOList = new ArrayList<>();
        HashMap<StockIdentifyDTO, StockInOutDTO> identifyMap = new HashMap<>();
        stockInOutDTOList.forEach(stockDTO -> {
            StockIdentifyDTO stockIdentifyDTO = new StockIdentifyDTO();
            BeanUtils.copyProperties(stockDTO, stockIdentifyDTO);
            stockIdentifyDTOList.add(stockIdentifyDTO);
            identifyMap.put(stockIdentifyDTO, stockDTO);
        });

        List<Stock> needUpdateList = stockIdentifyDTOList.stream().map(identify ->
                this.getOne(qw()
                        .eq(Common.isUsefulNum(identify.getItemDetailId()), "item_detail_id", identify.getItemDetailId())
                        .eq(Common.isUsefulNum(identify.getStoreId()), "store_id", identify.getStoreId())
                        .orderByDesc("update_at")
                )
        ).collect(Collectors.toList());

        needUpdateList.forEach(stock -> {
            StockIdentifyDTO identify = new StockIdentifyDTO();
            BeanUtils.copyProperties(stock, identify);
            StockInOutDTO stockInOutDTO = identifyMap.get(identify);
            if (Objects.nonNull(stockInOutDTO)) {
                Integer amount = stock.getAmount();
                Byte inoutType = stockInOutDTO.getInoutType();
                if (inoutType.equals(InOutEnum.IN.getValue())) {
                    amount += stockInOutDTO.getAmount();
                } else if (inoutType.equals(InOutEnum.OUT.getValue())) {
                    amount -= stockInOutDTO.getAmount();
                }
                stock.setAmount(amount);
            }
        });

        this.saveBatch(needUpdateList);
    }

    @Override
    public List<StockVo> getItemDetailVoFromEntityList(List<Stock> stockList) {

        //获取商品详情的map
        List<Integer> itemDetailIdList = stockList.stream().map(Stock::getItemDetailId).collect(Collectors.toList());
        List<ItemDetail> itemDetailList = new ArrayList<>(itemDetailService.listByIds(itemDetailIdList));
        List<ItemDetailVo> itemDetailVoList = itemDetailService.getItemDetailVoFromEntityList(itemDetailList);
        Map<Integer, ItemDetailVo> itemDetailVoMap = new HashMap<>();
        itemDetailVoList.forEach(itemDetailVo -> itemDetailVoMap.put(itemDetailVo.getId(), itemDetailVo));


        //获取商店的map
        List<Integer> storeIdList = stockList.stream().map(Stock::getStoreId).collect(Collectors.toList());
        List<Store> storeList = new ArrayList<>(storeService.listByIds(storeIdList));
        List<StoreVo> storeVoList = storeService.getStoreVoFromEntityList(storeList);
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
