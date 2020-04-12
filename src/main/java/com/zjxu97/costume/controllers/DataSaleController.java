package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.CostumeConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据类别: 售出、退货、入库、出库、库存
 * 其中，售出、退货使用一个接口使用枚举区分；
 * 入库、出库使用同一个接口，使用枚举区分；
 * 库存单独使用一个接口。
 * 商品纬度: 大小、性别、类别、模糊商品、具体商品
 * 位置纬度: 全部、大区域、省、市、区、店铺
 * 1、某件衣服的总体销售趋势
 * 2、某个商店的总体销售趋势
 *
 * @author zjxu97
 * @date 2020/1/19 21:37
 */
@RestController
@Api(tags = "销售数据相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/sale-data")
public class DataSaleController {

    @ApiOperation(value = "地区销售件数")
    @GetMapping(value = "location-count")
    public R<String> displayLocationSaleCount(@ApiParam(value = "地区等级") @RequestParam(value = "locationClass") int locationClass,
                                              @ApiParam(value = "locationId") @RequestParam(value = "locationId") int locationId,
                                              @ApiParam(value = "splitType") @RequestParam(value = "splitType") String splitType,
                                              @ApiParam(value = "开始时间") @RequestParam(value = "from") String from,
                                              @ApiParam(value = "截止时间") @RequestParam(value = "to") String to) {
        return R.ok("hello world");
    }

    @ApiOperation(value = "店铺销售件数")
    @GetMapping(value = "store-count")
    public R<String> displayStoreSaleData(@ApiParam(value = "地区等级") @RequestParam(value = "locationClass") int locationClass,
                                          @ApiParam(value = "locationId") @RequestParam(value = "locationId") int locationId,
                                          @ApiParam(value = "splitType") @RequestParam(value = "splitType") String splitType,
                                          @ApiParam(value = "开始时间") @RequestParam(value = "from") String from,
                                          @ApiParam(value = "截止时间") @RequestParam(value = "to") String to) {
        return R.ok("hello world");
    }

    @ApiOperation(value = "地区销售总额")
    @GetMapping(value = "location-amount")
    public R<String> displayLocationSaleAmount(@ApiParam(value = "地区等级") @RequestParam(value = "locationClass") int locationClass,
                                               @ApiParam(value = "locationId") @RequestParam(value = "locationId") int locationId,
                                               @ApiParam(value = "splitType") @RequestParam(value = "splitType") String splitType,
                                               @ApiParam(value = "开始时间") @RequestParam(value = "from") String from,
                                               @ApiParam(value = "截止时间") @RequestParam(value = "to") String to) {
        return R.ok("hello world");
    }

    @ApiOperation(value = "店铺销售总额")
    @GetMapping(value = "store-amount")
    public R<String> displayStoreSaleAmount(@ApiParam(value = "地区等级") @RequestParam(value = "locationClass") int locationClass,
                                            @ApiParam(value = "locationId") @RequestParam(value = "locationId") int locationId,
                                            @ApiParam(value = "splitType") @RequestParam(value = "splitType") String splitType,
                                            @ApiParam(value = "开始时间") @RequestParam(value = "from") String from,
                                            @ApiParam(value = "截止时间") @RequestParam(value = "to") String to) {
        return R.ok("hello world");
    }
}
