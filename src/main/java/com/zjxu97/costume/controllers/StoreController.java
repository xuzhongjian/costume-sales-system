package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.PageList;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.vo.StoreVo;
import com.zjxu97.costume.service.StoreService;
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
 * 需要分页
 *
 * @author zjxu97
 * @date 2019/12/29 22:25
 */
@Slf4j
@RestController
@Api(tags = "店铺相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    @GetMapping(value = "stores")
    @ApiOperation(value = "根据地理位置获取店铺")
    public R<Page<StoreVo>> listStores(@ApiParam(value = "locationId") @RequestParam(value = "locationId") int locationId,
                                       @ApiParam(value = "页容") @RequestParam(value = "size") int size,
                                       @ApiParam(value = "页码") @RequestParam(value = "current") int current) {
        Page<Store> storeIPage = storeService.listStoresByLocation(locationId, size, current);
        List<StoreVo> storeVoList = storeIPage.getRecords().stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());

        Page<StoreVo> page = new Page<>();
        BeanUtils.copyProperties(storeIPage, page);
        page.setRecords(storeVoList);
        return R.ok(page);
    }
}