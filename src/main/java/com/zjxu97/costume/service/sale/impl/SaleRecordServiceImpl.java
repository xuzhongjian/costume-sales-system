package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.sale.SaleRecordMapper;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.entity.sale.SaleRecord;
import com.zjxu97.costume.model.param.GoodParam;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.sale.SaleRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordMapper, SaleRecord> implements SaleRecordService {

    @Resource
    ItemDetailService itemDetailService;

    @Override
    public Integer recordSales(List<GoodParam> goodParamList) {
        List<SaleRecord> saleRecords = goodParamList.stream().map(goodParam -> {
            SaleRecord saleRecord = new SaleRecord();
            BeanUtils.copyProperties(goodParam, saleRecord);
            return saleRecord;
        }).collect(Collectors.toList());
        this.saveBatch(saleRecords);

        //计算涉及到的总价格
        List<Integer> itemDetailIdList = goodParamList.stream().map(GoodParam::getItemDetailId).collect(Collectors.toList());
        long count = itemDetailService.listByIds(itemDetailIdList).stream().map(ItemDetail::getPrice).count();
        return (int) count;
    }

    private QueryWrapper<SaleRecord> qw() {
        return new QueryWrapper<>();
    }
}
