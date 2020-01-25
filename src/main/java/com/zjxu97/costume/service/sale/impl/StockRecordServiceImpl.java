package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.sale.StockRecordMapper;
import com.zjxu97.costume.model.sale.StockRecord;
import com.zjxu97.costume.service.sale.StockRecordService;
import org.springframework.stereotype.Service;

@Service
public class StockRecordServiceImpl extends ServiceImpl<StockRecordMapper, StockRecord> implements StockRecordService {

    private QueryWrapper<StockRecord> qw() {
        return new QueryWrapper<>();
    }
}
