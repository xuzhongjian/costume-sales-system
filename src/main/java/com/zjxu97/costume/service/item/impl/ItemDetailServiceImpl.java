package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.mapper.item.ItemDetailMapper;
import com.zjxu97.costume.model.entity.item.Item;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.entity.item.ItemSize;
import com.zjxu97.costume.model.entity.item.ItemType;
import com.zjxu97.costume.model.param.ItemDetailPageParam;
import com.zjxu97.costume.model.param.ItemTypeDetailPageParam;
import com.zjxu97.costume.model.param.QueryItemDetailPageParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.service.item.ItemSizeService;
import com.zjxu97.costume.service.item.ItemTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemDetailServiceImpl extends ServiceImpl<ItemDetailMapper, ItemDetail> implements ItemDetailService {

    @Resource
    ItemService itemService;

    @Resource
    ItemTypeService itemTypeService;

    @Resource
    ItemSizeService itemSizeService;


    @Override
    public IPage<ItemDetail> queryItemDetail(QueryItemDetailPageParam param) {
        Integer itemTypeId = param.getItemTypeId();
        Integer itemSizeId = param.getItemSizeId();
        String itemKeyWords = param.getItemKeyWords();

        Page<ItemDetail> page = new Page<>();
        BeanUtils.copyProperties(param, page);

        List<Integer> itemIdList = itemService.getItemList(itemTypeId, itemKeyWords).stream().map(Item::getId).collect(Collectors.toList());
        QueryWrapper<ItemDetail> qw = qw()
                .in(Common.isUsefulList(itemIdList), "item_id", itemIdList)
                .eq(Common.isUsefulNum(itemSizeId), "item_size_id", itemSizeId);
        return this.page(page, qw);
    }

    @Override
    public List<ItemDetail> queryItemDetailList(QueryItemDetailPageParam param) {
        Integer itemTypeId = param.getItemTypeId();
        Integer itemSizeId = param.getItemSizeId();
        String itemKeyWords = param.getItemKeyWords();


        List<Integer> itemIdList = itemService.getItemList(itemTypeId, itemKeyWords).stream().map(Item::getId).collect(Collectors.toList());
        QueryWrapper<ItemDetail> qw = qw()
                .in(Common.isUsefulList(itemIdList), "item_id", itemIdList)
                .eq(Common.isUsefulNum(itemSizeId), "item_size_id", itemSizeId);

        return this.list(qw);
    }

    @Override
    public IPage<ItemDetail> getItemDetailByTypeId(ItemTypeDetailPageParam param) {
        List<Integer> itemIdList = itemService.getItemListByTypeId(param.getTypeId()).stream().map(Item::getId).collect(Collectors.toList());
        Page<ItemDetail> page = new Page<>();
        BeanUtils.copyProperties(param, page);
        return this.page(page, qw().in(Common.isUsefulList(itemIdList), "item_id", itemIdList));
    }

    @Override
    public IPage<ItemDetail> getItemDetailByItemId(ItemDetailPageParam param) {
        Integer itemId = param.getItemId();
        QueryWrapper<ItemDetail> qw = qw().eq(Common.isUsefulNum(itemId), "item_id", itemId);
        Page<ItemDetail> page = new Page<>();
        BeanUtils.copyProperties(param, page);
        return this.page(page, qw);
    }

    @Override
    public ItemType getItemTypeByDetailId(Integer detailId) {
        return itemTypeService.getItemTypeByDetailId(detailId);
    }

    @Override
    public List<ItemDetailVo> getItemDetailVoFromEntityList(List<ItemDetail> itemDetailList) {

        //获取size的map
        Set<Integer> itemSizeIdSet = itemDetailList.stream().map(ItemDetail::getItemSizeId).collect(Collectors.toSet());
        HashMap<Integer, ItemSize> sizeMap = new HashMap<>();
        itemSizeService.listByIds(itemSizeIdSet).forEach(itemSize -> {
            sizeMap.put(itemSize.getId(), itemSize);
        });

        //获取item的map
        Set<Integer> itemIdSet = itemDetailList.stream().map(ItemDetail::getItemId).collect(Collectors.toSet());
        HashMap<Integer, Item> itemMap = new HashMap<>();
        itemService.listByIds(itemIdSet).forEach(item -> {
            itemMap.put(item.getId(), item);
        });

        return itemDetailList.stream().map(itemDetail -> {
            Integer itemSizeId = itemDetail.getItemSizeId();
            Integer itemId = itemDetail.getItemId();
            ItemType itemType = itemTypeService.getItemTypeByDetailId(itemDetail.getId());

            ItemDetailVo itemDetailVo = new ItemDetailVo();
            BeanUtils.copyProperties(itemDetail, itemDetailVo);
            itemDetailVo.setItemName(itemMap.get(itemId).getItemName());
            itemDetailVo.setItemSize(sizeMap.get(itemSizeId).getDisplayName());
            itemDetailVo.setItemTypeName(itemType.getTypeName());
            itemDetailVo.setSexString(CostumeConstants.getString(itemType.getSex()));
            return itemDetailVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<ItemDetail> qw() {
        return new QueryWrapper<>();
    }
}
