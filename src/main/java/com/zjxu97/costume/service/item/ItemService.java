package com.zjxu97.costume.service.item;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.item.Item;
import com.zjxu97.costume.param.QueryItemsParam;
import com.zjxu97.costume.vo.ItemVo;

import java.util.List;

public interface ItemService extends IService<Item> {
    List<ItemVo> searchItems(String keyWord);

    List<Item> queryItems(QueryItemsParam queryItemsParam);


}
