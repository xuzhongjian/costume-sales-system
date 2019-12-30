package com.zjxu97.costume.service.commodity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.CommodityStock;
import com.zjxu97.costume.mapper.CommodityStockMapper;
import com.zjxu97.costume.model.Item;
import com.zjxu97.costume.service.commodity.CommodityStockService;
import com.zjxu97.costume.vo.CommodityStockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CommodityStockServiceImpl extends ServiceImpl<CommodityStockMapper, CommodityStock> implements CommodityStockService {
    public CommodityStockVo getItemComByStore(Integer storeId, Integer itemId) {
        CommodityStock commodityStock = this.getBaseMapper().selectOne(qw().eq("store_id", storeId).eq("item_id", itemId));
        CommodityStockVo commodityStockVo = new CommodityStockVo();
        BeanUtils.copyProperties(commodityStock, commodityStockVo);
        return commodityStockVo;
    }

    private QueryWrapper<CommodityStock> qw() {
        return new QueryWrapper<>();
    }
}
