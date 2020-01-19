package com.zjxu97.costume.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.common.Constants;
import com.zjxu97.costume.common.RetFunc;
import com.zjxu97.costume.model.Store;
import com.zjxu97.costume.param.StoreParam;
import com.zjxu97.costume.service.store.StoreService;
import com.zjxu97.costume.vo.StoreVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zjxu97
 * @date 2019/12/29 22:25
 */
@RestController
@Api(tags = "店铺相关")
@RequestMapping(Constants.API_PREFIX + "/store")
public class StoreController {
    private final static Logger log = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private StoreService storeService;

    @ApiOperation(value = "列出区县的所有店铺")
    @GetMapping(value = "list-stores-by-district")
    public R<List<StoreVo>> listStoresByDist(@RequestParam Integer districtId) {
        List<StoreVo> storeVos = storeService.listStoresByDist(districtId);
        return RetFunc.success(storeVos);
    }

    @ApiOperation(value = "列出城市的所有店铺")
    @GetMapping(value = "list-stores-by-city")
    public R<List<StoreVo>> listStoresByCity(@RequestParam Integer cityId) {
        List<StoreVo> storeVos = storeService.listStoresByCity(cityId);
        return RetFunc.success(storeVos);
    }

    @ApiOperation(value = "列出省份的所有店铺")
    @GetMapping(value = "list-stores-by-prov")
    public R<List<StoreVo>> listStoresByProv(@RequestParam Integer provId) {
        List<StoreVo> storeVos = storeService.listStoresByProv(provId);
        return RetFunc.success(storeVos);
    }

    @ApiOperation(value = "列出大区的所有店铺")
    @GetMapping(value = "list-stores-by-area")
    public R<List<StoreVo>> listStoresByArea(@RequestParam Integer area) {
        List<StoreVo> storeVos = storeService.listStoresByArea(area);
        return RetFunc.success(storeVos);
    }

    @ApiOperation(value = "添加店铺", notes = "不要填id")
    @PostMapping(value = "add-store")
    public R<String> addStore(@RequestBody StoreParam storeParam) {
        Store store = new Store();
        BeanUtils.copyProperties(storeParam, store);
        boolean b = storeService.saveOrUpdate(store);
        return b ? RetFunc.success("添加成功") : RetFunc.failure(new Exception("添加失败！"));
    }

    @ApiOperation(value = "删除店铺")
    @GetMapping(value = "del-store")
    public R<String> delStore(@RequestParam Integer storeId) {
        boolean isRemove = storeService.removeById(storeId);
        return isRemove ? RetFunc.success("删除成功") : RetFunc.failure(new Exception("删除失败！"));
    }

    @ApiOperation(value = "更新店铺", notes = "需要填id")
    @PostMapping(value = "update-store")
    public R<String> updStore(@RequestBody StoreParam storeParam) {
        Store store = new Store();
        BeanUtils.copyProperties(storeParam, store);
        boolean isSave = storeService.updateById(store);
        return isSave ? RetFunc.success("更新成功") : RetFunc.failure(new Exception("更新失败！"));
    }

    @ApiOperation(value = "搜索店铺")
    @GetMapping(value = "search-stores")
    public R<List<StoreVo>> searchStores(@RequestParam String keyWord) {
        List<StoreVo> storeVos = storeService.searchStores(keyWord);
        return RetFunc.success(storeVos);
    }

}
