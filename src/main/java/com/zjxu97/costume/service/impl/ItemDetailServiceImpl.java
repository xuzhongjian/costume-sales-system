package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.mapper.ItemDetailMapper;
import com.zjxu97.costume.model.entity.Item;
import com.zjxu97.costume.model.entity.ItemDetail;
import com.zjxu97.costume.model.entity.ItemSize;
import com.zjxu97.costume.model.entity.ItemType;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.service.ItemDetailService;
import com.zjxu97.costume.service.ItemService;
import com.zjxu97.costume.service.ItemSizeService;
import com.zjxu97.costume.service.ItemTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class ItemDetailServiceImpl extends ServiceImpl<ItemDetailMapper, ItemDetail> implements ItemDetailService {

    @Resource
    ItemService itemService;

    @Resource
    ItemTypeService itemTypeService;

    @Resource
    ItemSizeService itemSizeService;

    public List<ItemDetailVo> getDetailListByItemList(int itemId) {
        List<ItemDetail> list = this.list(qw().eq("item_id", itemId));
        return this.getItemDetailVoFromModelList(list);
    }

    @Override
    public List<ItemDetailVo> getItemDetailVoFromModelList(List<ItemDetail> itemDetailList) {

        //获取size的map
        Set<Integer> itemSizeIdSet = itemDetailList.stream().map(ItemDetail::getItemSizeId).collect(Collectors.toSet());
        HashMap<Integer, ItemSize> sizeMap = new HashMap<>();
        itemSizeService.listByIds(itemSizeIdSet).forEach(itemSize -> sizeMap.put(itemSize.getId(), itemSize));

        //获取item的map
        Set<Integer> itemIdSet = itemDetailList.stream().map(ItemDetail::getItemId).collect(Collectors.toSet());
        HashMap<Integer, Item> itemMap = new HashMap<>();
        itemService.listByIds(itemIdSet).forEach(item -> itemMap.put(item.getId(), item));

        return itemDetailList.stream().map(itemDetail -> {
            Integer itemSizeId = itemDetail.getItemSizeId();
            Integer itemId = itemDetail.getItemId();
            ItemType itemType = itemTypeService.getItemTypeByDetailId(itemDetail.getId());

            ItemDetailVo itemDetailVo = new ItemDetailVo();
            BeanUtils.copyProperties(itemDetail, itemDetailVo);
            itemDetailVo.setItemName(itemMap.get(itemId).getItemName());
            itemDetailVo.setItemSize(sizeMap.get(itemSizeId).getDisplayName());
            itemDetailVo.setItemTypeName(itemType.getTypeName());
            itemDetailVo.setSexString(Common.getSexString(itemDetail.getSex()));
            return itemDetailVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<ItemDetail> qw() {
        return new QueryWrapper<>();
    }
}
