package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.PageList;
import com.zjxu97.costume.commons.Ans;
import com.zjxu97.costume.model.entity.item.ItemDetail;
import com.zjxu97.costume.model.entity.item.ItemSize;
import com.zjxu97.costume.model.entity.item.ItemType;
import com.zjxu97.costume.model.param.ItemDetailPageParam;
import com.zjxu97.costume.model.param.ItemTypeDetailPageParam;
import com.zjxu97.costume.model.param.QueryItemDetailPageParam;
import com.zjxu97.costume.model.vo.ItemDetailVo;
import com.zjxu97.costume.model.vo.ItemSizeVo;
import com.zjxu97.costume.model.vo.ItemTypeVo;
import com.zjxu97.costume.service.item.ItemDetailService;
import com.zjxu97.costume.service.item.ItemSizeService;
import com.zjxu97.costume.service.item.ItemTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
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
@RequestMapping(CostumeConstants.API_PREFIX + "/items")
public class ItemController {
    private final static Logger log = LoggerFactory.getLogger(ItemController.class);

    @Resource
    private ItemTypeService itemTypeService;

    @Resource
    private ItemSizeService itemSizeService;

    @Resource
    private ItemDetailService itemDetailService;

    /**
     * TODO-分页-完成
     */
    @ApiOperation(value = "列出某一类商品的详细", notes = "类别的id")
    @PostMapping(value = "type-detail")
    public R<PageList<ItemDetailVo>> itemTypeDetail(@RequestBody ItemTypeDetailPageParam param) {
        IPage<ItemDetail> itemDetailIPage = itemDetailService.getItemDetailByTypeId(param);
        PageList<ItemDetailVo> pageList = this.getItemDetailVoPageList(itemDetailIPage);
        return Ans.success(pageList);
    }

    /**
     * TODO-分页-完成
     */
    @ApiOperation(value = "查询商品的详细", notes = "关键词、类型、大小")
    @PostMapping(value = "query-detail")
    public R<PageList<ItemDetailVo>> queryItemDetail(@RequestBody QueryItemDetailPageParam param) {
        IPage<ItemDetail> itemDetailIPage = itemDetailService.queryItemDetail(param);
        PageList<ItemDetailVo> pageList = this.getItemDetailVoPageList(itemDetailIPage);
        return Ans.success(pageList);
    }

    /**
     * TODO-分页-完成
     */
    @ApiOperation(value = "列出商品详细", notes = "使用商品的模糊id,不带有size")
    @PostMapping(value = "item-detail")
    public R<PageList<ItemDetailVo>> itemDetail(@RequestBody ItemDetailPageParam param) {
        IPage<ItemDetail> itemDetailIPage = itemDetailService.getItemDetailByItemId(param);
        PageList<ItemDetailVo> pageList = this.getItemDetailVoPageList(itemDetailIPage);
        return Ans.success(pageList);
    }

    @NotNull
    private PageList<ItemDetailVo> getItemDetailVoPageList(IPage<ItemDetail> itemDetailIPage) {
        List<ItemDetail> itemDetailList = itemDetailIPage.getRecords();

        List<ItemDetailVo> itemDetailVoList = itemDetailService.getItemDetailVoFromEntityList(itemDetailList);
        PageList<ItemDetailVo> ansData = new PageList<>();
        BeanUtils.copyProperties(itemDetailIPage, ansData);
        ansData.setRecords(itemDetailVoList);
        return ansData;
    }

    /**
     *
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
        return Ans.success(itemTypeVoList);
    }

    /**
     *
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
        return Ans.success(itemTypeVoList);
    }

}
