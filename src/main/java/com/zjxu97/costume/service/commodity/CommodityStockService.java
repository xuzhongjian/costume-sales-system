package com.zjxu97.costume.service.commodity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.CommodityStock;
import com.zjxu97.costume.param.GoodsParam;
import com.zjxu97.costume.vo.CommodityStockVo;

import java.util.List;

public interface CommodityStockService extends IService<CommodityStock> {
    CommodityStockVo getItemComByStore(Integer storeId, Integer itemId);

    void changeCommodityStock(GoodsParam goodsParam, Byte saleType);
}
