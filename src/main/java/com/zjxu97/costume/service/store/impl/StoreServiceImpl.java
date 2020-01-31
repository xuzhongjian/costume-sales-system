package com.zjxu97.costume.service.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.mapper.StoreMapper;
import com.zjxu97.costume.model.param.KeyWordsPageParam;
import com.zjxu97.costume.model.param.LocationIdPageParam;
import com.zjxu97.costume.service.store.StoreService;
import com.zjxu97.costume.model.vo.StoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public IPage<Store> listStoresByDist(LocationIdPageParam locationIdPageParam) {
        QueryWrapper<Store> qw = qw().eq("district_id", locationIdPageParam.getLocationId());
        Page<Store> page = new Page<>();
        BeanUtils.copyProperties(locationIdPageParam, page);
        return this.page(page, qw);
    }

    @Override
    public IPage<Store> listStoresByCity(LocationIdPageParam locationIdPageParam) {
        int MAX_CITY_DIST_NUM = 99;
        return this.getStoreVos(MAX_CITY_DIST_NUM, locationIdPageParam);
    }

    @Override
    public IPage<Store> listStoresByProv(LocationIdPageParam locationIdPageParam) {
        int MAX_PROV_DIST_NUM = 9999;
        return this.getStoreVos(MAX_PROV_DIST_NUM, locationIdPageParam);
    }

    @Override
    public IPage<Store> listStoresByArea(LocationIdPageParam locationIdPageParam) {
        int MAX_AREA_DIST_NUM = 99999;
        return this.getStoreVos(MAX_AREA_DIST_NUM, locationIdPageParam);
    }

    @Override
    public IPage<Store> searchStores(KeyWordsPageParam param) {
        Page<Store> page = new Page<>();
        BeanUtils.copyProperties(param, page);
        String keyWords = param.getKeyWords();

        QueryWrapper<Store> qw = qw().like("store_name", keyWords).or().like("store_address", keyWords);

        // 两次搜索
//        List<Store> storesByName = this.list(qw().like("store_name", keyWords));
//        List<Store> storesByAddress = this.list(qw().like("store_address", keyWords));

        return this.page(page, qw);
    }

    private IPage<Store> getStoreVos(int max_area_dist_num, LocationIdPageParam locationIdPageParam) {
        Page<Store> page = new Page<>();
        BeanUtils.copyProperties(locationIdPageParam, page);
        Integer locationId = locationIdPageParam.getLocationId();
        QueryWrapper<Store> qw = qw().between("district_id", locationId, locationId + max_area_dist_num);

        return this.page(page, qw);
    }

    @Override
    public List<StoreVo> getStoreVoFromEntityList(List<Store> storeList) {
        return storeList.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<Store> qw() {
        return new QueryWrapper<>();
    }
}
