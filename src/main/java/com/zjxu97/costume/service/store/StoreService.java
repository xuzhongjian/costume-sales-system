package com.zjxu97.costume.service.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.Store;
import com.zjxu97.costume.vo.StoreVo;

import java.util.List;

public interface StoreService extends IService<Store> {
    List<StoreVo> listStoresByDist(Integer districtId);

    List<StoreVo> listStoresByCity(Integer cityId);

    List<StoreVo> listStoresByProv(Integer provId);

    public List<StoreVo> listStoresByArea(Integer areaId);

    public List<StoreVo> searchStores(String keyWord);
}
