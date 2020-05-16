package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.model.entity.Item;
import com.zjxu97.costume.model.entity.ItemBigType;
import com.zjxu97.costume.model.entity.ItemSize;
import com.zjxu97.costume.model.entity.ItemType;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.model.vo.ItemSizeVo;
import com.zjxu97.costume.model.vo.ItemTypeVo;
import com.zjxu97.costume.model.vo.ItemVo;
import com.zjxu97.costume.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2019/12/29 23:47
 */
@Slf4j
@RestController
@Api(tags = "商品相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/item")
public class ItemController {

    @Resource
    private ItemTypeService itemTypeService;

    @Resource
    private ItemBigTypeService itemBigTypeService;

    @Resource
    private ItemService itemService;

    @Resource
    private ItemSizeService itemSizeService;

    @Resource
    private ItemDetailService itemDetailService;

    @ApiOperation(value = "列出小种类下的产品")
    @GetMapping(value = "items")
    public R<Page<ItemVo>> listItem(@ApiParam(value = "种类id") @RequestParam(value = "itemTypeId") int typeId,
                                    @ApiParam(value = "页容") @RequestParam(value = "size") int size,
                                    @ApiParam(value = "页码") @RequestParam(value = "current") int current) {
        Page<Item> itemPage = itemService.itemList(typeId, size, current);
        List<ItemVo> itemVoList = itemPage.getRecords().stream().map(item -> {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            return itemVo;
        }).collect(Collectors.toList());

        Page<ItemVo> page = new Page<>();
        BeanUtils.copyProperties(itemPage, page);
        page.setRecords(itemVoList);
        return R.ok(page);
    }

    @ApiOperation(value = "列出产品的详细")
    @GetMapping(value = "details")
    public R<List<ItemDetailVo>> listItemDetail(@ApiParam(value = "产品id") @RequestParam(value = "itemId") int itemId) {
        return R.ok(itemDetailService.getDetailListByItemList(itemId));
    }

    @ApiOperation(value = "列出大种类")
    @GetMapping(value = "big-types")
    public R<List<ItemTypeVo>> listBigType() {
        List<ItemBigType> itemTypeList = itemBigTypeService.list(null);
        List<ItemTypeVo> itemTypeVoList = itemTypeList.stream().map(itemType -> {
            ItemTypeVo itemTypeVo = new ItemTypeVo();
            BeanUtils.copyProperties(itemType, itemTypeVo);
            return itemTypeVo;
        }).collect(Collectors.toList());
        return R.ok(itemTypeVoList);
    }

    @ApiOperation(value = "列出大种类下的小种类")
    @GetMapping(value = "types")
    public R<List<ItemTypeVo>> listType(@ApiParam(value = "大种类id") @RequestParam(value = "bigTypeId") int bigTypeId) {
        List<ItemType> itemTypeList = itemTypeService.list(new QueryWrapper<ItemType>().eq(bigTypeId > 0, "item_big_type_id", bigTypeId));
        List<ItemTypeVo> itemTypeVoList = itemTypeList.stream().map(itemType -> {
            ItemTypeVo itemTypeVo = new ItemTypeVo();
            BeanUtils.copyProperties(itemType, itemTypeVo);
            return itemTypeVo;
        }).collect(Collectors.toList());
        return R.ok(itemTypeVoList);
    }

    @ApiOperation(value = "列出大小")
    @GetMapping(value = "sizes")
    public R<List<ItemSizeVo>> listSize() {
        List<ItemSize> listSizeList = itemSizeService.list(null);

        List<ItemSizeVo> itemTypeVoList = listSizeList.stream().map(itemSize -> {
            ItemSizeVo itemSizeVo = new ItemSizeVo();
            BeanUtils.copyProperties(itemSize, itemSizeVo);
            return itemSizeVo;
        }).collect(Collectors.toList());
        return R.ok(itemTypeVoList);
    }

}
