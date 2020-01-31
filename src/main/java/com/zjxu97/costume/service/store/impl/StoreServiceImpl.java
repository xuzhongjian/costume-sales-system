package com.zjxu97.costume.service.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.mapper.StoreMapper;
import com.zjxu97.costume.service.store.StoreService;
import com.zjxu97.costume.model.vo.StoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public List<StoreVo> listStoresByDist(Integer districtId, Integer pageNo, Integer pageSize) {
        List<Store> stores = this.list(qw().eq("district_id", districtId)
                .last("limit " + (pageNo - 1) * pageSize + " , " + pageSize));
        return stores.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<StoreVo> listStoresByCity(Integer cityId, Integer pageNo, Integer pageSize) {
        int MAX_CITY_DIST_NUM = 99;
        return this.getStoreVos(cityId, MAX_CITY_DIST_NUM, pageNo, pageSize);
    }

    @Override
    public List<StoreVo> listStoresByProv(Integer provId, Integer pageNo, Integer pageSize) {
        int MAX_PROV_DIST_NUM = 9999;
        return this.getStoreVos(provId, MAX_PROV_DIST_NUM, pageNo, pageSize);
    }

    @Override
    public List<StoreVo> listStoresByArea(Integer areaId, Integer pageNo, Integer pageSize) {
        areaId *= 100000;
        int MAX_AREA_DIST_NUM = 99999;
        return this.getStoreVos(areaId, MAX_AREA_DIST_NUM, pageNo, pageSize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<StoreVo> searchStores(String keyWord, Integer pageNo, Integer pageSize) {
        int fromIndex = (pageNo - 1) * pageSize;
        // 两次搜索
        List<Store> storesByName = this.list(qw().like("store_name", keyWord));
        List<Store> storesByAddress = this.list(qw().like("store_address", keyWord));
        // 滤重
        Set<Store> ansStoreSet = new TreeSet(Comparator.comparing(Store::getId));
        ansStoreSet.addAll(storesByName);
        ansStoreSet.addAll(storesByAddress);
        return ansStoreSet.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList()).subList(fromIndex, fromIndex + pageSize);
    }

    private List<StoreVo> getStoreVos(Integer areaId, int max_area_dist_num, Integer pageNo, Integer pageSize) {
        List<Store> stores = this.list(qw().between("district_id", areaId, areaId + max_area_dist_num)
                .last("limit " + (pageNo - 1) * pageSize + " , " + pageSize));
        return stores.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());
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
