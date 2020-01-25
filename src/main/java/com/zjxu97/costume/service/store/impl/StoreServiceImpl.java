package com.zjxu97.costume.service.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.Store;
import com.zjxu97.costume.mapper.StoreMapper;
import com.zjxu97.costume.service.store.StoreService;
import com.zjxu97.costume.vo.StoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public List<StoreVo> listStoresByDist(Integer districtId) {
        List<Store> stores = this.list(qw().eq("district_id", districtId));
        return stores.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());
    }


    @Override
    public List<StoreVo> listStoresByCity(Integer cityId) {
        int MAX_CITY_DIST_NUM = 99;
        return getStoreVos(cityId, MAX_CITY_DIST_NUM);
    }

    @Override
    public List<StoreVo> listStoresByProv(Integer provId) {
        int MAX_PROV_DIST_NUM = 9999;
        return getStoreVos(provId, MAX_PROV_DIST_NUM);
    }

    @Override
    public List<StoreVo> listStoresByArea(Integer areaId) {
        areaId *= 100000;
        int MAX_AREA_DIST_NUM = 99999;
        return getStoreVos(areaId, MAX_AREA_DIST_NUM);
    }

    private List<StoreVo> getStoreVos(Integer areaId, int max_area_dist_num) {
        List<Store> stores = this.list(qw().between("district_id", areaId, areaId + max_area_dist_num));
        return stores.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());
    }

    @Override
    public List<StoreVo> searchStores(String keyWord) {
        List<Store> storesByName = this.list(qw().like("store_name", keyWord));
        List<Store> storesByAddress = this.list(qw().like("store_address", keyWord));
        //滤重
        HashSet<Store> ansStores = new HashSet<>();
        ansStores.addAll(storesByName);
        ansStores.addAll(storesByAddress);
        return ansStores.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<Store> qw() {
        return new QueryWrapper<>();
    }
}
