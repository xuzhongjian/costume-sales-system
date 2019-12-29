package com.zjxu97.costume.service.item;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.Item;
import com.zjxu97.costume.vo.ItemVo;
import com.zjxu97.costume.vo.StoreVo;

import java.util.List;

public interface ItemService extends IService<Item> {
    public List<ItemVo> searchItems(String keyWord);
}
