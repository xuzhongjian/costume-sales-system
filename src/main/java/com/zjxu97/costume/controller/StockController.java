package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.commons.Rx;
import com.zjxu97.costume.param.QueryStockParam;
import com.zjxu97.costume.param.StockInOutParam;
import com.zjxu97.costume.param.StoreParam;
import com.zjxu97.costume.service.commodity.CommodityStockService;
import com.zjxu97.costume.vo.CommodityStockVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 21:33
 */

@RestController
@Api(tags = "库存相关")
@RequestMapping(Constants.API_PREFIX + "/stocks")
public class StockController {

    @Resource
    private CommodityStockService commodityStockService;

    @ApiOperation(value = "入库")
    @PostMapping(value = "in")
    public R<Boolean> in(@RequestBody List<StockInOutParam> stockInOuts) {
        Boolean isSuccess = commodityStockService.inOutStock(stockInOuts, InOutEnum.In.getValue());
        return Rx.success(isSuccess);
    }

    @ApiOperation(value = "出库")
    @PostMapping(value = "out")
    public R<Boolean> out(@RequestBody List<StockInOutParam> stockInOuts) {
        Boolean isSuccess = commodityStockService.inOutStock(stockInOuts, InOutEnum.In.getValue());
        return Rx.success(isSuccess);
    }

    @ApiOperation(value = "库存查询", notes = "按照店铺查询商品存量")
    @PostMapping(value = "query-stock")
    public R<List<CommodityStockVo>> queryStock(@RequestBody QueryStockParam queryStockParam) {

        return Rx.success(null);
    }


    @ApiOperation(value = "店铺库存", notes = "给出店铺的id、商品的id，查询这个商品的库存状况")
    @GetMapping(value = "com-by-store")
    public R<CommodityStockVo> getItemComByStore(@RequestParam Integer storeId, @RequestParam Integer itemId) {
        CommodityStockVo itemComByStore = commodityStockService.getItemComByStore(storeId, itemId);
        return Rx.success(itemComByStore);
    }
}
