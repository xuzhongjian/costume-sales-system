package com.zjxu97.costume.service.item;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.item.Item;

import java.util.List;

public interface ItemService extends IService<Item> {

    List<Item> getItemListByTypeId(Integer typeId);

    List<Item> getItemList(Integer typeId, String keyWords);

}
