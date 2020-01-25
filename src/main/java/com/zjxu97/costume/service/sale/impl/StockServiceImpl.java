package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.sale.StockMapper;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.service.sale.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    private QueryWrapper<Stock> qw() {
        return new QueryWrapper<>();
    }
}
