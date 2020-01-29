package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.mapper.item.ItemDetailMapper;
import com.zjxu97.costume.model.entity.item.Item;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.param.QueryItemDetailParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.item.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemDetailServiceImpl extends ServiceImpl<ItemDetailMapper, ItemDetail> implements ItemDetailService {

    @Resource
    ItemService itemService;


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
    public List<ItemDetailVo> getItemDetailByTypeId(Integer typeId, Integer pageNo, Integer pageSize) {
        List<Integer> itemIdList = itemService.getItemListByTypeId(typeId).stream().map(Item::getId).collect(Collectors.toList());
        return this.list(qw().in(Common.isUsefulList(itemIdList), "item_id", itemIdList)
                .last("limit " + (pageNo - 1) * pageSize + " , " + pageSize)).stream().map(itemDetail -> {
            ItemDetailVo itemDetailVo = new ItemDetailVo();
            BeanUtils.copyProperties(itemDetail, itemDetailVo);
            return itemDetailVo;
        }).collect(Collectors.toList());
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

    private QueryWrapper<ItemDetail> qw() {
        return new QueryWrapper<>();
    }
}
