package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Control;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.DataElement;
import com.zjxu97.costume.commons.DisplayTypeConstants;
import com.zjxu97.costume.mapper.SaleRecordMapper;
import com.zjxu97.costume.model.entity.SaleRecord;
import com.zjxu97.costume.service.SaleRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordMapper, SaleRecord> implements SaleRecordService {

    @Override
    public List<DataElement> getDataList(Control control, String from, String to, String xType, String xValue, String yValue) {

        if (yValue.equals(DisplayTypeConstants.SALE_COUNT)) {
            return this.getCountDataList(control, from, to, xType, xValue);
        } else if (yValue.equals(DisplayTypeConstants.SALE_AMOUNT)) {
            return this.getAmountDataList(control, from, to, xType, xValue);
        }

        return null;
    }

    private List<DataElement> getCountDataList(Control control, String from, String to, String xType, String xValue) {
        if (xType.equals(CostumeConstants.DATE)) {
            return this.getBaseMapper().getDateCount(control, from, to, xValue);
        }
        return null;
    }

    private List<DataElement> getAmountDataList(Control control, String from, String to, String xType, String xValue) {
        if (xType.equals(CostumeConstants.DATE)) {
            return this.getBaseMapper().getDateAmount(control, from, to, xValue);
        }
        return null;
    }

    private QueryWrapper<SaleRecord> qw() {
        return new QueryWrapper<>();
    }
}
