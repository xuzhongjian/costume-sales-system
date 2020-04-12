package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.ItemTypeMapper;
import com.zjxu97.costume.model.entity.ItemType;
import com.zjxu97.costume.service.ItemTypeService;
import org.springframework.stereotype.Service;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
@Service
public class ItemTypeServiceImpl extends ServiceImpl<ItemTypeMapper, ItemType> implements ItemTypeService {

    @Override
    public ItemType getItemTypeByDetailId(Integer itemDetailId) {
        return this.getBaseMapper().getItemTypeByItemDetailId(itemDetailId);
    }
}
