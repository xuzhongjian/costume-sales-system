package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Control;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.DataElement;
import com.zjxu97.costume.service.SaleRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Y - 仅限一个 - [销售件数 销售额]
 * X - 仅限一个 - [时间 地理位置 商品种类 商品 性别 大小]
 * Control - [时间 地理位置 商品种类 商品 性别 大小] ps. 不能和Y存在重复
 * ---------------------------------------------------------------
 * 比如：
 * X : [时间 - 年]
 * Y : [销售件数]
 * Control : { 地理位置 = 武汉市 , 性别 = 男 }
 * 结果，获得 武汉市 男性服装 的 [销售件数] 按照[年度] 划分出来
 * {"2014":100,"2015":102,"2016":130,"2017":190,"2018":201,"2019":230}
 * ---------------------------------------------------------------
 * 比如：
 * X : [时间 - 月]
 * Y : [销售金额]
 * Control : { 地理位置 = 东北地区 , 商品种类 = 上衣 }
 * 结果，获得 东北地区 上衣 的 [Y 销售金额] 按照[X 月] 划分出来
 * {"2019-1":100,"2019-2":102,"2019-3":130,"2019-4":190,"2019-5":201,"2019-6":230}
 *
 * @author zjxu97
 * @date 2020/1/19 21:37
 */
@RestController
@Api(tags = "数据相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/data")
public class DataController {

    @Resource
    SaleRecordService saleRecordService;


    /**
     * @param yValue  DisplayTypeConstants
     * @param xType   Control
     * @param xValue  Control
     * @param control Control
     * @param from    yyyy-MM-dd
     * @param to      yyyy-MM-dd
     * @return json
     * @see Control
     */
    @ApiOperation(value = "数据")
    @GetMapping(value = "sales")
    public R<List<DataElement>> displayLocationSaleCount(@ApiParam(value = "Y_VALUE") @RequestParam(value = "yValue") String yValue,
                                                         @ApiParam(value = "X_TYPE") @RequestParam(value = "xType") String xType,
                                                         @ApiParam(value = "X_VALUE") @RequestParam(value = "xValue") String xValue,
                                                         @ApiParam(value = "开始时间") @RequestParam(value = "from") String from,
                                                         @ApiParam(value = "截止时间") @RequestParam(value = "to") String to,
                                                         Control control) {

        List<DataElement> dataList = saleRecordService.getDataList(control, from, to, xType, xValue, yValue);
        return R.ok(dataList);
    }
}
