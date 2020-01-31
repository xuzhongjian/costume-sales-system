package com.zjxu97.costume.service.store;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.param.KeyWordsPageParam;
import com.zjxu97.costume.model.param.LocationIdPageParam;
import com.zjxu97.costume.model.vo.StoreVo;

import java.util.List;

public interface StoreService extends IService<Store> {
    IPage<Store> listStoresByDist(LocationIdPageParam locationIdPageParam);

    IPage<Store> listStoresByCity(LocationIdPageParam locationIdPageParam);

    IPage<Store> listStoresByProv(LocationIdPageParam locationIdPageParam);

    IPage<Store> listStoresByArea(LocationIdPageParam locationIdPageParam);


    IPage<Store> searchStores(KeyWordsPageParam keyWordsPageParam);

    List<StoreVo> getStoreVoFromEntityList(List<Store> storeList);
}
