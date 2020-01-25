package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.item.Item;
import com.zjxu97.costume.model.sale.SaleRecord;
import com.zjxu97.costume.mapper.sale.SaleRecordMapper;
import com.zjxu97.costume.param.GoodsParam;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.service.sale.SaleRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordMapper, SaleRecord> implements SaleRecordService {

    @Resource
    ItemService itemService;

    @Override
    public Integer recordSales(GoodsParam goodsParam, Byte saleType) {
        List<Integer> itemIds = goodsParam.getItemIds();
        Integer storeId = goodsParam.getStoreId();
        List<SaleRecord> saleRecords = itemIds.stream().map(itemId -> {
            SaleRecord saleRecord = new SaleRecord();
            saleRecord.setSaleType(saleType);
            saleRecord.setStoreId(storeId);
            saleRecord.setItemId(itemId);
            return saleRecord;
        }).collect(Collectors.toList());
        this.saveBatch(saleRecords);

        //计算涉及到的总价格
        int ans = 0;
        List<Integer> priceList = itemService.listByIds(itemIds).stream().map(Item::getPrice).collect(Collectors.toList());
        for (Integer price : priceList) {
            ans += price;
        }
        return ans;
    }

    private QueryWrapper<SaleRecord> qw() {
        return new QueryWrapper<>();
    }
}
