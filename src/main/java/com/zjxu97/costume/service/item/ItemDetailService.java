package com.zjxu97.costume.service.item;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.param.QueryItemDetailParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;

import java.util.List;

public interface ItemDetailService extends IService<ItemDetail> {
    List<ItemDetailVo> getItemDetailByItemId(Integer itemId);

    List<ItemDetailVo> getItemDetailByTypeId(Integer typeId);

    List<ItemDetailVo> queryItemDetail(QueryItemDetailParam queryItemDetailParam);
}
