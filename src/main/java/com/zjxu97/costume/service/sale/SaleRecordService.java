package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.sale.SaleRecord;
import com.zjxu97.costume.model.param.GoodParam;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface SaleRecordService extends IService<SaleRecord> {
    /**
     * 销售商品列表，商品售出或退回
     *
     * @param goodParamList 商品列表
     * @return 涉及到的总价格
     */
    Integer recordSales(List<GoodParam> goodParamList);
}
