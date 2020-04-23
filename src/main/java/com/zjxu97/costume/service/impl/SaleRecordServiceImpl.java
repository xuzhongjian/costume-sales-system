package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.commons.Control;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.DataElement;
import com.zjxu97.costume.mapper.SaleRecordMapper;
import com.zjxu97.costume.model.entity.ItemSize;
import com.zjxu97.costume.model.entity.SaleRecord;
import com.zjxu97.costume.service.ItemSizeService;
import com.zjxu97.costume.service.SaleRecordService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordMapper, SaleRecord> implements SaleRecordService {

    @Resource
    private ItemSizeService itemSizeService;

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
                List<ItemSize> list = itemSizeService.list();
                List<DataElement> sizeData = this.getBaseMapper().getSizeData(control, from, to, yValue);
                Map<String, String> map = new HashMap<>();
                for (ItemSize size : list) {
                    map.put(String.valueOf(size.getId()), size.getDisplayName());
                }
                for (DataElement size : sizeData) {
                    String key = size.getKey();
                    if (Strings.isNotBlank(key)) {
                        size.setKey(map.get(key.trim()));
                    }
                }
                return sizeData;
            case CostumeConstants.SEX:
                List<DataElement> sexData = this.getBaseMapper().getSexData(control, from, to, yValue);
                for (DataElement sexDatum : sexData) {
                    String key = sexDatum.getKey();
                    if (Strings.isNotBlank(key)) {
                        int index = Integer.parseInt(key.trim());
                        sexDatum.setKey(Common.getSexString((byte) index));
                    }
                }
                return sexData;
            default:
                return null;
        }
    }

    private QueryWrapper<SaleRecord> qw() {
        return new QueryWrapper<>();
    }
}
