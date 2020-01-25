package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.commons.Rx;
import com.zjxu97.costume.model.sale.Stock;
import com.zjxu97.costume.model.item.Item;
import com.zjxu97.costume.param.QueryItemsParam;
import com.zjxu97.costume.param.QueryStockParam;
import com.zjxu97.costume.param.StockInOutParam;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.vo.CommodityStockVo;
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

    @ApiOperation(value = "入库")
    @PostMapping(value = "in")
    public R<Boolean> in(@RequestBody List<StockInOutParam> stockInOuts) {
//        Boolean isSuccess = commodityStockService.inOutStock(stockInOuts, InOutEnum.In.getValue());
        return Rx.success(true);
    }

    @ApiOperation(value = "出库")
    @PostMapping(value = "out")
    public R<Boolean> out(@RequestBody List<StockInOutParam> stockInOuts) {
//        Boolean isSuccess = commodityStockService.inOutStock(stockInOuts, InOutEnum.In.getValue());
        return Rx.success(true);
    }

    @ApiOperation(value = "库存查询", notes = "按照店铺查询商品存量")
    @PostMapping(value = "query-stock")
    public R<List<CommodityStockVo>> queryStock(@RequestBody QueryStockParam queryStockParam) {
        QueryItemsParam queryItemsParam = new QueryItemsParam();
        BeanUtils.copyProperties(queryStockParam, queryItemsParam);
        List<Integer> ids = itemService.queryItems(queryItemsParam).stream().map(Item::getId).collect(Collectors.toList());

        Integer storeId = queryStockParam.getStoreId();
//        List<CommodityStockVo> vos = commodityStockService.getStoreStocks(ids, storeId).stream().map(commodityStock -> {
//            CommodityStockVo commodityStockVo = new CommodityStockVo();
//            BeanUtils.copyProperties(commodityStock, commodityStockVo);
//            return commodityStockVo;
//        }).collect(Collectors.toList());

        return Rx.success(null);
    }


    @ApiOperation(value = "店铺库存", notes = "给出店铺的id、商品的id，查询这个商品的库存状况")
    @GetMapping(value = "com-by-store")
    public R<CommodityStockVo> getItemComByStore(@RequestParam Integer storeId, @RequestParam Integer itemId) {
//        Stock itemComByStore = commodityStockService.getItemComByStore(storeId, itemId);
//        CommodityStockVo commodityStockVo = new CommodityStockVo();
//        BeanUtils.copyProperties(itemComByStore, commodityStockVo);
        return Rx.success(null);
    }
}
