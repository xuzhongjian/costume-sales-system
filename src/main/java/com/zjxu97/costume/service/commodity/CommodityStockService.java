package com.zjxu97.costume.service.commodity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.CommodityStock;
import com.zjxu97.costume.vo.CommodityStockVo;

public interface CommodityStockService extends IService<CommodityStock> {
    public CommodityStockVo getItemComByStore(Integer storeId, Integer itemId);
}
