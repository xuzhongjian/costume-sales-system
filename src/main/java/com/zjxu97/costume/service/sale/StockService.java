package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.commons.PageParam;
import com.zjxu97.costume.model.dto.StockDisplayDTO;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.model.param.StoreStockPageParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.model.vo.StockVo;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface StockService extends IService<Stock> {
    /**
     * 变更库存数量
     *
     * @param stockInOutDTOList 库存数量需要变更的列表
     */
    void updateStockAmount(List<StockInOutDTO> stockInOutDTOList);

    /**
     * 查询商品的ID列表的店铺的库存数据
     *
     * @param itemIdList 商品的ID列表
     * @param storeId    店铺id
     * @param pageParam  分页参数
     * @return 库存数据的分页
     */
    IPage<Stock> getStockByItemList(List<Integer> itemIdList, Integer storeId, PageParam pageParam);

    /**
     * 分页查询店铺库存数据
     *
     * @param storeStockPageParam 店铺id + 分页参数
     * @return 库存数据的分页
     */
    IPage<Stock> getStockByStore(StoreStockPageParam storeStockPageParam);

    /**
     * model list -> vo list
     *
     * @param stockList 库存的model列表
     * @return 库存的完全信息
     */
    List<StockVo> getStockVoFromModelList(List<Stock> stockList);
}
