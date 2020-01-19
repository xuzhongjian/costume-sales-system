package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.SaleRecord;
import com.zjxu97.costume.param.GoodsParam;

import java.util.List;

public interface SaleRecordService extends IService<SaleRecord> {
    Integer recordSales(GoodsParam goodsParam, Byte saleType);

}
