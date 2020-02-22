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

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface ItemDetailService extends IService<ItemDetail> {

    /**
     * 根据传入参数，取得具体商品的列表
     *
     * @param param 分页 + 类比的ID
     * @return 具体商品的分页
     */
    IPage<ItemDetail> getItemDetailByTypeId(ItemTypeDetailPageParam param);

    /**
     * 根据传入参数，取得具体商品的列表
     *
     * @param param 分页 + 商品的模糊id
     * @return 具体商品的分页
     */
    IPage<ItemDetail> getItemDetailByItemId(ItemDetailPageParam param);

    /**
     * 根据传入参数，取得具体商品的列表
     *
     * @param param 分页 + (类别id + 关键字 + 大小)
     * @return 具体商品的分页
     */
    IPage<ItemDetail> queryItemDetail(QueryItemDetailPageParam param);

    /**
     * 根据传入参数，取得具体商品的列表
     *
     * @param param 分页 + (类别id + 关键字 + 大小)
     * @return 具体商品的列表
     */
    List<ItemDetail> queryItemDetailList(QueryItemDetailPageParam param);

    /**
     * 从商品详情的entity中获取其完全信息，将id转为其对应的文本信息等
     *
     * @param itemDetailList 商品详情的model
     * @return 商品详情的完全信息
     */
    List<ItemDetailVo> getItemDetailVoFromModelList(List<ItemDetail> itemDetailList);
}

