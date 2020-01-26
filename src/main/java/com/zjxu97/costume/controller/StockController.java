package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.commons.Return;
import com.zjxu97.costume.model.dto.StockDisplayDTO;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.item.Item;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.model.param.QueryItemParam;
import com.zjxu97.costume.model.param.QueryStockParam;
import com.zjxu97.costume.model.param.StockInOutParam;
import com.zjxu97.costume.model.vo.StockVo;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.service.sale.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2020/1/19 21:33
 */

@RestController
@Api(tags = "库存相关")
@RequestMapping(Constants.API_PREFIX + "/stocks")
public class StockController {

    @Resource
    private ItemService itemService;

    @Resource
    StockService stockService;

    /**
     * OK
     */
    @ApiOperation(value = "入库")
    @PostMapping(value = "in")
    public R<Boolean> in(@RequestBody List<StockInOutParam> stockInOutParamList) {
        List<StockInOutDTO> stockInOutDTOList = stockInOutParamList.stream().map(stockInOutParam -> {
            StockInOutDTO stockInOutDTO = new StockInOutDTO();
            BeanUtils.copyProperties(stockInOutParam, stockInOutDTO);
            stockInOutDTO.setInoutType(InOutEnum.IN.getValue());
            return stockInOutDTO;
        }).collect(Collectors.toList());
        stockService.updateStockAmount(stockInOutDTOList);
        return Return.success(true);
    }

    /**
     * OK
     */
    @ApiOperation(value = "出库")
    @PostMapping(value = "out")
    public R<Boolean> out(@RequestBody List<StockInOutParam> stockInOutParamList) {
        List<StockInOutDTO> stockInOutDTOList = stockInOutParamList.stream().map(stockInOutParam -> {
            StockInOutDTO stockInOutDTO = new StockInOutDTO();
            BeanUtils.copyProperties(stockInOutParam, stockInOutDTO);
            stockInOutDTO.setInoutType(InOutEnum.OUT.getValue());
            return stockInOutDTO;
        }).collect(Collectors.toList());
        stockService.updateStockAmount(stockInOutDTOList);
        return Return.success(true);
    }

    /**
     * OK
     */
    @ApiOperation(value = "库存查询", notes = "按照店铺查询商品存量")
    @PostMapping(value = "query")
    public R<List<StockVo>> queryStock(@RequestBody QueryStockParam queryStockParam) {
        QueryItemParam queryItemParam = new QueryItemParam();
        BeanUtils.copyProperties(queryStockParam, queryItemParam);
        List<Integer> itemIdList = itemService.queryItem(queryItemParam).stream().map(Item::getId).collect(Collectors.toList());

        Integer storeId = queryStockParam.getStoreId();
        List<StockVo> stockVoList = stockService.getStockByItemList(itemIdList, storeId).stream().map(stockDisplayDTO -> {
            StockVo stockVo = new StockVo();
            BeanUtils.copyProperties(stockDisplayDTO, stockVo);
            return stockVo;
        }).collect(Collectors.toList());

        return Return.success(stockVoList);
    }

    /**
     * OK
     */
    @ApiOperation(value = "店铺库存", notes = "店铺的库存状况")
    @GetMapping(value = "store-stock")
    public R<List<StockVo>> getItemComByStore(@RequestParam Integer storeId) {
        List<StockDisplayDTO> stockDisplayDTOList = stockService.getStockByStore(storeId);
        List<StockVo> stockVoList = stockDisplayDTOList.stream().map(stockDisplayDTO -> {
            StockVo commodityStockVo = new StockVo();
            BeanUtils.copyProperties(stockDisplayDTO, commodityStockVo);
            return commodityStockVo;
        }).collect(Collectors.toList());
        return Return.success(stockVoList);
    }
}
