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
import com.zjxu97.costume.model.param.ItemTypeDetailPageParam;
import com.zjxu97.costume.model.param.QueryItemDetailParam;
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
    public List<ItemDetailVo> queryItemDetail(QueryItemDetailParam queryItemDetailParam) {
        Integer itemTypeId = queryItemDetailParam.getItemTypeId();
        Integer itemSizeId = queryItemDetailParam.getItemSizeId();
        String itemKeyWords = queryItemDetailParam.getItemKeyWords();
        Integer pageNo = queryItemDetailParam.getPageNo();
        Integer pageSize = queryItemDetailParam.getPageSize();

        List<Integer> itemIdList = itemService.getItemList(itemTypeId, itemKeyWords).stream().map(Item::getId).collect(Collectors.toList());
        return this.list(qw()
                .in(Common.isUsefulList(itemIdList), "item_id", itemIdList)
                .eq(Common.isUsefulNum(itemSizeId), "item_size_id", itemSizeId)
                .last("limit " + (pageNo - 1) * pageSize + " , " + pageSize))
                .stream().map(itemDetail -> {
                    ItemDetailVo itemDetailVo = new ItemDetailVo();
                    BeanUtils.copyProperties(itemDetail, itemDetailVo);
                    return itemDetailVo;
                }).collect(Collectors.toList());
    }

    @Override
    public IPage<ItemDetail> getItemDetailByTypeId(ItemTypeDetailPageParam param) {
        List<Integer> itemIdList = itemService.getItemListByTypeId(param.getTypeId()).stream().map(Item::getId).collect(Collectors.toList());
        Page<ItemDetail> page = new Page<>();
        BeanUtils.copyProperties(param, page);
        return this.page(page, qw().in(Common.isUsefulList(itemIdList), "item_id", itemIdList));
    }

    @Override
    public List<ItemDetailVo> getItemDetailByItemId(Integer itemId, Integer pageNo, Integer pageSize) {
        List<ItemDetail> itemDetailList = this.list(qw().eq(Common.isUsefulNum(itemId), "item_id", itemId)
                .last("limit " + (pageNo - 1) * pageSize + " , " + pageSize));
        return itemDetailList.stream().map(itemDetail -> {
            ItemDetailVo itemDetailVo = new ItemDetailVo();
            BeanUtils.copyProperties(itemDetail, itemDetailVo);
            return itemDetailVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ItemDetailVo> getItemDetailVoFromList(List<ItemDetail> itemDetailList) {

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
            ItemType itemType = itemTypeService.getItemTypeByDetailId(itemDetail.getItemId());

            ItemDetailVo itemDetailVo = new ItemDetailVo();
            BeanUtils.copyProperties(itemDetail, itemDetailVo);
            itemDetailVo.setItemName(itemMap.get(itemId).getItemName());
            itemDetailVo.setItemSize(sizeMap.get(itemSizeId).getDisplayName());
            itemDetailVo.setItemTypeName(itemType.getTypeName());
            itemDetailVo.setSexString(CostumeConstants.getString(itemType.getSex()));
            return itemDetailVo;
        }).collect(Collectors.toList());
    }

    @Override
    public ItemType getItemTypeByDetailId(Integer detailId) {
        return itemTypeService.getItemTypeByDetailId(detailId);
    }

    private QueryWrapper<ItemDetail> qw() {
        return new QueryWrapper<>();
    }
}
