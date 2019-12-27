package com.zjxu97.costume.service.item.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.Item;
import com.zjxu97.costume.mapper.ItemMapper;
import com.zjxu97.costume.service.item.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
}
