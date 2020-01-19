package com.zjxu97.costume.service.commodity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.common.SaleTypeEnum;
import com.zjxu97.costume.model.CommodityStock;
import com.zjxu97.costume.mapper.CommodityStockMapper;
import com.zjxu97.costume.model.Item;
import com.zjxu97.costume.param.GoodsParam;
import com.zjxu97.costume.service.commodity.CommodityStockService;
import com.zjxu97.costume.vo.CommodityStockVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommodityStockServiceImpl extends ServiceImpl<CommodityStockMapper, CommodityStock> implements CommodityStockService {
    public CommodityStockVo getItemComByStore(Integer storeId, Integer itemId) {
        CommodityStock commodityStock = this.getBaseMapper().selectOne(qw().eq("store_id", storeId).eq("item_id", itemId));
        CommodityStockVo commodityStockVo = new CommodityStockVo();
        BeanUtils.copyProperties(commodityStock, commodityStockVo);
        return commodityStockVo;
    }

    @Override
    public void changeCommodityStock(GoodsParam goodsParam, Byte saleType) {
        List<Integer> itemIds = goodsParam.getItemIds();
        Integer storeId = goodsParam.getStoreId();
        List<CommodityStock> commodityStocks = new ArrayList<>();
        for (Integer itemId : itemIds) {
            CommodityStock commodityStock =
                    this.getOne(qw().eq("store_id", storeId).eq("item_id", itemId));
            commodityStocks.add(commodityStock);
        }
        if (saleType.equals(SaleTypeEnum.SALE.getValue())) {
            commodityStocks = commodityStocks.stream().peek(commodityStock -> commodityStock.setAmount(commodityStock.getAmount() - 1)).collect(Collectors.toList());
        } else if (saleType.equals(SaleTypeEnum.RETURN.getValue())) {
            commodityStocks = commodityStocks.stream().peek(commodityStock -> commodityStock.setAmount(commodityStock.getAmount() + 1)).collect(Collectors.toList());
        }
        this.saveOrUpdateBatch(commodityStocks);
    }

    private QueryWrapper<CommodityStock> qw() {
        return new QueryWrapper<>();
    }
}
