package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.model.Item;
import com.zjxu97.costume.mapper.ItemMapper;
import com.zjxu97.costume.param.QueryItemsParam;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.vo.ItemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Override
    public List<ItemVo> searchItems(String keyWord) {
        List<Item> items = this.baseMapper.selectList(qw().like("item_name", keyWord));
        return items.stream().map(item -> {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            return itemVo;
        }).collect(Collectors.toList());
    }

    public List<Item> queryItems(QueryItemsParam queryItemsParam) {
        Integer costumeId = queryItemsParam.getCostumeId();
        Integer itemSize = queryItemsParam.getItemSize();
        Byte sex = queryItemsParam.getSex();
        return this.list(qw()
                .eq(Common.isUsefulNum(costumeId), "costume_id", costumeId)
                .eq(Common.isUsefulNum(itemSize), "item_size", itemSize)
                .eq(Common.isUsefulNum(sex), "sex", sex)
        );
    }


    private QueryWrapper<Item> qw() {
        return new QueryWrapper<>();
    }
}
