package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.*;
import com.zjxu97.costume.mapper.SaleRecordMapper;
import com.zjxu97.costume.model.entity.ItemSize;
import com.zjxu97.costume.model.entity.SaleRecord;
import com.zjxu97.costume.service.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordMapper, SaleRecord> implements SaleRecordService {

    @Resource
    private ItemSizeService itemSizeService;

    @Resource
    private AreaService areaService;

    @Resource
    private CityService cityService;

    @Resource
    private StoreService storeService;

    @Resource
    private ProvinceService provinceService;

    @Resource
    private DistrictService districtService;


    @Resource
    private ItemTypeService itemTypeService;

    @Resource
    private ItemBigTypeService itemBigTypeService;

    @Override
    public List<DataElement> getDataList(Control control, String from, String to, String xType, String xValue, String yValue) {
        switch (xType) {
            case CostumeConstants.DATE:
                return this.getBaseMapper().getDateData(control, from, to, xValue, yValue);
            case CostumeConstants.LOCATION:
                List<DataElement> locationData = this.getBaseMapper().getLocationData(control, from, to, xValue, yValue);
                List<Integer> idList = locationData.stream().map(DataElement::getKey).map(Integer::parseInt).collect(Collectors.toList());
                Map<String, String> locationMap = new HashMap<>();
                switch (xValue) {
                    case LocationTypeConstants.AREA:
                        areaService.listByIds(idList).forEach(area -> locationMap.put(String.valueOf(area.getId()), area.getAreaName()));
                        break;
                    case LocationTypeConstants.CITY:
                        cityService.listByIds(idList).forEach(city -> locationMap.put(String.valueOf(city.getId()), city.getCityName()));
                        break;
                    case LocationTypeConstants.PROVINCE:
                        provinceService.listByIds(idList).forEach(province -> locationMap.put(String.valueOf(province.getId()), province.getProvinceName()));
                        break;
                    case LocationTypeConstants.DISTRICT:
                        districtService.listByIds(idList).forEach(district -> locationMap.put(String.valueOf(district.getId()), district.getDistrictName()));
                        break;
                }
                for (DataElement size : locationData) {
                    String key = size.getKey();
                    if (Strings.isNotBlank(key)) {
                        size.setKey(locationMap.get(key.trim()));
                    }
                }
                return locationData;
            case CostumeConstants.STORE:
                List<DataElement> storeData = this.getBaseMapper().getStoreData(control, from, to, yValue);
                Map<String, String> storeMap = new HashMap<>();
                storeService.listByIds(storeData.stream().map(DataElement::getKey).map(Integer::parseInt).collect(Collectors.toList()))
                        .forEach(itemType -> storeMap.put(String.valueOf(itemType.getId()), itemType.getStoreAddress()));
                for (DataElement size : storeData) {
                    String key = size.getKey();
                    if (Strings.isNotBlank(key)) {
                        size.setKey(storeMap.get(key.trim()));
                    }
                }
                return storeData;
            case CostumeConstants.ITEM_TYPE:
                List<DataElement> itemTypeData = this.getBaseMapper().getItemTypeData(control, from, to, yValue);
                Map<String, String> itemTypeMap = new HashMap<>();
                itemTypeService.listByIds(itemTypeData.stream().map(DataElement::getKey).map(Integer::parseInt).collect(Collectors.toList()))
                        .forEach(itemType -> itemTypeMap.put(String.valueOf(itemType.getId()), itemType.getTypeName()));
                for (DataElement size : itemTypeData) {
                    String key = size.getKey();
                    if (Strings.isNotBlank(key)) {
                        size.setKey(itemTypeMap.get(key.trim()));
                    }
                }
                return itemTypeData;
            case CostumeConstants.ITEM_BIG_TYPE:
                List<DataElement> itemBigTypeData = this.getBaseMapper().getItemBigTypeData(control, from, to, yValue);
                Map<String, String> itemBigTypeMap = new HashMap<>();
                itemBigTypeService.listByIds(itemBigTypeData.stream().map(DataElement::getKey).map(Integer::parseInt).collect(Collectors.toList()))
                        .forEach(itemType -> itemBigTypeMap.put(String.valueOf(itemType.getId()), itemType.getTypeName()));
                for (DataElement size : itemBigTypeData) {
                    String key = size.getKey();
                    if (Strings.isNotBlank(key)) {
                        size.setKey(itemBigTypeMap.get(key.trim()));
                    }
                }
                return itemBigTypeData;
            case CostumeConstants.ITEM:
                List<DataElement> itemData = this.getBaseMapper().getItemData(control, from, to, yValue);
                Map<String, String> itemMap = new HashMap<>();
                itemTypeService.listByIds(itemData.stream().map(DataElement::getKey).map(Integer::parseInt).collect(Collectors.toList()))
                        .forEach(itemType -> itemMap.put(String.valueOf(itemType.getId()), itemType.getTypeName()));
                for (DataElement size : itemData) {
                    String key = size.getKey();
                    if (Strings.isNotBlank(key)) {
                        size.setKey(itemMap.get(key.trim()));
                    }
                }
                return itemData;
            case CostumeConstants.SIZE:
                List<ItemSize> list = itemSizeService.list();
                List<DataElement> sizeData = this.getBaseMapper().getSizeData(control, from, to, yValue);
                Map<String, String> sizeMap = new HashMap<>();
                for (ItemSize size : list) {
                    sizeMap.put(String.valueOf(size.getId()), size.getDisplayName());
                }
                for (DataElement size : sizeData) {
                    String key = size.getKey();
                    if (Strings.isNotBlank(key)) {
                        size.setKey(sizeMap.get(key.trim()));
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
}
