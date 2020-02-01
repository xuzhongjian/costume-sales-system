package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Ans;
import com.zjxu97.costume.commons.CostumeConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据类别: 售出、退货、入库、出库、库存
 * 其中，售出、退货使用一个接口使用枚举区分；
 * 入库、出库使用同一个接口，使用枚举区分；
 * 库存单独使用一个接口。
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
@Api(tags = "销售数据相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/sale-data")
public class DataSaleController {

    @ApiOperation(value = "测试")
    @GetMapping(value = "test")
    public R<String> out() {
        return Ans.success("hello world");
    }

}
