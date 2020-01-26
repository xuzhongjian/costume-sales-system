package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.model.entity.item.Item;
import com.zjxu97.costume.mapper.item.ItemMapper;
import com.zjxu97.costume.model.param.QueryItemParam;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.model.vo.ItemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Override
    public List<ItemVo> searchItem(String keyWord) {
        List<Item> itemList = this.baseMapper.selectList(qw().like("item_name", keyWord));
        return itemList.stream().map(item -> {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            return itemVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Item> queryItem(QueryItemParam queryItemParam) {
        Integer itemTypeId = queryItemParam.getItemTypeId();
        Integer itemSizeId = queryItemParam.getItemSizeId();
        String itemKeyWords = queryItemParam.getItemKeyWords();
        return this.list(qw()
                .eq(Common.isUsefulNum(itemTypeId), "item_type_id", itemTypeId)
                .eq(Common.isUsefulNum(itemSizeId), "item_size_id", itemSizeId)
                .like(Common.isUsefulString(itemKeyWords), "item_name", itemKeyWords)
        );
    }

    private QueryWrapper<Item> qw() {
        return new QueryWrapper<>();
    }
}
