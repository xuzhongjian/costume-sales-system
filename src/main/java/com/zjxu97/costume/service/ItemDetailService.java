package com.zjxu97.costume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.ItemDetail;
import com.zjxu97.costume.model.vo.ItemDetailVo;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface ItemDetailService extends IService<ItemDetail> {

    List<ItemDetailVo> getDetailListByItemList(int itemId);

    /**
     * 从商品详情的entity中获取其完全信息，将id转为其对应的文本信息等
     *
     * @param itemDetailList 商品详情的model
     * @return 商品详情的完全信息
     */
    List<ItemDetailVo> getItemDetailVoFromModelList(List<ItemDetail> itemDetailList);
}

