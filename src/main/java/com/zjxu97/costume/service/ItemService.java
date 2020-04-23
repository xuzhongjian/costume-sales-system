package com.zjxu97.costume.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.Item;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface ItemService extends IService<Item> {

    Page<Item> itemList(int itemTypeId, int size, int current);

}
