package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.item.ItemTypeMapper;
import com.zjxu97.costume.model.entity.item.Item;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.entity.item.ItemType;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.service.item.ItemTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemTypeServiceImpl extends ServiceImpl<ItemTypeMapper, ItemType> implements ItemTypeService {

    @Override
    public ItemType getItemTypeByDetailId(Integer itemDetailId) {
        return this.getBaseMapper().getItemTypeByItemDetailId(itemDetailId);
    }

    private QueryWrapper<ItemType> qw() {
        return new QueryWrapper<>();
    }
}
