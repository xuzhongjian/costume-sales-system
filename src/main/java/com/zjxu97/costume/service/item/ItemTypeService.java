package com.zjxu97.costume.service.item;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.item.ItemType;

/**
 * @author zjxu97
 * @date 2020/1/25 15:19
 */
public interface ItemTypeService extends IService<ItemType> {
    /**
     * 根据商品的详细的id获取所属于的大类
     *
     * @param itemDetailId 商品的详细的id
     * @return 商品大类
     */
    ItemType getItemTypeByDetailId(Integer itemDetailId);
}
