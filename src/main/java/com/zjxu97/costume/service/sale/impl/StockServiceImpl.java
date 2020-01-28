package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.mapper.sale.StockMapper;
import com.zjxu97.costume.model.dto.StockDisplayDTO;
import com.zjxu97.costume.model.dto.StockIdentifyDTO;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.service.sale.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Override
    public List<StockDisplayDTO> getStockByStore(Integer storeId) {
        return this.list(qw().eq(Common.isUsefulNum(storeId), "store_id", storeId)).stream()
                .sorted(Comparator.comparing(Stock::getItemDetailId)).map(stock -> {
                    StockDisplayDTO stockDisplayDTO = new StockDisplayDTO();
                    BeanUtils.copyProperties(stock, stockDisplayDTO);
                    return stockDisplayDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<StockDisplayDTO> getStockByItemList(List<Integer> itemList, Integer storeId) {
        return this.list(qw().eq(Common.isUsefulNum(storeId), "store_id", storeId)
                .in(Common.isUsefulList(itemList), "item_id", itemList)
        ).stream().map(stock -> {
            StockDisplayDTO stockDisplayDTO = new StockDisplayDTO();
            BeanUtils.copyProperties(stock, stockDisplayDTO);
            return stockDisplayDTO;
        }).collect(Collectors.toList());
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

    private QueryWrapper<Stock> qw() {
        return new QueryWrapper<>();
    }
}
