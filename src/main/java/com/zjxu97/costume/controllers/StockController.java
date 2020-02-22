package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.*;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.entity.sale.Stock;
import com.zjxu97.costume.model.param.QueryItemDetailPageParam;
import com.zjxu97.costume.model.param.QueryStockPageParam;
import com.zjxu97.costume.model.param.StockInOutParam;
import com.zjxu97.costume.model.param.StoreStockPageParam;
import com.zjxu97.costume.model.vo.StockVo;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.sale.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 需要分页
 *
 * @author zjxu97
 * @date 2020/1/19 21:33
 */

@RestController
@Api(tags = "库存相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/stocks")
public class StockController {

    @Resource
    private ItemDetailService itemDetailService;

    @Resource
    private StockService stockService;

    /**
     *
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
        return Ans.success(true);
    }

    /**
     *
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
        return Ans.success(true);
    }

    /**
     * 分页-完成
     */
    @ApiOperation(value = "库存查询", notes = "店铺、关键字、类别、大小")
    @GetMapping(value = "query")
    public R<PageList<StockVo>> queryStock(@RequestParam QueryStockPageParam param) {
        QueryItemDetailPageParam queryItemDetailPageParam = new QueryItemDetailPageParam();
        BeanUtils.copyProperties(param, queryItemDetailPageParam);

        List<Integer> itemDetailIdList = itemDetailService.queryItemDetailList(queryItemDetailPageParam)
                .stream().map(ItemDetail::getId).collect(Collectors.toList());

        Integer storeId = param.getStoreId();
        PageParam page = new PageParam();
        BeanUtils.copyProperties(param, page);

        IPage<Stock> stockPage = stockService.getStockByItemList(itemDetailIdList, storeId, page);

        PageList<StockVo> stockVoPageList = this.getStockVoPageList(stockPage);
        return Ans.success(stockVoPageList);
    }

    /**
     * 分页-完成
     */
    @ApiOperation(value = "店铺库存", notes = "店铺")
    @PostMapping(value = "store")
    public R<PageList<StockVo>> getStoreStock(@RequestBody StoreStockPageParam param) {
        IPage<Stock> stockPage = stockService.getStockByStore(param);
        PageList<StockVo> stockVoPageList = this.getStockVoPageList(stockPage);
        return Ans.success(stockVoPageList);
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
