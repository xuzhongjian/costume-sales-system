package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.Rx;
import com.zjxu97.costume.model.CostumeType;
import com.zjxu97.costume.model.Item;
import com.zjxu97.costume.param.QueryItemsParam;
import com.zjxu97.costume.service.costume.CostumeTypeService;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.vo.CostumeTypeVo;
import com.zjxu97.costume.vo.ItemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2019/12/29 23:47
 */
@RestController
@Api(tags = "商品相关")
@RequestMapping(Constants.API_PREFIX + "/costume")
public class CostumeController {
    private final static Logger log = LoggerFactory.getLogger(CostumeController.class);

    @Resource
    private CostumeTypeService costumeTypeService;

    @Resource
    private ItemService itemService;

    @ApiOperation(value = "列出品类")
    @GetMapping(value = "list-costumes")
    public R<List<CostumeTypeVo>> listCostumes() {
        List<CostumeType> costumeTypes = costumeTypeService.list(null);
        List<CostumeTypeVo> costumeTypeVos = costumeTypes.stream().map(costumeType -> {
            CostumeTypeVo costumeTypeVo = new CostumeTypeVo();
            BeanUtils.copyProperties(costumeType, costumeTypeVo);
            return costumeTypeVo;
        }).collect(Collectors.toList());
        return Rx.success(costumeTypeVos);
    }

    @ApiOperation(value = "查询商品")
    @PostMapping(value = "query-item")
    public R<List<ItemVo>> queryItems(@RequestBody QueryItemsParam queryItemsParam) {
        List<Item> items = itemService.queryItems(queryItemsParam);

        List<ItemVo> collect = items.stream().map(item -> {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            return itemVo;
        }).collect(Collectors.toList());

        return Rx.success(collect);
    }

    @ApiOperation(value = "查询品类商品")
    @GetMapping(value = "costume-items")
    public R<List<ItemVo>> queryItemsFromCostume(@RequestParam Integer costumeId) {
        List<Item> items = itemService.list(new QueryWrapper<Item>().eq("costume_id", costumeId));
        List<ItemVo> collect = items.stream().map(item -> {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            return itemVo;
        }).collect(Collectors.toList());

        return Rx.success(collect);
    }

    @ApiOperation(value = "搜索商品")
    @GetMapping(value = "search-items")
    public R<List<ItemVo>> searchItems(@RequestParam String keyWord) {
        List<ItemVo> itemVos = itemService.searchItems(keyWord);
        return Rx.success(itemVos);
    }
}
