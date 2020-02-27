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
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(CostumeConstants.API_PREFIX + "/items")
public class ItemController {

    @Resource
    private ItemTypeService itemTypeService;

    @Resource
    private ItemSizeService itemSizeService;

    @Resource
    private ItemDetailService itemDetailService;

    /**
     * 分页-完成
     */
    @ApiOperation(value = "列出某一类商品的详细", notes = "类别的id")
    @GetMapping(value = "type-detail")
    public R<PageList<ItemDetailVo>> itemTypeDetail(@ApiParam(value = "类别的id + 分页") ItemTypeDetailPageParam param) {
        PageList<ItemDetailVo> pageList = this.getItemDetailVoPageList(itemDetailService.getItemDetailByTypeId(param));
        return Ans.success(pageList);
    }

    /**
     * 分页-完成
     */
    @ApiOperation(value = "查询商品的详细", notes = "关键词、类型、大小")
    @GetMapping(value = "query-detail")
    public R<PageList<ItemDetailVo>> queryItemDetail(@ApiParam(value = "关键词、类型、大小") QueryItemDetailPageParam param) {
        PageList<ItemDetailVo> pageList = this.getItemDetailVoPageList(itemDetailService.queryItemDetail(param));
        return Ans.success(pageList);
    }

    /**
     * 分页-完成
     */
    @ApiOperation(value = "列出商品详细", notes = "使用商品的模糊id,不带有size")
    @GetMapping(value = "item-detail")
    public R<PageList<ItemDetailVo>> itemDetail(@ApiParam(value = "商品的模糊id + 分页") ItemDetailPageParam itemDetailPageParam) {
        PageList<ItemDetailVo> pageList = this.getItemDetailVoPageList(itemDetailService.getItemDetailByItemId(itemDetailPageParam));
        return Ans.success(pageList);
    }

    @NotNull
    private PageList<ItemDetailVo> getItemDetailVoPageList(IPage<ItemDetail> itemDetailPage) {
        List<ItemDetail> itemDetailList = itemDetailPage.getRecords();

        List<ItemDetailVo> itemDetailVoList = itemDetailService.getItemDetailVoFromModelList(itemDetailList);
        PageList<ItemDetailVo> ansData = new PageList<>();
        BeanUtils.copyProperties(itemDetailPage, ansData);
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
    public R<List<ItemSizeVo>> listSize(@ApiParam(value = "页号", defaultValue = "1") Integer pageNo,
                                        @ApiParam(value = "页容", defaultValue = "10") Integer pageSize) {
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
