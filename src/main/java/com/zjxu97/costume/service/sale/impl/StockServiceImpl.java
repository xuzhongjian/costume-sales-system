package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.mapper.sale.StockMapper;
import com.zjxu97.costume.model.dto.StockDTO;
import com.zjxu97.costume.model.dto.StockIdentifyDTO;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.service.sale.StockService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {
    public void updateStockAmount(List<StockDTO> stockDTOList) {
        List<StockIdentifyDTO> stockIdentifyDTOList = new ArrayList<>();
        HashMap<StockIdentifyDTO, StockDTO> identifyMap = new HashMap<>();
        stockDTOList.forEach(stockDTO -> {
            StockIdentifyDTO stockIdentifyDTO = new StockIdentifyDTO();
            BeanUtils.copyProperties(stockDTO, stockIdentifyDTO);
            stockIdentifyDTOList.add(stockIdentifyDTO);
            identifyMap.put(stockIdentifyDTO, stockDTO);
        });

        List<Stock> needUpdateList = stockIdentifyDTOList.stream().map(identify ->
                this.getOne(
                        qw().eq(Common.isUsefulNum(identify.getItemId()), "item_id", identify.getItemId())
                                .eq(Common.isUsefulNum(identify.getStoreId()), "store_id", identify.getStoreId())
                                .orderByDesc("update_at")
                )
        ).collect(Collectors.toList());

        needUpdateList.forEach(stock -> {
            StockIdentifyDTO identify = new StockIdentifyDTO();
            BeanUtils.copyProperties(stock, identify);
            StockDTO stockDTO = identifyMap.get(identify);
            if (Objects.nonNull(stockDTO)) {
                Integer amount = stock.getAmount();
                Byte inoutType = stockDTO.getInoutType();
                if (inoutType.equals(InOutEnum.IN.getValue())) {
                    amount += stockDTO.getAmount();
                } else if (inoutType.equals(InOutEnum.OUT.getValue())) {
                    amount -= stockDTO.getAmount();
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
