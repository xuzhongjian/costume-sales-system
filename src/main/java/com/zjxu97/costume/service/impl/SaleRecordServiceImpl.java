package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Control;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.DataElement;
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
        switch (xType) {
            case CostumeConstants.DATE:
                return this.getBaseMapper().getDateData(control, from, to, xValue, yValue);
            case CostumeConstants.LOCATION:
                return this.getBaseMapper().getLocationData(control, from, to, xValue, yValue);
            case CostumeConstants.ITEM_TYPE:
                return this.getBaseMapper().getItemTypeData(control, from, to, yValue);
            case CostumeConstants.ITEM:
                return this.getBaseMapper().getItemData(control, from, to, yValue);
            case CostumeConstants.SIZE:
                return this.getBaseMapper().getSizeData(control, from, to, yValue);
            case CostumeConstants.SEX:
                return this.getBaseMapper().getSexData(control, from, to, yValue);
            default:
                return null;
        }
    }

    private QueryWrapper<SaleRecord> qw() {
        return new QueryWrapper<>();
    }
}
