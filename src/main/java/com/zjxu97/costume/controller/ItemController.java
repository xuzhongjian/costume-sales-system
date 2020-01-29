package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Constants;
import com.zjxu97.costume.commons.Return;
import com.zjxu97.costume.model.entity.item.ItemSize;
import com.zjxu97.costume.model.entity.item.ItemType;
import com.zjxu97.costume.model.param.QueryItemDetailParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.model.vo.ItemSizeVo;
import com.zjxu97.costume.model.vo.ItemTypeVo;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.item.ItemSizeService;
import com.zjxu97.costume.service.item.ItemTypeService;
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
public class ItemController {
    private final static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Resource
    private ItemTypeService itemTypeService;

    @Resource
    private ItemSizeService itemSizeService;

    @Resource
    private ItemDetailService itemDetailService;

    /**
     * OK
     */
    @ApiOperation(value = "列出某一类商品的详细", notes = "类别的id")
    @GetMapping(value = "type-detail")
    public R<List<ItemDetailVo>> itemTypeDetail(@RequestParam(name = "类别的id") Integer itemTypeId,
                                                @RequestParam(name = "页号", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "页容", defaultValue = "10") Integer pageSize) {
        List<ItemDetailVo> itemDetailVoList = itemDetailService.getItemDetailByTypeId(itemTypeId, pageNo, pageSize);
        return Return.success(itemDetailVoList);
    }

    /**
     * OK
     */
    @ApiOperation(value = "查询商品的详细", notes = "关键词、类型、大小")
    @PostMapping(value = "query-detail")
    public R<List<ItemDetailVo>> itemTypeDetail(@RequestBody QueryItemDetailParam queryItemDetailParam) {
        List<ItemDetailVo> itemDetailVoList = itemDetailService.queryItemDetail(queryItemDetailParam);
        return Return.success(itemDetailVoList);
    }

    /**
     * OK
     */
    @ApiOperation(value = "列出商品详细", notes = "使用商品的模糊id")
    @GetMapping(value = "item-detail")
    public R<List<ItemDetailVo>> itemDetail(@RequestParam(name = "商品的模糊id") Integer itemId,
                                            @RequestParam(name = "页号", defaultValue = "1") Integer pageNo,
                                            @RequestParam(name = "页容", defaultValue = "10") Integer pageSize) {
        List<ItemDetailVo> itemDetailVoList = itemDetailService.getItemDetailByItemId(itemId, pageNo, pageSize);
        return Return.success(itemDetailVoList);
    }

    /**
     * OK
     */
    @ApiOperation(value = "列出品类")
    @GetMapping(value = "list-type")
    public R<List<ItemTypeVo>> listType() {
        List<ItemType> itemTypeList = itemTypeService.list(null);
        List<ItemTypeVo> itemTypeVoList = itemTypeList.stream()
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
    public R<List<ItemSizeVo>> listSize(
            @RequestParam(name = "页号", defaultValue = "1") Integer pageNo,
            @RequestParam(name = "页容", defaultValue = "10") Integer pageSize) {
        List<ItemSize> listSizeList = itemSizeService.list(new QueryWrapper<ItemSize>()
                .last("limit " + (pageNo - 1) * pageSize + " , " + pageSize));

        List<ItemSizeVo> itemTypeVoList = listSizeList.stream()
                .map(itemSize -> {
                    ItemSizeVo itemSizeVo = new ItemSizeVo();
                    BeanUtils.copyProperties(itemSize, itemSizeVo);
                    return itemSizeVo;
                }).collect(Collectors.toList());
        return Return.success(itemTypeVoList);
    }

}
