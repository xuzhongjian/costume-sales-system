package com.zjxu97.costume.service.item;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.item.Item;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface ItemService extends IService<Item> {
    /**
     * 根据传入参数，返回符合规则的模糊商品
     *
     * @param typeId 类别id
     * @return 模糊商品
     */
    List<Item> getItemListByTypeId(Integer typeId);

    /**
     * 根据传入参数，返回符合规则的模糊商品
     *
     * @param typeId   类别id
     * @param keyWords 关键字
     * @return 模糊商品
     */
    List<Item> getItemList(Integer typeId, String keyWords);

}
