package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.item.ItemTypeMapper;
import com.zjxu97.costume.model.item.ItemType;
import com.zjxu97.costume.service.item.ItemTypeService;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeServiceImpl extends ServiceImpl<ItemTypeMapper, ItemType> implements ItemTypeService {

    private QueryWrapper<ItemType> qw() {
        return new QueryWrapper<>();
    }
}
