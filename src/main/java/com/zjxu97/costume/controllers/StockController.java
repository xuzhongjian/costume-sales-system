package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.InOutEnum;
import com.zjxu97.costume.commons.Return;
import com.zjxu97.costume.model.dto.StockDisplayDTO;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.param.QueryItemDetailParam;
import com.zjxu97.costume.model.param.QueryStockParam;
import com.zjxu97.costume.model.param.StockInOutParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;
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
 * TODO
 *
 * @author zjxu97
 * @date 2020/1/19 21:33
 */

@RestController
@Api(tags = "库存相关")
@RequestMapping(Constants.API_PREFIX + "/stocks")
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
        return Return.success(true);
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
        return Return.success(true);
    }

    /**
     *
     */
    @ApiOperation(value = "库存查询", notes = "店铺、关键字、类别、大小")
    @PostMapping(value = "query")
    public R<List<StockVo>> queryStock(@RequestBody QueryStockParam queryStockParam) {
        Integer pageNo = queryStockParam.getPageNo();
        Integer pageSize = queryStockParam.getPageSize();
        QueryItemDetailParam queryItemDetailParam = new QueryItemDetailParam();
        BeanUtils.copyProperties(queryStockParam, queryItemDetailParam);
        List<Integer> itemIdList = itemDetailService.queryItemDetail(queryItemDetailParam).stream().map(ItemDetailVo::getId).collect(Collectors.toList());
        Integer storeId = queryStockParam.getStoreId();
        List<StockVo> stockVoList = stockService.getStockByItemList(itemIdList, storeId, pageNo, pageSize).
                stream().map(stockDisplayDTO -> {
            StockVo stockVo = new StockVo();
            BeanUtils.copyProperties(stockDisplayDTO, stockVo);
            return stockVo;
        }).collect(Collectors.toList());

        return Return.success(stockVoList);
    }

    /**
     *
     */
    @ApiOperation(value = "店铺库存", notes = "店铺")
    @GetMapping(value = "store")
    public R<List<StockVo>> getItemComByStore(@RequestParam Integer storeId, Integer pageNo, Integer pageSize) {
        List<StockDisplayDTO> stockDisplayDTOList = stockService.getStockByStore(storeId, pageNo, pageSize);
        List<StockVo> stockVoList = stockDisplayDTOList.stream().map(stockDisplayDTO -> {
            StockVo stockVo = new StockVo();
            BeanUtils.copyProperties(stockDisplayDTO, stockVo);
            return stockVo;
        }).collect(Collectors.toList());
        return Return.success(stockVoList);
    }
}
