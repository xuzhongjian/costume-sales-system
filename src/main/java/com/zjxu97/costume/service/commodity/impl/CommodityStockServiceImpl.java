package com.zjxu97.costume.service.commodity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.CommodityStock;
import com.zjxu97.costume.mapper.CommodityStockMapper;
import com.zjxu97.costume.service.commodity.CommodityStockService;
import org.springframework.stereotype.Service;

@Service
public class CommodityStockServiceImpl extends ServiceImpl<CommodityStockMapper, CommodityStock> implements CommodityStockService {
}
