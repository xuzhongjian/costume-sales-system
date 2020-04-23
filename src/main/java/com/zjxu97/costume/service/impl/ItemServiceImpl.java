package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.ItemMapper;
import com.zjxu97.costume.model.entity.Item;
import com.zjxu97.costume.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {


    @Override
    public Page<Item> itemList(int itemTypeId, int size, int current) {
        QueryWrapper<Item> queryWrapper = new QueryWrapper<Item>().eq("item_type_id", itemTypeId);
        Page<Item> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        return this.page(page, queryWrapper);
    }

    private QueryWrapper<Item> qw() {
        return new QueryWrapper<>();
    }
}
