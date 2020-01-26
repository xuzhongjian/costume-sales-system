package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.sale.StockRecordMapper;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.sale.StockRecord;
import com.zjxu97.costume.service.sale.StockRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockRecordServiceImpl extends ServiceImpl<StockRecordMapper, StockRecord> implements StockRecordService {

    public void stockRecord(List<StockInOutDTO> stockInOutDTOList) {
        List<StockRecord> stockRecordList = stockInOutDTOList.stream().map(stockRecordDTO -> {
            StockRecord stockRecord = new StockRecord();
            BeanUtils.copyProperties(stockRecordDTO,stockRecord);
            return stockRecord;
        }).collect(Collectors.toList());
        this.saveBatch(stockRecordList);
    }

    private QueryWrapper<StockRecord> qw() {
        return new QueryWrapper<>();
    }
}
