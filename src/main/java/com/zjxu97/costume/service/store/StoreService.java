package com.zjxu97.costume.service.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.vo.StoreVo;

import java.util.List;

public interface StoreService extends IService<Store> {
    List<StoreVo> listStoresByDist(Integer districtId, Integer pageNo, Integer pageSize);

    List<StoreVo> listStoresByCity(Integer cityId, Integer pageNo, Integer pageSize);

    List<StoreVo> listStoresByProv(Integer provId, Integer pageNo, Integer pageSize);

    List<StoreVo> listStoresByArea(Integer areaId, Integer pageNo, Integer pageSize);

    List<StoreVo> searchStores(String keyWord, Integer pageNo, Integer pageSize);

    List<StoreVo> getStoreVoFromEntityList(List<Store> storeList);
}
