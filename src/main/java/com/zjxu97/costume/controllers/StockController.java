package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.PageList;
import com.zjxu97.costume.model.entity.Stock;
import com.zjxu97.costume.model.vo.StockVo;
import com.zjxu97.costume.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 需要分页
 *
 * @author zjxu97
 * @date 2020/1/19 21:33
 */

@RestController
@Api(tags = "库存相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/stock")
public class StockController {

    @Resource
    private StockService stockService;

    @ApiOperation(value = "店铺库存")
    @GetMapping(value = "store")
    public R<PageList<StockVo>> getStoreStock(@ApiParam(value = "页码") @RequestParam(value = "current") int current,
                                              @ApiParam(value = "页容") @RequestParam(value = "size") int size,
                                              @ApiParam(value = "店铺id") @RequestParam(value = "storeId") int storeId) {
        IPage<Stock> stockPage = stockService.getStockByStore(current, size, storeId);
        PageList<StockVo> stockVoPageList = this.getStockVoPageList(stockPage);
        return R.ok(stockVoPageList);
    }

    private PageList<StockVo> getStockVoPageList(IPage<Stock> stockPage) {
        List<Stock> stockList = stockPage.getRecords();
        List<StockVo> stockVoList = stockService.getStockVoFromModelList(stockList);
        PageList<StockVo> ansData = new PageList<>();
        BeanUtils.copyProperties(stockPage, ansData);
        ansData.setRecords(stockVoList);
        return ansData;
    }
}
