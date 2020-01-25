package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.commons.Rx;
import com.zjxu97.costume.model.dto.StockDTO;
import com.zjxu97.costume.model.param.GoodParam;
import com.zjxu97.costume.service.sale.SaleRecordService;
import com.zjxu97.costume.service.sale.StockRecordService;
import com.zjxu97.costume.service.sale.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    StockService stockService;

    @Resource
    StockRecordService stockRecordService;


    @ApiOperation(value = "购买", notes = "参数是所购买商品的list 返回值是购买的总价")
    @PostMapping(value = "sale")
    public R<Integer> sale(@RequestBody List<GoodParam> saleItemParamList) {
        Integer total = saleRecordService.recordSales(saleItemParamList);

        //转换成记录的对象
        List<StockDTO> stockDTOList = saleItemParamList.stream().map(goodParam -> {
            StockDTO dto = new StockDTO();
            BeanUtils.copyProperties(goodParam, dto);
            dto.setAmount(1);
            dto.setInoutType(InOutEnum.OUT.getValue());
            return dto;
        }).collect(Collectors.toList());

        stockRecordService.stockRecord(stockDTOList);
        stockService.updateStockAmount(stockDTOList);
        return Rx.success(total);
    }

    @ApiOperation(value = "退货", notes = "参数是所退货商品的list 返回值是退货的总价")
    @PostMapping(value = "return")
    public R<Integer> returnGoods(@RequestBody List<GoodParam> goodParam) {
        Integer total = saleRecordService.recordSales(goodParam);


        return Rx.success(total);
    }
}
