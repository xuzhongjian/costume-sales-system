package com.zjxu97.costume.service.item;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.entity.item.ItemType;
import com.zjxu97.costume.model.param.ItemDetailPageParam;
import com.zjxu97.costume.model.param.ItemTypeDetailPageParam;
import com.zjxu97.costume.model.param.QueryItemDetailPageParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;

import java.util.List;

public interface ItemDetailService extends IService<ItemDetail> {
    IPage<ItemDetail> getItemDetailByTypeId(ItemTypeDetailPageParam param);

    IPage<ItemDetail> getItemDetailByItemId(ItemDetailPageParam param);

    IPage<ItemDetail> queryItemDetail(QueryItemDetailPageParam param);

    ItemType getItemTypeByDetailId(Integer detailId);

    List<ItemDetailVo> getItemDetailVoFromEntityList(List<ItemDetail> itemDetailList);
}

