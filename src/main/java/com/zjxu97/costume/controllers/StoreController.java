package com.zjxu97.costume.controllers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.zjxu97.costume.commons.Ans;
import com.zjxu97.costume.commons.CostumeConstants;
import com.zjxu97.costume.commons.PageList;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.param.KeyWordsPageParam;
import com.zjxu97.costume.model.param.LocationIdPageParam;
import com.zjxu97.costume.model.param.StoreParam;
import com.zjxu97.costume.model.vo.StoreVo;
import com.zjxu97.costume.service.store.StoreService;
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
 * 需要分页
 *
 * @author zjxu97
 * @date 2019/12/29 22:25
 */
@RestController
@Api(tags = "店铺相关")
@RequestMapping(CostumeConstants.API_PREFIX + "/store")
public class StoreController {
    private final static Logger log = LoggerFactory.getLogger(StoreController.class);

    @Resource
    private StoreService storeService;

    /**
     * 分页-完成
     */
    @ApiOperation(value = "列出区县的所有店铺")
    @PostMapping(value = "list-stores")
    public R<PageList<StoreVo>> listStores(@RequestBody LocationIdPageParam locationIdPageParam) {
        IPage<Store> storeIPage = storeService.listStoresByDist(locationIdPageParam);
        PageList<StoreVo> storeVoPageList = this.getStoreVoPageList(storeIPage);
        return Ans.success(storeVoPageList);
    }


    /**
     * 分页-完成
     */
    @ApiOperation(value = "列出区县的所有店铺")
    @PostMapping(value = "list-stores-by-district")
    public R<PageList<StoreVo>> listStoresByDist(@RequestBody LocationIdPageParam locationIdPageParam) {
        IPage<Store> storeIPage = storeService.listStoresByDist(locationIdPageParam);
        PageList<StoreVo> storeVoPageList = this.getStoreVoPageList(storeIPage);
        return Ans.success(storeVoPageList);
    }

    /**
     * 分页-完成
     */
    @ApiOperation(value = "列出城市的所有店铺")
    @PostMapping(value = "list-stores-by-city")
    public R<PageList<StoreVo>> listStoresByCity(@RequestBody LocationIdPageParam locationIdPageParam) {
        IPage<Store> storeIPage = storeService.listStoresByCity(locationIdPageParam);
        PageList<StoreVo> storeVoPageList = this.getStoreVoPageList(storeIPage);
        return Ans.success(storeVoPageList);
    }

    /**
     * 分页-完成
     */
    @ApiOperation(value = "列出省份的所有店铺")
    @PostMapping(value = "list-stores-by-prov")
    public R<PageList<StoreVo>> listStoresByProv(@RequestBody LocationIdPageParam locationIdPageParam) {
        IPage<Store> storeIPage = storeService.listStoresByProv(locationIdPageParam);
        PageList<StoreVo> storeVoPageList = this.getStoreVoPageList(storeIPage);
        return Ans.success(storeVoPageList);
    }

    /**
     * 分页-完成
     */
    @ApiOperation(value = "列出大区的所有店铺")
    @PostMapping(value = "list-stores-by-area")
    public R<PageList<StoreVo>> listStoresByArea(@RequestBody LocationIdPageParam locationIdPageParam) {
        IPage<Store> storeIPage = storeService.listStoresByArea(locationIdPageParam);
        PageList<StoreVo> storeVoPageList = this.getStoreVoPageList(storeIPage);
        return Ans.success(storeVoPageList);
    }

    /**
     * 分页-完成
     */
    @ApiOperation(value = "搜索店铺")
    @PostMapping(value = "search-stores")
    public R<PageList<StoreVo>> searchStores(@RequestBody KeyWordsPageParam keyWordsPageParam) {
        IPage<Store> storeIPage = storeService.searchStores(keyWordsPageParam);
        PageList<StoreVo> storeVoPageList = this.getStoreVoPageList(storeIPage);
        return Ans.success(storeVoPageList);
    }

    @ApiOperation(value = "添加店铺", notes = "不要填id")
    @PostMapping(value = "add-store")
    public R<String> addStore(@RequestBody StoreParam storeParam) {
        Store store = new Store();
        BeanUtils.copyProperties(storeParam, store);
        boolean b = storeService.saveOrUpdate(store);
        return b ? Ans.success("添加成功") : Ans.failure(new Exception("添加失败！"));
    }

    @ApiOperation(value = "删除店铺")
    @GetMapping(value = "del-store")
    public R<String> delStore(@RequestParam Integer storeId) {
        boolean isRemove = storeService.removeById(storeId);
        return isRemove ? Ans.success("删除成功") : Ans.failure(new Exception("删除失败！"));
    }

    @ApiOperation(value = "更新店铺", notes = "需要填id")
    @PostMapping(value = "update-store")
    public R<String> updStore(@RequestBody StoreParam storeParam) {
        Store store = new Store();
        BeanUtils.copyProperties(storeParam, store);
        boolean isSave = storeService.updateById(store);
        return isSave ? Ans.success("更新成功") : Ans.failure(new Exception("更新失败！"));
    }

    private PageList<StoreVo> getStoreVoPageList(IPage<Store> storeIPage) {
        PageList<StoreVo> storeVoPageList = new PageList<>();
        BeanUtils.copyProperties(storeIPage, storeVoPageList);
        List<Store> storeList = storeIPage.getRecords();
        List<StoreVo> storeVoList = storeList.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());
        storeVoPageList.setRecords(storeVoList);
        return storeVoPageList;
    }

}
