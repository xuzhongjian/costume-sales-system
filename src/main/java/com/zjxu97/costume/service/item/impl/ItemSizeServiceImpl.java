package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.item.ItemSizeMapper;
import com.zjxu97.costume.model.item.ItemSize;
import com.zjxu97.costume.service.item.ItemSizeService;
import org.springframework.stereotype.Service;

@Service
public class ItemSizeServiceImpl extends ServiceImpl<ItemSizeMapper, ItemSize> implements ItemSizeService {

    private QueryWrapper<ItemSize> qw() {
        return new QueryWrapper<>();
    }
}
