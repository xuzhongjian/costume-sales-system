package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.Return;
import com.zjxu97.costume.model.entity.item.*;
import com.zjxu97.costume.model.param.QueryItemParam;
import com.zjxu97.costume.model.vo.ItemSizeVo;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.service.item.ItemSizeService;
import com.zjxu97.costume.service.item.ItemTypeService;
import com.zjxu97.costume.model.vo.ItemTypeVo;
import com.zjxu97.costume.model.vo.ItemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2019/12/29 23:47
 */
@RestController
@Api(tags = "商品相关")
@RequestMapping(Constants.API_PREFIX + "/items")
public class ItemsController {
    private final static Logger log = LoggerFactory.getLogger(ItemsController.class);

    @Resource
    private ItemTypeService itemTypeService;

    @Resource
    private ItemSizeService itemSizeService;

    @Resource
    private ItemService itemService;

    /**
     * OK
     */
    @ApiOperation(value = "查询商品")
    @PostMapping(value = "query")
    public R<List<ItemVo>> queryItems(@RequestBody QueryItemParam queryItemParam) {
        List<Item> items = itemService.queryItem(queryItemParam);

        List<ItemVo> collect = items.stream().map(item -> {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            return itemVo;
        }).collect(Collectors.toList());

        return Return.success(collect);
    }

    /**
     * OK
     */
    @ApiOperation(value = "列出品类")
    @GetMapping(value = "list-type")
    public R<List<ItemTypeVo>> listType() {
        List<ItemType> itemTypeList = itemTypeService.list(null);
        List<ItemTypeVo> itemTypeVoList = itemTypeList.stream()
                .sorted(Comparator.comparingInt(ItemType::getSex))
                .map(itemType -> {
                    ItemTypeVo itemTypeVo = new ItemTypeVo();
                    BeanUtils.copyProperties(itemType, itemTypeVo);
                    return itemTypeVo;
                }).collect(Collectors.toList());
        return Return.success(itemTypeVoList);
    }

    /**
     * OK
     */
    @ApiOperation(value = "列出大小")
    @GetMapping(value = "list-size")
    public R<List<ItemSizeVo>> listSize() {
        List<ItemSize> listSizeList = itemSizeService.list(null);

        List<ItemSizeVo> itemTypeVoList = listSizeList.stream()
                .map(itemSize -> {
                    ItemSizeVo itemSizeVo = new ItemSizeVo();
                    BeanUtils.copyProperties(itemSize, itemSizeVo);
                    return itemSizeVo;
                }).collect(Collectors.toList());
        return Return.success(itemTypeVoList);
    }


}
