package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.common.Constants;
import com.zjxu97.costume.common.RetFunc;
import com.zjxu97.costume.common.SaleTypeEnum;
import com.zjxu97.costume.param.GoodsParam;
import com.zjxu97.costume.service.commodity.CommodityStockService;
import com.zjxu97.costume.service.sale.SaleRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zjxu97
 * @date 2020/1/19 17:33
 */
@RestController
@Api(tags = "销售相关")
@RequestMapping(Constants.API_PREFIX + "/sales")
public class SaleController {

    @Resource
    SaleRecordService saleRecordService;

    @Resource
    CommodityStockService commodityStockService;

    @ApiOperation(value = "购买", notes = "参数是所购买商品的list 返回值是购买的总价")
    @PostMapping(value = "sale")
    public R<Integer> sale(@RequestBody GoodsParam goodsParam) {
        Integer total = saleRecordService.recordSales(goodsParam, SaleTypeEnum.SALE.getValue());
        commodityStockService.changeCommodityStock(goodsParam, SaleTypeEnum.SALE.getValue());

        return RetFunc.success(total);
    }

    @ApiOperation(value = "退货", notes = "参数是所退货商品的list 返回值是退货的总价")
    @PostMapping(value = "return")
    public R<Integer> returnGoods(@RequestBody GoodsParam goodsParam) {
        Integer total = saleRecordService.recordSales(goodsParam, SaleTypeEnum.RETURN.getValue());
        commodityStockService.changeCommodityStock(goodsParam, SaleTypeEnum.RETURN.getValue());

        return RetFunc.success(total);
    }
}
