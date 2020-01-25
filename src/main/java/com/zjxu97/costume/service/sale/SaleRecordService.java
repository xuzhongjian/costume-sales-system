package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.sale.SaleRecord;
import com.zjxu97.costume.model.param.GoodsParam;

public interface SaleRecordService extends IService<SaleRecord> {
    Integer recordSales(GoodsParam goodsParam, Byte saleType);
}
