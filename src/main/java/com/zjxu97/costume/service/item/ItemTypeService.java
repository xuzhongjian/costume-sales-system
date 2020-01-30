package com.zjxu97.costume.service.item;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.item.Item;
import com.zjxu97.costume.model.entity.item.ItemType;

import java.util.List;
import java.util.Set;

/**
 * @author zjxu97
 * @date 2020/1/25 15:19
 */
public interface ItemTypeService extends IService<ItemType> {
    ItemType getItemTypeByDetailId(Integer itemDetailId);
}
