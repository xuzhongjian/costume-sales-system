package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
@Api(tags = "销售数据相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/data")
public class SaleController {

    @Resource
    StoreService storeService;

    @ApiOperation(value = "地区销售件数")
    @GetMapping(value = "sales")
    public R<Map<Integer, Integer>> displayLocationSaleCount(@ApiParam(value = "地区等级") @RequestParam(value = "locationClass") int locationClass,
                                                             @ApiParam(value = "locationId") @RequestParam(value = "locationId") int locationId,
                                                             @ApiParam(value = "splitType") @RequestParam(value = "splitType") String splitType,
                                                             @ApiParam(value = "开始时间") @RequestParam(value = "from") String from,
                                                             @ApiParam(value = "截止时间") @RequestParam(value = "to") String to) {
        List<Integer> storeList = storeService.listStoresByLocation(locationClass, locationId).stream().map(Store::getId).collect(Collectors.toList());

        return R.ok(null);
    }
}
