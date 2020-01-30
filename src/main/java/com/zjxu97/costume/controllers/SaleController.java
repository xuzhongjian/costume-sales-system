package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.commons.Ans;
import com.zjxu97.costume.model.dto.StockInOutDTO;
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
 * 无需分页
 *
 * @author zjxu97
 * @date 2020/1/19 17:33
 */
@RestController
@Api(tags = "销售相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/sales")
public class SaleController {

    @Resource
    SaleRecordService saleRecordService;

    @Resource
    StockService stockService;

    @Resource
    StockRecordService stockRecordService;

    /**
     *
     */
    @ApiOperation(value = "购买", notes = "参数是所购买商品的list 返回值是购买的总价")
    @PostMapping(value = "sale")
    public R<Integer> sale(@RequestBody List<GoodParam> saleItemParamList) {
        Integer total = saleRecordService.recordSales(saleItemParamList);

        //转换成记录的对象
        List<StockInOutDTO> stockInOutDTOList = saleItemParamList.stream().map(goodParam -> {
            StockInOutDTO dto = new StockInOutDTO();
            BeanUtils.copyProperties(goodParam, dto);
            dto.setAmount(1);
            dto.setInoutType(InOutEnum.OUT.getValue());
            return dto;
        }).collect(Collectors.toList());

        stockRecordService.stockRecord(stockInOutDTOList);
        stockService.updateStockAmount(stockInOutDTOList);
        return Ans.success(total);
    }

    /**
     *
     */
    @ApiOperation(value = "退货", notes = "参数是所退货商品的list 返回值是退货的总价")
    @PostMapping(value = "return")
    public R<Integer> returnGood(@RequestBody List<GoodParam> saleItemParamList) {
        Integer total = saleRecordService.recordSales(saleItemParamList);

        //转换成记录的对象
        List<StockInOutDTO> stockInOutDTOList = saleItemParamList.stream().map(goodParam -> {
            StockInOutDTO dto = new StockInOutDTO();
            BeanUtils.copyProperties(goodParam, dto);
            dto.setAmount(1);
            dto.setInoutType(InOutEnum.IN.getValue());
            return dto;
        }).collect(Collectors.toList());

        stockRecordService.stockRecord(stockInOutDTOList);
        stockService.updateStockAmount(stockInOutDTOList);
        return Ans.success(total);
    }
}