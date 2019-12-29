package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.common.Constants;
import com.zjxu97.costume.common.RetFunc;
import com.zjxu97.costume.model.CostumeType;
import com.zjxu97.costume.model.Item;
import com.zjxu97.costume.service.costume.CostumeTypeService;
import com.zjxu97.costume.service.item.ItemService;
import com.zjxu97.costume.vo.CostumeTypeVo;
import com.zjxu97.costume.vo.ItemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
@Api(tags = "服装相关")
@RequestMapping(Constants.API_PREFIX + "/costume")
public class CostumeController {
    private final static Logger log = LoggerFactory.getLogger(CostumeController.class);

    @Resource
    CostumeTypeService costumeTypeService;

    @Resource
    ItemService itemService;

    @ApiOperation(value = "列出品类")
    @GetMapping(value = "list-costumes")
    public R<List<CostumeTypeVo>> listCostumes() {
        List<CostumeType> costumeTypes = costumeTypeService.getBaseMapper().selectList(null);
        List<CostumeTypeVo> costumeTypeVos = costumeTypes.stream().map(costumeType -> {
            CostumeTypeVo costumeTypeVo = new CostumeTypeVo();
            BeanUtils.copyProperties(costumeType, costumeTypeVo);
            return costumeTypeVo;
        }).collect(Collectors.toList());
        return RetFunc.success(costumeTypeVos);
    }

    @ApiOperation(value = "查询单品")
    @GetMapping(value = "query-items")
    public R<List<ItemVo>> queryItems() {
        List<Item> items = itemService.getBaseMapper().selectList(null);
        List<ItemVo> itemVos = items.stream().map(item -> {
            ItemVo itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            return itemVo;
        }).collect(Collectors.toList());
        return RetFunc.success(itemVos);
    }

    @ApiOperation(value = "搜索单品")
    @GetMapping(value = "search-items")
    public R<List<ItemVo>> searchItems(@RequestParam String keyWord) {
        List<ItemVo> itemVos = itemService.searchItems(keyWord);
        return RetFunc.success(itemVos);

    }

}
