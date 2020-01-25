package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.mapper.sale.StockMapper;
import com.zjxu97.costume.model.dto.StockDTO;
import com.zjxu97.costume.model.dto.StockIdentifyDTO;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.service.sale.StockService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {
    public void updateStockAmount(List<StockDTO> stockDTOList) {
        List<StockIdentifyDTO> stockIdentifyDTOList = new ArrayList<>();
        stockDTOList.forEach(stockDTO -> {
            StockIdentifyDTO stockIdentifyDTO = new StockIdentifyDTO();
            stockIdentifyDTO.setItemId(stockDTO.getItemId());
            stockIdentifyDTO.setStoreId(stockDTO.getStoreId());
            stockIdentifyDTOList.add(stockIdentifyDTO);
        });

        List<Stock> needUpdate = stockIdentifyDTOList.stream().map(identify ->
                this.getOne(qw().eq(Common.isUsefulNum(identify.getItemId()), "item_id", identify.getItemId())
                        .eq(Common.isUsefulNum(identify.getStoreId()), "store_id", identify.getStoreId())
                        .orderByDesc("update_at"))
        ).collect(Collectors.toList());
        needUpdate.forEach(stock -> {});

    }

    private QueryWrapper<Stock> qw() {
        return new QueryWrapper<>();
    }
}
