package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.StockRecordMapper;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.StockRecord;
import com.zjxu97.costume.service.StockRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class StockRecordServiceImpl extends ServiceImpl<StockRecordMapper, StockRecord> implements StockRecordService {

    @Override
    public void stockRecord(List<StockInOutDTO> stockInOutDTOList) {
        List<StockRecord> stockRecordList = stockInOutDTOList.stream().map(stockRecordDTO -> {
            StockRecord stockRecord = new StockRecord();
            BeanUtils.copyProperties(stockRecordDTO, stockRecord);
            return stockRecord;
        }).collect(Collectors.toList());
        this.saveBatch(stockRecordList);
    }

    private QueryWrapper<StockRecord> qw() {
        return new QueryWrapper<>();
    }
}
