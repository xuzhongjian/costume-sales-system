package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.Ans;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 入库、出库、库存
 * <p>
 * 商品纬度: 大小、性别、类别、模糊商品、具体商品
 * 位置纬度: 全部、大区域、省、市、区、店铺
 * <p>
 * 1、某件衣服的总体销售趋势
 * 2、某个商店的总体销售趋势
 *
 * @author zjxu97
 * @date 2020/1/19 21:37
 */
@RestController
@Api(tags = "库存数据相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/stock-data")
public class DataStockController {

    @ApiOperation(value = "测试")
    @GetMapping(value = "test")
    public R<String> out() {
        return Ans.success("hello world");
    }

}
