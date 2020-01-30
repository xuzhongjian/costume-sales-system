package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.Common;
import com.zjxu97.costume.model.entity.item.Item;
import com.zjxu97.costume.mapper.item.ItemMapper;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.entity.item.ItemType;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.item.ItemService;
import net.sf.jsqlparser.statement.Commit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    @Resource
    ItemDetailService itemDetailService;

    @Override
    public List<Item> getItemListByTypeId(Integer itemTypeId) {
        return this.list(qw().eq(Common.isUsefulNum(itemTypeId), "item_type_id", itemTypeId));
    }

    @Override
    public List<Item> getItemList(Integer itemTypeId, String keyWords) {
        return this.list(qw().like(Common.isUsefulString(keyWords), "item_name", keyWords)
                .eq(Common.isUsefulNum(itemTypeId), "item_type_id", itemTypeId));
    }

    private QueryWrapper<Item> qw() {
        return new QueryWrapper<>();
    }
}
